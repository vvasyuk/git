package com.tryout.DailyCodingProblems.p28

import scala.collection.immutable.TreeMap
import scala.collection.mutable

// A wall consists of several rows of bricks of various integer lengths and uniform height. Your goal is to find a vertical line going from the top to the bottom of the wall that cuts through the fewest number of bricks. If the line goes through the edge between two bricks, this does not count as a cut.
//
//For example, suppose the input is as follows, where values in each row represent the lengths of bricks in that row:
//
//[[3, 5, 1, 1],
// [2, 3, 3, 2],
// [5, 5],
// [4, 4, 2],
// [1, 3, 3, 3],
// [1, 1, 6, 1, 1]]
//The best we can we do here is to draw a line after the eighth brick, which will only require cutting through the bricks in the third and fifth row.
//
//Given an input consisting of brick lengths for each row such as the one above, return the fewest number of bricks that must be cut to create a vertical line.

//+----------+
//|3335555511|    3 8 9 10
//+----------+
//|2233333322|    2 5 8 10
//+----------+
//|5555555555|    5 10
//+----------+
//|4444444422|    4 8 10
//+----------+
//|1333333333|    1 4 7 10
//+----------+
//|1166666611|    1 2 8 9 10
//+----------+

object n281_cut_bricks {
  def main(args: Array[String]): Unit = {
    val in = Array(
      Array(3, 5, 1, 1),
      Array(2, 3, 3, 2),
      Array(5, 5),
      Array(4, 4, 2),
      Array(1, 3, 3, 3),
      Array(1, 1, 6, 1, 1),
    )

    val mm=mutable.Map[Int,Int]()
    in.foreach(a=>{
      a.foldLeft((mm,0)){(a,v)=>{
        val sum = a._2+v
        a._1.get(sum) match{
          case Some(e)=>mm.update(sum,e+1)
          case None=>mm.put(sum,1)
        }
        (a._1,sum)
      }}
    })

    println(mm)
    println("result: " + mm.toSeq.sortWith((a,b)=>a._2>b._2)(1))
    println("brick: " + mm.toSeq.sortWith((a,b)=>a._2>b._2)(1)._1)
  }
}
