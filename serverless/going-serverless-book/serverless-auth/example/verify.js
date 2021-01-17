const jwt = require('jsonwebtoken');

const token = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoxMjMsInNjb3BlcyI6WyJ1c2VyczpyZWFkIl19LCJpYXQiOjE2MDU5ODUxMTIsImV4cCI6MTYwNTk4ODcxMn0.mj6rSnTtHKLht5rVVSzpG0xk9M7uZXLrtjd9J8wXTME';

const secret = 'shhh';

try {
  const decoded = jwt.verify(token, secret);
  console.log(decoded.user.id);
  console.log(decoded.user.scopes);
} catch (err) {
  console.log(err);
}