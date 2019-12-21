package com.tryout.DailyCodingProblems.P18

object rectangle_intersection_187 {
  def isIntersecting(r1: RectangleCoordinates2, r2: RectangleCoordinates2):Boolean={
    if (r1.topLeft._1 >= r2.topLeft._1 + r2.dimensions._1) return false	//left side > then right side
    if (r1.topLeft._1 + r1.dimensions._1 <= r2.topLeft._1) return false //right side > then left side

    if (r1.topLeft._2 <= r2.topLeft._2 - r2.dimensions._2) return false //r1 upper border lower than r2 lower border
    if (r1.topLeft._2 - r1.dimensions._2 >= r2.topLeft._2) return false
    return true
  }

  def execute(arr:Array[RectangleCoordinates2]): Unit={
    val n = arr.size
    arr.zipWithIndex.foreach(x=>
      arr.slice(x._2+1, n).foreach(y=>
        println(x._1.id + " : " + y.id + " - " + isIntersecting(x._1, y))
      )
    )
  }

  def main(args: Array[String]):Unit={
    val rec1 = RectangleCoordinates2("a", (1, 4), (3, 3))
    val rec2 = RectangleCoordinates2("b", (-1, 3), (2, 1))
    val rec3 = RectangleCoordinates2("c", (0, 5), (4, 2))
    execute(Array(rec1, rec2, rec3))
  }
}

case class RectangleCoordinates2(id: String, topLeft: (Int, Int), dimensions: (Int, Int))
