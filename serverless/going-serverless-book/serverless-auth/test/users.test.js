const users = require('../lib/users');

/* global expect */

describe('login()', () => {
    test('returns user with scope', () => {
        const user = users.login('Cthon98', 'hunter2');
        expect(user.username).toMatch('Cthon98');
        expect(user.scopes).toEqual(['armadillos']);
    });
    test('returns user with empty scope', () => {
        const user = users.login('AzureDiamond', '*******');
        expect(user.scopes).toEqual([]);
    });
    test('throws error on no match', () => {
        expect(() => users.login('user', 'foo'))
            .toThrow("Username/password not found");
    })
});
