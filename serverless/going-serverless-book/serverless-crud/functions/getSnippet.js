const AWS = require('aws-sdk');

const db = new AWS.DynamoDB.DocumentClient();

module.exports.handler = (event, context, callback) => {
    console.log(JSON.stringify(event));
    const snippetId = event.pathParameters.id;
    const params = {
        Key: {
            id: snippetId,
        },
        TableName: process.env.SNIPPETS_TABLE_NAME,
    };
    return db.get(params).promise()
        .then(({ Item }) => {
            let response;
            if (Item) {
                response = {
                    statusCode: 200,
                    headers: {
                        "Access-Control-Allow-Origin": "*"
                    },
                    body: JSON.stringify(Item),
                };
            } else {
                response = {
                    statusCode: 404,
                    headers: {
                        "Access-Control-Allow-Origin": "*"
                    },
                    body: null,
                }
            }
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

