const AWS = require('aws-sdk');

const db = new AWS.DynamoDB.DocumentClient();

module.exports.handler = (event, context, callback) => {
  console.log(JSON.stringify(event));
  const text = JSON.parse(event.body).text;
  const snippetId = event.pathParameters.id;
  const params = {
    Key: {
      id: snippetId,    
    },
    UpdateExpression: 'set #t = :t',
    ExpressionAttributeNames: {
      '#t' : 'text'
    },
    ExpressionAttributeValues: {
      ':t': text,
    },
    TableName: process.env.SNIPPETS_TABLE_NAME,
  };
  return db.update(params).promise()
    .then(() => {
      const response = {
        statusCode: 200,
        headers: {
          'Access-Control-Allow-Origin': "*"
        },
        body: JSON.stringify({
          text: text,
        }),
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