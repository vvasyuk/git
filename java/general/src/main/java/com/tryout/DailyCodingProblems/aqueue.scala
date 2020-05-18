package com.tryout.DailyCodingProblems

import scala.collection.mutable

object aqueue {
  def main(args: Array[String]): Unit = {
    val q = mutable.Queue[Int]()
    q+=1
    q+=2
    while(q.nonEmpty) {
      println(q.dequeue())
    }
  }
}
