package com.tryout.DailyCodingProblems.p0
import scala.collection.mutable.ArrayBuffer

//Given a list of integers, write a function that returns the largest sum of non-adjacent numbers. Numbers can be 0 or negative.
//For example, [2, 4, 6, 2, 5] should return 13, since we pick 2, 6, and 5. [5, 1, 1, 5] should return 10, since we pick 5 and 5.
//Follow-up: Can you do this in O(N) time and constant space?
object p9_largest_non_adjacent {
  def main(args: Array[String]): Unit = {
    val in = Array(2, 4, 6, 2, 5)
    val res = ArrayBuffer.fill[Int](in.size)(0)

    //second is index
    in.zipWithIndex.foldLeft(res){ (acc, e) =>{
      val (el, indx) = e

      if(indx-2 >= 0){
        acc(indx) = acc(indx-2) + el
      } else {
        acc(indx) = el
      }
      acc
    }}


    println(
      math.max(res.last, res.init.last)
    )
  }
}
