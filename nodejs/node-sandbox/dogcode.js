// dogcode.js
module.exports = async (axiosPath) => {
    console.log(`axiosPath ${axiosPath}`);
    const axios = require(axiosPath);
    const res = await axios.get('https://dog.ceo/api/breeds/image/random');
    return res.data;
};
