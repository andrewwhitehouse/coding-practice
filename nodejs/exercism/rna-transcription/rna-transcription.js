const complement = (nucleotide) => {
  const mappings = {G: 'C', C: 'G', T: 'A', A: 'U'};
  return mappings[nucleotide];
};

export const toRna = (dna) => {
  return dna.split('').map(complement).join('');
};
