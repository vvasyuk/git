package com.tryout.DailyCodingProblems.p29

import scala.collection.mutable.ArrayBuffer

// Pascal's triangle is a triangular array of integers constructed with the following formula:
//
//The first row consists of the number 1.
//For each subsequent row, each element is the sum of the numbers directly above it, on either side.
//For example, here are the first few rows:
//
//    1
//   1 1
//  1 2 1
// 1 3 3 1
//1 4 6 4 1
//Given an input k, return the kth row of Pascal's triangle.
//
//Bonus: Can you do this using only O(k) space?
object p295_pascals_triangle {

  def execute(k: Int): ArrayBuffer[Int] = {
    val arr = ArrayBuffer.fill(k+1)(0)
    arr(1) = 1
    for(i <- 1 until k+1;
        j <- i until 0 by -1
        ){
      println("i/j : " + i + "/" + j)
      arr(j) += arr(j-1)
    }
    arr
  }

  def main(args: Array[String]): Unit = {
    val k = 5
    execute(k).foreach(print(_))
  }
}