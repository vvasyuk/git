package com.tryout.DailyCodingProblems.p33

// Given an array of numbers and a number k, determine if there are three entries in the array which add up to the specified number k.
// For example, given [20, 303, 3, 4, 25] and k = 49, return true as 20 + 4 + 25 = 49.
object p339_sum_of_3 {

  def pairSumToK(in: Array[Int], k: Int, start: Int): Boolean = {
    var lo = start
    var hi = in.size-1
    while(lo<hi){
      if(in(lo)+in(hi)==k){
        return true
      }else if(in(lo)+in(hi)<k){
        lo+=1
      }else{
        hi-=1
      }
    }
    false
  }

  // sort the array. Then, for each entry in the array, we find whether or not there are two other elements that sum to the remainder.
// Since the array is sorted, we can write a function that solves this 2-sum sub-problem in O(n) time, by using a two-pointer technique. We maintain an invariant that we exclude the smallest number when the sum is too small, and largest number when the sum is too big. That gives us an overall time complexity of O(n2), since the time of the main function work dominates the time it takes to sort. If we assume that we can sort in-place, the space complexity is now O(1).
  def main(args: Array[String]): Unit = {
    val k = 49
    val in = Array(20, 303, 3, 4, 25).sorted
    val res = (for(i<-0 until in.size-2)yield{
      pairSumToK(in, k-in(i), i+1)
    }).exists(_==true)
  println(res)
  }

}

// def triplet_sums_to_k(arr, k):
//    def pair_sums_to_k(arr, k, start):
//        lo = start
//        hi = len(arr) - 1
//        while lo < hi:
//            if arr[lo] + arr[hi] == k:
//                return True
//            elif arr[lo] + arr[hi] < k:
//                lo += 1
//            else:
//                hi -= 1
//        return False
//
//    arr.sort()
//    for i in range(len(arr) - 2):
//        if pair_sums_to_k(arr, k - arr[i], i + 1):
//            return True
//
//    return False