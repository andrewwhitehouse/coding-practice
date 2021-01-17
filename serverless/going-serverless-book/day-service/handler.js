'use strict';

const getDayFromDate = (dateString) => {
  const DAYS = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
  console.log("dateString " + dateString);
  const date = new Date(Date.parse(dateString));
  if (date.toString() === 'Invalid Date') {  // Without toString we get NaN
    throw new Error('Date parsing failed!');
  }
  const dayIndex = date.getDay();
  const day = DAYS[dayIndex];
  return day;
};

const generateResponse = (statusCode, body) => {
  return {
    statusCode: statusCode,
    headers: {
      'Access-Control-Allow-Origin': '*',
    },
    body: JSON.stringify(body)
  };
};

module.exports.calculateDay = (event, context, callback) => {
  console.log('calculateDay invoked!');
  console.log(event);
  const input = JSON.parse(event.body);
  console.log(input);
  let day;
  try {
    day = getDayFromDate(input.date);
  } catch (e) {
    callback(null, generateResponse(500, {error: e.message}));
  }

  const body = {
    date: input.date,
    day: getDayFromDate(input.date)
  };
  callback(null, generateResponse(200, body));
};