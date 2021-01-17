const cheerio = require('cheerio');
const URL = require('url');
const { v4: uuidv4 } = require('uuid');
const utils = require('../lib/utils');
const Dispatcher = require('../lib/dispatcher');

const dispatcher = new Dispatcher(process.env.SNS_JOBS_TOPIC_ARN);

module.exports.run = (event, context, callback) => {
    const eventBody = JSON.parse(event.body);
    const { url, linkSelector, mapping } = eventBody;
    const sourceURL = URL.parse(url);
    console.log("sourceURL " + JSON.stringify(sourceURL));
    const origin = `${sourceURL.protocol}//${sourceURL.hostname}`;
    const crawlId = uuidv4();

    return utils.getHTML(url)
        .then(html => findTargetUrls(html, linkSelector, origin))
        .then((targetUrls) => {
            const jobs = buildJobs(crawlId, targetUrls, mapping);
            return dispatcher.sendBatch(jobs);
        })
        .then(() => {
            const response = {
                statusCode: 200,
                headers: {
                    'Access-Control-Allow-Origin': '*',
                },
                body: JSON.stringify({
                    id: crawlId,
                }),
            };
            callback(null, response);
        })
        .catch((error) => {
            callback(error);
        });
};

// Helper functions

function findTargetUrls(html, linkSelector, origin) {
    const $ = cheerio.load(html);
    const targetUrls = $(linkSelector).map((index, elem) => {
        const href = $(elem).attr('href');
        const destination = URL.parse(href);
        const url = (destination.protocol && destination.hostName)
            ? href : `${origin}${destination.path}`;
        return url;
    }).get();
    console.log(`Found ${targetUrls.length} matching targetUrls`);
    console.log(targetUrls);
    return targetUrls;
}

function buildJobs(crawlId, targetUrls, mapping) {
    console.log(`crawlId ${crawlId} targetUrls ${targetUrls} mapping ${mapping}`);
    const jobs = targetUrls.map(url => Object({
        crawlId,
        url,
        mapping,
    }));
    console.log(`jobs ${JSON.stringify(jobs)}`);
    return jobs;
}