const LambdaTester = require('lambda-tester');
const { calculateDay } = require('../handler');

const testEvent = {
    body: '{"date":"22 May 2017"}'
};

const testEventWithInvalidDate = {
    body: '{"date":"boom"}'
};

describe('hander()', () => {
    test('returns the correct day of the week', () =>
        LambdaTester(calculateDay)
            .event(testEvent)
            .expectResult((response) => {
                expect(response.statusCode).toBe(200);
                const data = JSON.parse(response.body);
                expect(data.day).toBe('Monday');
            })
    );
    test('returns sensible error for invalid date', () =>
        LambdaTester(calculateDay)
            .event(testEventWithInvalidDate)
            .expectResult((response) => {
                expect(response.statusCode).toBe(500);
                const data = JSON.parse(response.body);
                expect(data.error).toBe("Date parsing failed!");
            })
    )
});
