package com.tryout.DailyCodingProblems.p0

import scala.collection.mutable.ArrayBuffer

// Given an array of integers, find the first missing positive integer in linear time and constant space.
// In other words, find the lowest positive integer that does not exist in the array. The array can contain duplicates and negative numbers as well.
//For example, the input [3, 4, -1, 1] should give 2. The input [1, 2, 0] should give 3.
//You can modify the input array in-place.
object p4_lowest_pos_miss {

  def execute(in: ArrayBuffer[Int]): Int = {
    val newArr = ArrayBuffer.fill[Int](in.length+1)(-1)
    in.filter(x=>x>=0&&x<=in.length).foreach(x=>newArr(x)=x)
    newArr.zipWithIndex.tail.takeWhile(x=>x._1==x._2).last._2+1
  }

  def main(args: Array[String]): Unit = {
    val in = ArrayBuffer(3, 4, -1, 1)
    println(execute(in))
  }
}

//alt
//def first_missing_positive(nums):
//    s = set(nums)
//    i = 1
//    while i in s:
//        i += 1
//    return i

//alt2
//def first_missing_positive(nums):
//    if not nums:
//        return 1
//    for i, num in enumerate(nums):
//        while i + 1 != nums[i] and 0 < nums[i] <= len(nums):
//            v = nums[i]
//            nums[i], nums[v - 1] = nums[v - 1], nums[i]
//            if nums[i] == nums[v - 1]:
//                break
//    for i, num in enumerate(nums, 1):
//        if num != i:
//            return i
//    return len(nums) + 1