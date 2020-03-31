package com.tryout.DailyCodingProblems.p28

// A regular number in mathematics is defined as one which evenly divides some power of 60.
// Equivalently, we can say that a regular number is one whose only prime divisors are 2, 3, and 5.
//
//These numbers have had many applications, from helping ancient Babylonians keep time to tuning instruments according to the diatonic scale.
//
//Given an integer N, write a program that returns, in order, the first N regular numbers.
object n283_regular_numbers {
  // each regular number must be a multiple of 2, 3, or 5. Suppose we store our solution in a sorted array.
  // Then at each step in our program, we will keep track of three counters, i2, i3, and i5, to represent the smallest indices of the array
  // such that multiplying 2, 3, or 5 by the corresponding index would create a previously unseen regular number.

  //        Solution         |  m2 |  m3 |  m5
  //--------------------------------------------
  //[1, 0, 0, 0, 0, 0, 0, 0] |  1  |  0  |  0
  //[1, 2, 0, 0, 0, 0, 0, 0] |  1  |  1  |  0
  //[1, 2, 3, 0, 0, 0, 0, 0] |  2  |  1  |  0
  //[1, 2, 3, 4, 5, 0, 0, 0] |  2  |  1  |  1
  //[1, 2, 3, 4, 5, 6, 0, 0] |  3  |  2  |  1
  //[1, 2, 3, 4, 5, 6, 8, 0] |  4  |  2  |  1
  //[1, 2, 3, 4, 5, 6, 8, 9] |  4  |  3  |  1
  def main(args: Array[String]): Unit = {
    val n = 10
    val res = Array.fill(n+1)(1)
    var i2 =0
    var i3 =0
    var i5 =0

    for(
      x <- 1 to n
    ){
      val m = List((2,2*res(i2)), (3,3*res(i3)), (5,5*res(i5))).minBy(_._2)
      res(x)=m._2
      m._1 match{
        case 2 => i2+=1
        case 3 => i3+=1
        case 5 => i5+=1
      }
    }

    res.foreach(println(_))
  }
}

//def regular_numbers(n):
//    solution = [1] * n
//    i2, i3, i5 = 0, 0, 0
//
//    for i in range(1, n):
//        m = min(2 * solution[i2], 3 * solution[i3], 5 * solution[i5])
//        solution[i] = m
//
//        if m % 2 == 0:
//            i2 += 1
//        if m % 3 == 0:
//            i3 += 1
//        if m % 5 == 0:
//            i5 += 1
//
//    return solution