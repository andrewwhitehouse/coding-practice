const AWS = require('aws-sdk');

const db = new AWS.DynamoDB.DocumentClient();

module.exports.handler = (event, context, callback) => {
    console.log(JSON.stringify(event));
    const params = {
        TableName: process.env.SNIPPETS_TABLE_NAME,
    };
    return db.scan(params).promise()
        .then(({ Items }) => {
            console.log(Items);
            const response = {
                statusCode: 200,
                headers: {
                    "Access-Control-Allow-Origin": "*"
                },
                body: JSON.stringify(Items),
            };
            console.log(response);
            callback(null, response);
        })
        .catch((err) => {
            const response = {
                statusCode: 500,
                headers: {
                    "Access-Control-Allow-Origin": "*"
                },
                error: err.message,
            };
            console.log(err.message);
            callback(null, response);
        });
};