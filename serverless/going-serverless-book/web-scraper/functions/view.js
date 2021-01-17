const Crawls = require('../lib/models/crawls');

const crawls = new Crawls(process.env.CRAWLS_TABLE_NAME);

module.exports.run = (event, context, callback) => {
    const crawlsId = event.pathParameters.id;

    return crawls.find(crawlsId)
        .then((results) => {
            const response = {
                statusCode: 200,
                headers: {
                    'Access-Control-Allow-Origin': '*',
                },
                body: JSON.stringify({ data: results }),
            };
            callback(null, response);
        })
        .catch((err) => {
            const response = {
                statusCode: 500,
                headers: {
                    'Access-Control-Allow-Origin': '*',
                },
                body: JSON.stringify({ error: err.message }),
            };
            callback(null, response);
        });
};