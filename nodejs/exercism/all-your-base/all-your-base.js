function checkBase(base, errorMessage) {
  if (   (base == undefined)
      || (base < 2)
      || (base !== parseInt(base, 10))) {
      throw errorMessage;
  }
}

function checkInput(digits, base) {
  if (   (digits.length == 0)
      || ((digits.length > 0) && (digits[0] == 0))
      || (digits.filter(value => value < 0).length > 0)
      || (digits.filter(value => value >= base).length > 0)) {
      throw 'Input has wrong format';
  }
}

function toValue(digits, base) {
  if (digits == [0]) {
    return 0;
  }
  checkInput(digits, base);
  let value = digits[0];
  for(var i=1; i < digits.length; i++) {
      value = value*base + digits[i];
  }
  return value;
}

function toDigits(value, base) {
  if (value == 0) {
    return [0];
  }
  let digits = [];
  while (value > 0) {
    let quotient = Math.floor(value/base);
    let remainder = value % base;
    digits.push(remainder);
    value = quotient;
  }
  return digits.reverse();
}

export const convert = (digits, fromBase, toBase) => {
  checkBase(fromBase, 'Wrong input base');
  checkBase(toBase, 'Wrong output base');
  if (digits.length == 1 && digits[0] == 0) {
    return digits;
  }
  return toDigits(toValue(digits, fromBase), toBase);
};
