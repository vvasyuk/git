package com.tryout.DailyCodingProblems.p30

// Write an algorithm that finds the total number of set bits in all integers between 1 and N.
// TODO: investigate p309_bit_sum
object p309_bit_sum {
  def main(args: Array[String]): Unit = {
    var N = 5 // 0101, 0100, 0011, 0010, 0001 = 7
    var ans = 0
    var two = 2
    var n = N

    while(n>0){
      ans+= (N/two)*(two>>1)
      if((N&(two-1)) > (two>>1)-1) ans += (N&(two-1)) - (two>>1)+1
      two <<= 1
      n >>= 1
    }

    println(ans)
  }
}

//int getSetBitsFromOneToN(int N){
//  int two = 2,ans = 0;
//  int n = N;
//  while(n){
//  ans += (N/two)*(two>>1);
//  if((N&(two-1)) > (two>>1)-1) ans += (N&(two-1)) - (two>>1)+1;
//  two <<= 1;
//  n >>= 1;
//}
//  return ans;
//}

// or
// Even | Odd
//------------
//000  | 001
//010  | 011
//100  | 101
//Note that the two columns only differ in their last column, with even numbers having zeroes and odd numbers having ones. If there are an equal amount of even and odd numbers, which will happen if N is odd, this column will contribute a number of set bits equivalent to (N + 1) // 2. For example, here N == 5, so there are three numbers in each group, and each of the odd integers has a one in the last column.
//
//Furthermore, if we cut off this last column, the prefixes are the same in each group, and represent exactly the numbers between 0 and N // 2.
//
//Therefore, for an odd number N, we can form a recursive relationship for the number of set bits up to N:
//
//f(N) = (N + 1) / 2 + 2 * f(N / 2)

// def total_set_bits(n):
//    if n == 0:
//        return 0
//    elif n % 2 == 1:
//        return (n + 1) // 2 + 2 * total_set_bits(n // 2)
//    else:
//        return count_set_bits(n) + total_set_bits(n - 1)