// nodesecure.js
const { NodeVM } = require("vm2");
const { join } = require("path");
const fs = require("fs");

const vm = new NodeVM({
  sandbox: {},
  require: {
    external: ["axios"],
    builtin: ["url", "crypto"],
    root: join(__dirname, "node_modules")
  }
});

const code = fs.readFileSync(join(__dirname, 'dogcode.js'));
const axiosPath = join(__dirname, 'node_modules/axios');

vm.run(code)(axiosPath).then(result => {
  console.log(result.message); // Dog image
});
