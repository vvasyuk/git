package com.tryout.DailyCodingProblems.p27

// A fixed point in an array is an element whose value is equal to its index. Given a sorted array of distinct elements, return a fixed point, if one exists. Otherwise, return False.
//
//For example, given [-6, 0, 2, 40], you should return 2. Given [1, 5, 7, 8], you should return False.
object n273_array_fixed_point {

  def main(args: Array[String]): Unit = {
    println("start")
    val nums = Vector(-6, 0, 2, 40)
//    for ((v, i) <- nums.zipWithIndex) {
//      println(i + ":" + v)
//    }
    val res = nums.view.zipWithIndex.takeWhile(x=>x._2>=x._1).exists(x=>x._1==x._2)
      //.foreach(x=>println(x._2 + ":" + x._1))

    println(res)
  }
}
