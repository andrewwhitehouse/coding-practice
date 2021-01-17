const UsersDB = [
  {
    username: 'Cthon98',
    password: 'hunter2',
    scopes: ['armadillos'],
  },
  {
    username: 'AzureDiamond',
    password: '*******',
    scopes: [],
  },
];

function login(username, password) {
  const matched = UsersDB.filter((u) => {
    return u.username === username && u.password === password;
  });
  if (matched.length == 0) {
    throw "Username/password not found";
  }
  return { username, scopes: matched[0].scopes };
}

module.exports = {
    login,
};