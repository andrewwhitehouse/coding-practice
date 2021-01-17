const jwt = require('jsonwebtoken');
const users = require('../lib/users');

const JWT_EXPIRATION_TIME = '30m';

module.exports.handler = (event, context, callback) => {
  const { username, password } = JSON.parse(event.body);
  try {
    const user = users.login(username, password);
    
    const token = jwt.sign({ user }, process.env.JWT_SECRET, { expiresIn: JWT_EXPIRATION_TIME });
    const response = {
      statusCode: 200,
      headers: {
        'Access-Control-Allow-Origin': '*',
      },
      body: JSON.stringify({
        token,
      }),
    };
    callback(null, response);
  } catch (e) {
    const response = {
      statusCode: 401,
      headers: {
        'Access-Control-Allow-Origin': '*',
      },
      body: JSON.stringify({
        error: e.message
      }),
    };
    callback(null, response);
  }
};