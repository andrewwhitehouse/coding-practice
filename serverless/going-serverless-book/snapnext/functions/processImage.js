const _ = require('lodash');
const fs = require('fs');
const path = require('path');
const AWS = require('aws-sdk');
const gm = require('gm').subClass({ imageMagick: true });

const s3 = new AWS.S3();

module.exports.handler = (event, context, callback) => {
    const { facialAnalysis, id } = event.Records[0].dynamodb.NewImage;
    const fileName = id.S;
    console.log("fileName " + fileName);
    const s3SourceKey = `${process.env.ORIGINAL_FOLDER_NAME}/${fileName}`;
    const s3DestinationKey = `${process.env.PROCESSED_FOLDER_NAME}/${fileName}`;
    console.log("source " + s3SourceKey + " destination " + s3DestinationKey);

    const params = {
        Bucket: process.env.IMAGES_BUCKET_NAME,
        Key: s3SourceKey,
    };

    return s3.getObject(params).promise()
        .then((data) => {
            console.log("Retrieved image, cropping face");
            const buffer = data.Body;
            return cropFace(buffer, facialAnalysis);
        })
        .then((outputBuffer) => {
            console.log("Face cropped, now saving to S3");
            return s3.putObject({
                Bucket: process.env.IMAGES_BUCKET_NAME,
                Key: s3DestinationKey,
                Body: outputBuffer,
                ACL: 'public-read',
            }).promise();
        })
        .then((res) => {
            console.log(`Image saved to ${s3DestinationKey}`);
            callback(null, { success: true });
        });
};

const cropFace = (buffer, facialAnalysis) => {
    return sizeImage(buffer)
        .then((image) => {
            const face = _.get(facialAnalysis, 'M.FaceDetails.L[0].M.BoundingBox.M', false);
            console.log(`Bounding box ${face}`);
            const faceWidth = face.Width.N * image.width;
            const faceHeight = face.Height.N * image.height;
            const faceX = face.Left.N * image.width;
            const faceY = face.Top.N * image.height;

            // If there's a face in the image, return a crop of the face
            return (face ? cropImage(buffer, faceWidth, faceHeight, faceX, faceY) : buffer);
        });
}

const sizeImage = (buffer) => {
    return new Promise((resolve, reject) => {
        gm(buffer)
            .size((err, dimensions) => {
                console.log(`sizeImage err ${err} dimensions ${dimensions}`);
                if (err) {
                    reject(err);
                } else {
                    resolve(dimensions);
                }
            })
        });
}

const cropImage = (buffer, width, height, x, y) => {
    return new Promise((resolve, reject) => {
        gm(buffer)
            .crop(width, height, x, y)
            .toBuffer((err, buf) => {
                if (err) {
                    reject(err);
                } else {
                    resolve(buf);
                }
            });
    });
}