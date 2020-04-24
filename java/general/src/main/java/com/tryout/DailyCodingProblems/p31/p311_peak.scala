package com.tryout.DailyCodingProblems.p31

// Given an unsorted array, in which all elements are distinct, find a "peak" element in O(log N) time.
//
//An element is considered a peak if it is greater than both its left and right neighbors. It is guaranteed that the first and last elements are lower than all others.
object p311_peak {
  // If this element is greater than both of its neighbors, we have magically found a solution, and can return it
  // Otherwise, there will be at least one neighbor with a higher value.
  def main(args: Array[String]): Unit = {
    val in = Array(1,2,1,3,5,6,4)
    println(execute(in, 0, in.size-1))
  }

  def execute(in: Array[Int], start: Int, end: Int): Int = {
    val res: Int = if(start>end){
      -1
    }else {
      val mid = start+(end-start)/2
      if(in(mid-1)<in(mid) && in(mid)>in(mid+1)){
        mid
      }else{
        val l = execute(in,start,mid-1)
        val r = execute(in,mid+1,end)
        Math.max(l,r)
      }
    }
    res
  }
}
