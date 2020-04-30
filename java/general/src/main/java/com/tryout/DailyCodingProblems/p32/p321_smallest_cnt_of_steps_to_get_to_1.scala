package com.tryout.DailyCodingProblems.p32

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

// Given a positive integer N, find the smallest number of steps it will take to reach 1.
//
//There are two kinds of permitted steps:
//
// You may decrement N to N - 1.
// If a * b = N, you may decrement N to the larger of a and b.
//For example, given 100, you can reach 1 in five steps with the following route: 100 -> 10 -> 9 -> 3 -> 2 -> 1.
object p321_smallest_cnt_of_steps_to_get_to_1 {

  def getDivisors(in: Int): ArrayBuffer[Int] = {
    val res = ArrayBuffer[Int]()
    val x = Math.pow(in,0.5).toInt
    for(i<-Range(x,1,-1)){
      if(in%i==0)
        res+=in/i
    }
    res
  }

  def steps(in:Int):Unit = {
    val visited = mutable.Set[Int]()
    val deq = mutable.ArrayDeque[(Int,Int)]()
    deq.append((in,0))
    //    deq.append((2,2))
    //    deq.append((3,3))
    //
    //    println(deq.removeLast())
    //    println(deq.removeLast())
    //    println(deq.removeLast())

    while (deq.nonEmpty){
      val (num, moves) = deq.removeHead()
      visited+=num
      if (num==1){
        println(moves)
      }

      val candidates = getDivisors(num) ++ Array(num-1)
      for(c<-candidates){
        if(!visited.contains(c)){
          deq.append((c,moves+1))
        }
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val in = 100    // 100 -> 10 -> 9 -> 3 -> 2 -> 1
    //val divs = getDivisors(in)
    //divs.foreach(println(_))

    steps(100)
  }

}
