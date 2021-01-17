const AWS = require('aws-sdk');

const rek = new AWS.Rekognition();
const db = new AWS.DynamoDB.DocumentClient();

module.exports.handler = (event, content, callback) => {
    console.log("analyzeImage invoke");
    console.log(event);
    const s3Key = event.Records[0].s3.object.key;
    const imageId = s3Key.replace(`${process.env.ORIGINAL_FOLDER_NAME}/`, '');
    console.log(`analyzeImage: imageId ${imageId}`);

    const detectFacesParams = {
        Image: {
            S3Object: {
                Bucket: process.env.IMAGES_BUCKET_NAME,
                Name: s3Key,
            },
        },
        Attributes: [
            'ALL',
        ],
    };
    return rek.detectFaces(detectFacesParams).promise()
        .then((facialAnalysisResult) => {
            const putParams = {
                Item: {
                    id: imageId,
                    facialAnalysis: facialAnalysisResult,
                    timestamp: Date.now(),
                },
                TableName: process.env.IMAGES_TABLE_NAME,
            };
            console.log("Adding imageId " + imageId + " to table " + process.env.IMAGES_TABLE_NAME);
            return db.put(putParams).promise();
        })
        .then(() => {
            const response = {
                statusCode: 201,
                body: JSON.stringify({success: true}),
            };
            callback(null, response);
        });
};