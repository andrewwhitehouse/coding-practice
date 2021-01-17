const AWS = require('aws-sdk');

const kms = new AWS.KMS();

module.exports.handler = (event, context, callback) => {
  console.log(process.env.MY_SECRETS);
  return kms.decrypt({
    CiphertextBlob: Buffer(process.env.MY_SECRETS, 'base64'),
  }).promise()
  .then((data) => {
    console.log(data);
    const decrypted = String(data.Plaintext);
    const secrets = JSON.parse(decrypted);
    callback(null, secrets);
  });
};