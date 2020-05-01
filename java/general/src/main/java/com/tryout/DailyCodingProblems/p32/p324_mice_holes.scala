package com.tryout.DailyCodingProblems.p32

// there are N mice and N holes placed at integer points along a line. Given this, find a method that maps mice to holes
// such that the largest number of steps any mouse takes is minimized.
//
//Each move consists of moving one mouse one unit to the left or right, and only one mouse can fit inside each hole.
//
//For example, suppose the mice are positioned at [1, 4, 9, 15], and the holes are located at [10, -5, 0, 16].
// In this case, the best pairing would require us to send the mouse at 1 to the hole at -5, so our function should return 6.
object p324_mice_holes {
  def main(args: Array[String]): Unit = {
    val mice = Array(1, 4, 9, 15).sorted
    val hole = Array(10, -5, 0, 16).sorted

    val res = mice.zip(hole)
      .foldLeft(0)((a,b)=>Math.max(a,Math.abs(b._1-b._2)))
    println(res)
  }
}