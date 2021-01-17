const AWS = require('aws-sdk');
const { v4: uuidv4 } = require('uuid');

const db = new AWS.DynamoDB.DocumentClient();

module.exports.handler = (event, context, callback) => {
    console.log(JSON.stringify(event));
    const text = JSON.parse(event.body).text;

    const params = {
        Item: {
            id: uuidv4(),
            text
        },
        TableName: process.env.SNIPPETS_TABLE_NAME,
    };

    return db.put(params).promise()
        .then(() => {
            const response = {
                statusCode: 201,
                headers: {
                    'Access-Control-Allow-Origin': "*"
                },
                body: JSON.stringify(params.Item),
            };
            console.log(response);
            callback(null, response);
        })
        .catch((err) => {
            const response = {
                statusCode: 500,
                headers: {
                    'Access-Control-Allow-Origin': "*"
                },
                error: err.message,
            };
            console.log(err.message);
            callback(null, response);
        });
};