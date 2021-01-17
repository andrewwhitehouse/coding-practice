const jwt = require('jsonwebtoken');

const secret = 'shhh';
const user = {
  id: 123,
  scopes: ['users:read']
};
const expiresIn = 3600; // 1 hour
const token = jwt.sign({user}, secret, {expiresIn});
console.log(`JWT issued: ${token}`);
