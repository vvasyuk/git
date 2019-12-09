package com.tryout.DailyCodingProblems

object n49_max_sum_subarray {

  def execute(ints: Array[Int]): Unit = {
    var maxEndingHere = 0
    var maxSoFar = 0

    ints.foreach(x=>{
      maxEndingHere=Math.max(x, maxEndingHere+x)
      maxSoFar=Math.max(maxSoFar, maxEndingHere)
    })

    println(maxSoFar)


    var minEndingHere = 0
    var minSoFar = 0
    ints.foreach(x=>{
      minEndingHere=Math.min(x, minEndingHere+x)
      minSoFar=Math.min(minSoFar, minEndingHere)
    })

    println(minSoFar)
  }

  def main(args: Array[String]):Unit={
    //Given an array of numbers, find the maximum sum of any contiguous subarray of the array.
    execute(Array(34, -50, 42, 14, -5, 86))   //maximum sum would be 137, since we would take elements 42, 14, -5, and 86.
  }
}

//We can work backwards from our desired solution by iterating over the array and looking at the maximum possible subarray that can be made ending at each index.
// At each index, either we can include that element in our sum or we exclude it.
//
//We can then keep track of the maximum subarray we've seen so far in a variable max_so_far,
// compute the maximum subarray that includes x at each iteration, and choose whichever one is bigger.
//
//def max_subarray_sum(arr):
//max_ending_here = max_so_far = 0
//for x in arr:
//max_ending_here = max(x, max_ending_here + x)
//max_so_far = max(max_so_far, max_ending_here)
//return max_so_far
//This algorithm is known as Kadane's algorithm, and it runs in O(N) time and O(1) space.