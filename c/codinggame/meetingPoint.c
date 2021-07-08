#include <stdio.h>

int digitSum(int n) {
    int sum = 0;
    while (n > 0) {
      sum += n % 10;
      n /= 10;
    }
    return sum;
}

int meetingPoint(int r1Start, int r2Start) {
  int lower = r1Start <= r2Start ? r1Start : r2Start;
  int upper = r1Start > r2Start ? r1Start : r2Start;
  for(;;) {
      //printf("lower %d upper %d\n", lower, upper);
      while (lower < upper) {
          lower += digitSum(lower);
      }
      if (lower == upper) {
        return lower;
      }
      int temp = lower;
      lower = upper;
      upper = temp;
  }
}

int main() {
  printf("32, 47 -> %d\n", meetingPoint(32, 47)); // 47
  printf("57, 78 -> %d\n", meetingPoint(57, 78)); // 111
  printf("5, 7 -> %d\n", meetingPoint(5, 7)); // 620
  printf("456, 483 -> %d\n", meetingPoint(456, 483)); // 483
  printf("1158, 2085 -> %d\n", meetingPoint(1158, 2085)); // 2103
  printf("2489, 5026 -> %d\n", meetingPoint(2489, 5026)); // 5215
  printf("13, 14689 -> %d\n", meetingPoint(13, 14678)); // 20014
  printf("991, 997 -> %d\n", meetingPoint(991, 997)); // 1118
}
