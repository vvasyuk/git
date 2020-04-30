package com.tryout.DailyCodingProblems.p32

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

// Starting from 0 on a number line, you would like to make a series of jumps that lead to the integer N.
//
//On the ith jump, you may move exactly i places to the left or right.
//
//Find a path with the fewest number of jumps required to get from 0 to N.
object p322_jump_series_on_line {

  def main(args: Array[String]): Unit = {
    val N = 11

    jumpPath(N)
  }

  def jumpPath(N: Int): Unit = {
    var n = N
    var isMegative = false
    if (n < 0) {
      n = -n
      isMegative = true
    }
    var (k, total) = (0, 0)
    while (total < 0 || (total > 0 && (total - n) % 2 != 0)) {
      k += 1
      total += k
    }
    if (total == 0) return make_path(Range(0, k + 1).toList, isMegative)
    val nums = Range(0, k + 1).toList
    val index = (total - n) / 2
    //    return make_path(nums[:index] + [-index] + nums[index + 1:], is_negative)
  }
  def make_path(nums: List[Int], isNegative: Boolean): ArrayBuffer[Int] = {
    val path = mutable.ArrayBuffer[Int](nums.head)
    for(i<- 1 to nums.size){
      path.append(path.last + i)
    }
    if (isNegative){
      for(i<-path)yield{-1*i}
    }else{ path}
  }
}
// def make_path(nums, is_negative):
//    path = [nums[0]]
//    for i in nums[1:]:
//        path.append(path[-1] + i)
//    if is_negative:
//        return [-1 * i for i in path]
//    else:
//        return path
//
//def jump_path(n):
//    is_negative = False
//    if n < 0:
//        n = -n
//        is_negative = True
//    k = total = 0
//    while total < n or (total > n and (total - n) % 2 != 0):
//        k += 1
//        total += k
//
//    if total == n:
//        return make_path(range(k + 1), is_negative)
//
//    nums = list(range(k + 1))
//    index = (total - n) // 2
//
//    return make_path(nums[:index] + [-index] + nums[index + 1:], is_negative)