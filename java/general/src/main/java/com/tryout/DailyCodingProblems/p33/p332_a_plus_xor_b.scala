package com.tryout.DailyCodingProblems.p33

import scala.collection.mutable

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

  def simple(m: Int, n: Int): mutable.Map[Int,Int] = {
    val res = mutable.Map[Int,Int]()
    for(a<-0 to m/2){
      val b = m-a
      val xor = (a^b)
      println(s"a $a b $b xor $xor")
      if((a^b)==n)
        res(a) = b
    }
    res
  }

  // Note that when applied to two binary digits, XOR is essentially an add without carry operation. It follows that the sum of two integers a and b can be written as a ^ b + 2 * (a & b), where the latter term represents the carry. Rearranging terms, we can set a & b = (M - N) / 2.
  def main(args: Array[String]): Unit = {
    val a = 5
    val b = 2
    println(a.toBinaryString)
    println(b.toBinaryString)
    val c = a ^ b
    val d = a & b
    val sum = a ^ b + 2 * (a & b)
    println(s"c $c d $d sum $sum")

    val res = simple(11,10)
    println(res)
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