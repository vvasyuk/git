package com.tryout.DailyCodingProblems.p33

import scala.collection.mutable.ArrayBuffer

// You are given a string consisting of the letters x and y, such as xyxxxyxyy. In addition, you have an operation called flip,
// which changes a single x to y or vice versa.
//
//Determine how many times you would need to apply this operation to ensure that all x's come before all y's.
// In the preceding example, it suffices to flip the second and sixth characters, so you should return 2.
object p331_flip_x_y {

  //First pass over our input to determine the number of y's to the left of each element.
  //Second pass to find the number of x's to the right of each element.

  def execute(in: String): Int = {
    val n = in.size
    val y_left = ArrayBuffer.fill(n)(0)
    val x_right = ArrayBuffer.fill(n)(0)

    var (l, r) = (0,0)


    for(i<- 0 until n){
      y_left(i) = l
      if (in(i) == 'y') l+=1
    }
    for(i<- n-1 to 0 by -1){
      x_right(i) = r
      if (in(i) == 'x') r+=1
    }

    y_left.zip(x_right).map(x=>x._1+x._2).min
  }

  def main(args: Array[String]): Unit = {
    val in = "xyxxxyxyy"
    println(execute(in))
  }
}