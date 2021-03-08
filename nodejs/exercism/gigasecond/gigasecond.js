export const gigasecond = (date) => {
  const dateInMillis = date.getTime();
  const GIGA_SECOND = 1000000000000;
  return new Date(dateInMillis + GIGA_SECOND);
};
