const COLORS = ["black","brown","red","orange","yellow","green","blue","violet","grey","white"];

const colorCode = (color) => COLORS.indexOf(color);

export const value = (colors) => {
  var total = 0;
  var multiplier = 1;
  for(var i=colors.length-1; i >= 0; i--) {
      total += colorCode(colors[i]) * multiplier;
      multiplier *= 10;
  }
  return total;
};
