const fetch = require('node-fetch');
const Haikunator = require('haikunator');
const AWS = require('aws-sdk');

const haikunator = new Haikunator();
const s3 = new AWS.S3();

module.exports.handler = (event, context, callback) => {
    const args = JSON.parse(event.body);
    const imageId = haikunator.haikunate();
    return fetch(args.imageUrl)
        .then((res) => {
            if (res.ok) {
                return res;
            }
            return Promise.reject(new Error(
                `Failed to fetch ${res.url}: ${res.status} ${res.statusText}`));
        })
        .then(res => res.buffer())
        .then((buffer) => {
            const bucketName = process.env.IMAGES_BUCKET_NAME;
            return s3.putObject({
                Bucket: bucketName,
                Key: `${process.env.ORIGINAL_FOLDER_NAME}/${imageId}.png`,
                Body: buffer,
                ACL: 'public-read',
            }).promise();
        })
        .then((res) => {
            const response = {
                statusCode: 200,
                body: JSON.stringify({
                    id: imageId,
                }),
            };
            callback(null, response);
        })
        .catch((err) => {
            const response = {
                statusCode: 500,
                error: err.message,
            };
            callback(null, response);
        });
}

