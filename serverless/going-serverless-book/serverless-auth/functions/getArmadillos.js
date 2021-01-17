module.exports.handler = (event, context, callback) => {
  //console.log(event.requestContext);
  //const user = JSON.parse(event.requestContext.authorizer.user);
  //console.log(user);
  
  const response = {
    statusCode: 200,
    headers: {
      'Access-Control-Allow-Origin': '*',
    },
    body: JSON.stringify({
      armadillos: [
        { 
          id: 2,
          name: 'Arnold',
          address: '123 Carapace Drive',
        },
      ],
    }),
  };
  callback(null, response);
};