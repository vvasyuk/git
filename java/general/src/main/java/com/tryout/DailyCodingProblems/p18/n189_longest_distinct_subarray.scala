package com.tryout.DailyCodingProblems.p18

import scala.collection.mutable.Map

object n189_longest_distinct_subarray {

  def execute(arr:Array[Int]): Unit={
    val m = Map[Int,Int]()
    var res = 0
    var longestDistinctSubarrayIndex = 0

    arr.zipWithIndex.foreach(x=>{
      if(m.keySet.exists(_==x._1)){	// element exists in map
        if(m(x._1) >= longestDistinctSubarrayIndex){
          res = Math.max(res, x._2 - longestDistinctSubarrayIndex)
          longestDistinctSubarrayIndex = m(x._1)+1	// starts aftet repeated element
        }
      }
      m+=(x._1 -> x._2)
    })

    println(Math.max(res, arr.size-longestDistinctSubarrayIndex))
  }

  def main(args: Array[String]):Unit={
    execute(Array(5,1,3,5,2,3,4,1))
  }
}
