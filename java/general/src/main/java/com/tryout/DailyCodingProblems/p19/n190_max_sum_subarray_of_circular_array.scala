package com.tryout.DailyCodingProblems.p19

object n190_max_sum_subarray_of_circular_array {
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
    val maxSubarraySumWraparound = ints.sum - minSoFar
    val res = Math.max(maxSoFar, maxSubarraySumWraparound)
    println("max_sum_subarray_of_circular_array_190: " + res)
  }

  def main(args: Array[String]):Unit={
    //Given a circular array, compute its maximum subarray sum in O(n) time.
    execute(Array(8, -1, 3, 4))   // return 15 as we choose the numbers 3, 4, and 8 where the 8 is obtained from wrapping around.
  }
}

//Solution
//This question is very similar to Daily Coding Problem #49, which asked to find the maximum subarray sum of an array, although in this case it's possible that the subarray can wrap around.
//
//So we can split this problem into two parts. One part is the same as before: find the maximum subarray sum that doesn't wrap around. We also can find the maximum subarray sum that does wrap around, and take the maximum of the two.
//
//To find the maximum subarray sum that wraps around, we can find the minimum subarray sum that doesn't wrap around by similar logic as #49, and subtract the total sum of the array by it.
//
//def maximum_circular_subarray(arr):
//max_subarray_sum_wraparound = sum(arr) - min_subarray_sum(arr)
//return max(max_subarray_sum(arr), max_subarray_sum_wraparound)
//
//
//def max_subarray_sum(arr):
//max_ending_here = max_so_far = 0
//for x in arr:
//max_ending_here = max(x, max_ending_here + x)
//max_so_far = max(max_so_far, max_ending_here)
//return max_so_far
//
//
//def min_subarray_sum(arr):
//min_ending_here = min_so_far = 0
//for x in arr:
//min_ending_here = min(x, min_ending_here + x)
//min_so_far = min(min_so_far, min_ending_here)
//return min_so_far
//This takes O(n) time and O(1) space.