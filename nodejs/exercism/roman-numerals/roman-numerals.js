

function units(number) {
  return "I".repeat(number % 10);
}

function tens(number) {
  return "X".repeat(Math.floor((number % 100) / 10));
}

function hundreds(number) {
  return "C".repeat(Math.floor((number % 1000) / 100));
}

function thousands(number) {
  return "M".repeat(Math.floor(number / 1000));
}

function simplify(romanNumber) {
  let number = romanNumber;
  const replacements = [
    ["IIIII", "V"],
    ["VV",    "X"],
    ["XXXXX", "L"],
    ["LL",    "C"],
    ["CCCCC", "D"],
    ["DD",    "M"],
    ["DCCCC", "CM"],
    ["CCCC",  "CD"],
    ["LXXXX", "XC"],
    ["XXXX",  "XL"],
    ["VIIII", "IX"],
    ["IIII",  "IV"],
  ];
  for(let [before, after] of replacements) {
    let regExp = new RegExp(before);
    console.log(number);
    number = number.replace(regExp, after);
  }
  return number;
}

export const toRoman = (number) => {
  return simplify(thousands(number) + hundreds(number) + tens(number) + units(number));
};
