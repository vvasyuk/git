package dcp.p0

import scala.collection.mutable.ArrayBuffer

object q009_largetstNonAdjacent {
  def main(args: Array[String]): Unit = {
    val in = Array(2, 4, 6, 2, 5) // 13 - we pick 2, 6, and 5
    val in2 = Array(5, 1, 1, 5) // 10
    println(largetstNonAdjacent(in))
  }

  def largetstNonAdjacent(arr: Array[Int]): Int =
    val res = ArrayBuffer.fill[Int](arr.size)(0)

    //second is index
    arr.zipWithIndex.foldLeft(res){ (acc, e) =>{
      val (el, indx) = e

      if(indx-2 >= 0) then
        acc(indx) = acc(indx-2) + el
      else
        acc(indx) = el
      acc
    }}
    math.max(res.last, res.init.last)

}
