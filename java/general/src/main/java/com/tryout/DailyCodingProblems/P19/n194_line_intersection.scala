package com.tryout.DailyCodingProblems.P19

object n194_line_intersection {

  def execute(l1: Array[Int], l2: Array[Int]): Unit = {

    var cnt =0
    val lineSegments = l1.zip(l2)

    //crossing of 1st segment with second and third
    //crossing of 2nd segment with third
    for(n<-lineSegments.zipWithIndex;
        m<-lineSegments.zipWithIndex if n._2 >m._2
        ){
      val p1 = n._1
      val p2 = m._1
      var inter=false

      if((p1._1 > p2._1 && p1._2 < p2._2) || (p1._1 < p2._1 && p1._2 > p2._2)){
        inter = true
      }
      println(p1 + " : " + p2 + " intersecting: " + inter)
    }
  }

  def main(args: Array[String]):Unit={
    val l1 = Array(1,2,5)
    val l2 = Array(3,2,6)
    execute(l1, l2)
  }
}
