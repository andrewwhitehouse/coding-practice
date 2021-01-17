const AWS = require('aws-sdk');

module.exports.handler = (event, context, callback) => {
    const db = new AWS.DynamoDB.DocumentClient();
    const imageId = event.pathParameters.id;
    const params = {
        Key: {
            id: imageId,
        },
        TableName: process.env.IMAGES_TABLE_NAME,
    };
    return db.get(params).promise()
        .then(({Item}) => {
            const bucketBaseUrl = `https://${process.env.IMAGES_BUCKET_NAME}.s3.amazonaws.com`;
            const body = {
                id: imageId,
                original: `${bucketBaseUrl}/${process.env.ORIGINAL_FOLDER_NAME}/${imageId}`,
                processed: `${bucketBaseUrl}/${process.env.PROCESSED_FOLDER_NAME}/${imageId}`,
                timestamp: Item.timestamp,
                analysis: {
                    facial: Item.facialAnalysis,
                    scene: Item.sceneAnalysis,
                },
            };
            const response = {
                statusCode: 200,
                body: JSON.stringify(body),
            };
            callback(null, response);
        });
}