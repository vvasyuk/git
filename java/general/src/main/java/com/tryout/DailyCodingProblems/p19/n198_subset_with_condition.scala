package com.tryout.DailyCodingProblems.p19

import scala.collection.mutable.ArrayBuffer

object n198_subset_with_condition {

  def execute(arr: Array[Int]): Unit = {
    val res: ArrayBuffer[Int] = ArrayBuffer()
    var maxIndex = 0
    val numDivisors: ArrayBuffer[Int] = ArrayBuffer.fill(arr.size)(1)
    val prevDivisor: ArrayBuffer[Int] = ArrayBuffer.fill(arr.size)(-1)

    for (n <- Range(1, arr.size);
         m <- Range(0, n)) {
      println(arr(n) + " : " + arr(m))

      if (arr(n) % arr(m) == 0) {
        numDivisors(n) += numDivisors(m)
        prevDivisor(n) = m
      }

      if (numDivisors(maxIndex) < numDivisors(n)) {
        maxIndex = n
      }
    }

    Range(0,arr.size).foreach(i=> println("numDivisors: " + numDivisors(i) + " - " + "prevDivisor: " + prevDivisor(i)))
    println("maxIndex: " + maxIndex)

    while (maxIndex>=0){
      println("longet subset element:" + arr(maxIndex))
      res+=arr(maxIndex)
      maxIndex=prevDivisor(maxIndex)
    }

    res.reverse.foreach(println(_))
  }

  def main(args: Array[String]):Unit={
    execute(Array(3, 5, 10, 20, 21))
  }
}
