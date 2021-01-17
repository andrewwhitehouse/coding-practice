const scrapeIt = require('scrape-it');
const axios = require('axios');

function getHTML(url) {
    console.log(`getHTML ${url}`);
    if (!url) {
        return Promise.reject('Missing url for getHTML');
    }
    
    return new Promise((resolve, reject) => {
      const result = axios.get(url);
      result.then((response) => {
        resolve(response.data);    
      }).catch((error) => reject(error));
    });
}

function scrapeHTML(html, mapping) {
    console.log('scrapeHTML');
    if (!html || !mapping) {
        return Promise.reject('Missing arguments for scrapeHTML');
    }
    
    console.log(html);
    
    return new Promise((resolve, reject) => {
        try {
            const data = scrapeIt.scrapeHTML(html, mapping);
            console.log(data);
            resolve(data);
        } catch (e) {
            console.log(e.message);
            reject(e);
        }
    });
}

module.exports = {
    getHTML,
    scrapeHTML,
};