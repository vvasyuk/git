package com.tryout.DailyCodingProblems.p33

// Given integers M and N, write a program that counts how many positive integer pairs (a, b) satisfy the following conditions:
//
//a + b = M
//a XOR b = N
object p332_a_plus_xor_b {
  //XOR
  //0	0	0
  //1	0	1
  //0	1	1
  //1	1	0
  def main(args: Array[String]): Unit = {

  }
}

// def num_pairs(m, n):
//    xor_bits = bin(n)[2:]
//    and_bits = bin((m - n) // 2)[2:]
//
//    max_len = max(len(xor_bits), len(and_bits))
//    xor_bits = list(map(int, xor_pair.rjust(max_len, '0')))
//    and_bits = list(map(int, and_pair.rjust(max_len, '0')))
//
//    count = 1
//    for i in range(max_len):
//        if and_bits[i] == 1:
//            continue
//        elif xor_bits[i] == 1:
//            count *= 2
//
//    return count // 2