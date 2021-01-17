const _ = require('lodash');
const jwt = require('jsonwebtoken');
const utils = require('../lib/utils');

const authorizeUser = (userScopes, methodArn) => {
  const hasValidScope = _.some(userScopes, scope => _.endsWith(methodArn, scope));
  return hasValidScope;
}

module.exports.handler = (event, context, callback) => {
  const token = event.authorizationToken;
  console.log("token " + token);
  try {
    const decoded = jwt.verify(token, process.env.JWT_SECRET);
    const user = decoded.user;
    
    console.log("Decoded user " + user);
    
    const isAllowed = authorizeUser(user.scopes, event.methodArn);
    
    console.log("Decoded scopes " + user.scopes);
    console.log("methodArn " + event.methodArn);
    
    const effect = isAllowed ? 'Allow' : 'Deny';
    const userId = user.username;
    const authorizerContext = { user: JSON.stringify(user) }
    const policy = utils.buildIAMPolicy(userId, effect, event.methodArn, authorizerContext);
    
    console.log('Statement ' + JSON.stringify(policy.policyDocument.Statement));
    
    callback(null, policy);
  } catch (e) {
    callback('Unauthorized');
  }
};