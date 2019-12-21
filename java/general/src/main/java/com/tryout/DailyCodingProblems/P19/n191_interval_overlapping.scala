package com.tryout.DailyCodingProblems.P19

object n191_interval_overlapping {


  def execute(tuples: Array[(Int, Int)]): Unit = {
    //sort the intervals by the end time
    val sortedArray = tuples.sortBy(_._2)
    //sortedArray.foreach(println(_))
    var currentEnd = Double.NegativeInfinity
    var overlapping = 0

    sortedArray.foreach(x=>{
      if(x._1 >= currentEnd){ // if start >= currentEnd
        currentEnd = x._2     // currentEnd = end
      }else{
        overlapping+=1
      }
    })

    println("overlapping cnt: " + overlapping)
  }

  def main(args: Array[String]):Unit={
    //Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
    execute(Array((7, 9), (2, 4), (5, 8)))   // return 1 as the last interval can be removed and the first two won't overlap.
  }
}
