const _ = require('lodash');
const AWS = require('aws-sdk');

const db = new AWS.DynamoDB.DocumentClient();

module.exports.handler = (event, context, callback) => {
    const params = {
        TableName: process.env.IMAGES_TABLE_NAME,
    };
    console.log(params);
    return db.scan(params).promise()
        .then(({Items}) => {
            const bucketBaseUrl = `https://${process.env.IMAGES_BUCKET_NAME}.s3.amazonaws.com`;
            const body = _.map(Items, item => Object({
                id: item.id,
                original: `${bucketBaseUrl}/${process.env.ORIGINAL_FOLDER_NAME}/${item.id}`,
                processed: `${bucketBaseUrl}/${process.env.PROCESSED_FOLDER_NAME}/${item.id}`,
                timestamp: item.timestamp,
            }));
            const response = {
                statusCode: 200,
                body: JSON.stringify(body),
            };
            callback(null, response);
        });
};