package com.tryout.DailyCodingProblems

import scala.collection.mutable.ArrayBuffer

object n200_intervals_stabs {


  def execute(intervals: Array[(Int, Int)]): Unit = {
    val sortedIntervals = intervals.sortBy(x=>x._2)
    val resPoints = ArrayBuffer[Int]()
    var latestEndPoint = -1

    sortedIntervals.foreach(x=>{
      if(x._1>latestEndPoint){
        latestEndPoint = x._2
        resPoints+=(x._2)
      }
    })

    resPoints.foreach(println(_))

  }

  def main(args: Array[String]):Unit={
    execute(Array((1, 4), (4, 5), (7, 9), (9, 12))) //answer Array(4,9)
  }
}
