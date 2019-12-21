package com.tryout.DailyCodingProblems.P19

import scala.collection.mutable.ArrayBuffer

object n197_rotate_k_latest_elements {
  def reverse (arr: ArrayBuffer[Int], s:Int, e:Int)={
    var (start, end) = (s,e)

    while (start<end){
      val tmp = arr(start)
      arr(start) = arr(end)
      arr(end) = tmp
      start+=1
      end-=1
    }
    arr
  }

  def execute(arr: ArrayBuffer[Int], k:Int):Unit={
    val splitIndex = arr.size-k

    val secondHalfReversed = reverse(arr, splitIndex, arr.size-1)
    val firstHalfReversed = reverse(arr, 0, splitIndex-1)
    val res = reverse(firstHalfReversed, 0, arr.size-1)

    res.foreach(println(_))
  }

  def main(args: Array[String]):Unit={
    execute(ArrayBuffer(0,1,2,3,4,5,6,7,8,9), 3)
  }
}
