const vm = require('vm');
const sandbox = { https: require('https') };

vm.createContext(sandbox);
const code = `
    new Promise(resolve => {
      const req = https.get('https://dog.ceo/api/breeds/image/random', res => {
        let data = '';
        res.on('data', chunk => { data += chunk; } );
        res.on('end', () => { resolve(data); } )
      })
    })
`;

vm.runInContext(code, sandbox).then(result => {
  console.log(JSON.parse(result).message); // Dog image
});
