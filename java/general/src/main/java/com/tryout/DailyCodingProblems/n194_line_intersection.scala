package com.tryout.DailyCodingProblems

object n194_line_intersection {

  def execute(l1: Array[Int], l2: Array[Int]): Unit = {

    for(n<-l1.zipWithIndex;
        m<-l2.zipWithIndex if (m._1 > n._1)
        ){
      println(n._2 + " : " + m._2)
    }

  }

  def main(args: Array[String]):Unit={
    val l1 = Array(1,3,5)
    val l2 = Array(2,4,6)
    execute(l1, l2)
  }
}
