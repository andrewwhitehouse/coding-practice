const AWS = require('aws-sdk');
const https = require('https');

const httpsParams = {
    httpOptions: {
        agent: new https.Agent({
            keepAlive: false,
            ciphers: 'ALL',
            secureProtocol: 'TLSv1_method',
        }),
    },
};

/*
 * Used to publish scrape jobs. Handles communication with SNS.
 */
class Dispatcher {
    constructor(topicArn) {
        console.log(`Dispatcher topicArn ${topicArn}`);
        this.topicArn = topicArn;
        AWS.config.update({
            region: process.env.REGION,
        });
        this.sns = new AWS.SNS(httpsParams);
    }

    sendBatch(jobs) {
        if (!jobs) {
            return Promise.reject(new Error('Missing jobs for Dispatcher.sendBatch'));
        }

        return Promise.all(jobs.map(job => this.send(job)))
            .catch((err) => {
                throw new Error(`activeJobsTopic.sendBatch failed: ${err.message}`);
            });
    }

    send(job) {
        if (!job) return Promise.reject(new Error('Missing job for Dispatcher.send'));
        const params = {
            Message: JSON.stringify(job),
            TopicArn: this.topicArn,
        };
        return this.sns.publish(params).promise();
    }
}

module.exports = Dispatcher;