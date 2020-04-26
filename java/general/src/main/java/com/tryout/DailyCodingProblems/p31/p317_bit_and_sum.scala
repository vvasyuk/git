package com.tryout.DailyCodingProblems.p31

// Write a function that returns the bitwise AND of all integers between M and N, inclusive.
object p317_bit_and_sum {
  // 1: 0001
  // 2: 0010
  // 3: 0011
  // 4: 0100
  // 5: 0101
  def main(args: Array[String]): Unit = {
    val m = 1
    val n = 5
    var res = m

    for (i<-m+1 to n){
      //println(i)
      res &= i
    }
    println(res)
  }

}
