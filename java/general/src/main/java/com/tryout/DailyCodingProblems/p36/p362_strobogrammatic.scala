package com.tryout.DailyCodingProblems.p36

import scala.collection.mutable.ArrayBuffer

// A strobogrammatic number is a positive number that appears the same after being rotated 180 degrees.
// For example, 16891 is strobogrammatic.
//
// Create a program that finds all strobogrammatic numbers with N digits.
object p362_strobogrammatic {
  // We start with one key, say 1. To this we can add any of the five keys as the second digit. We continue adding in this way until our string reaches a length of half the required length.


  def backtrack(n: Int, flips: Map[Int, Int], nums: Iterable[Int], result: ArrayBuffer[ArrayBuffer[Int]], curr: ArrayBuffer[Int]): ArrayBuffer[ArrayBuffer[Int]] = {
    if(curr.size != 0 && curr.size == (n+1)/2){
      // We cannot use numbers that start with 0, and 6 or 9 cannot be in the center.
      if (curr(0)==0 || (n%2!=0 && Seq(6,9).contains(curr.last))){
        null
      }else{
        // If the number has an odd number of digits, do not flip and add the center.
        val prefix = if (n%2==0) curr else curr.init
        // Take each digit down, flip it and reverse it.
        val flippedReversed = curr.map(flips).reverse
        val newRes = prefix ++ flippedReversed
        result.append(newRes)
      }
    }else{
      for {num<-nums}{
        curr.append(num)
        backtrack(n,flips,nums,result,curr)
        curr.remove(curr.size-1)
      }
    }
    result
  }

  def main(args: Array[String]): Unit = {
    val n = 5

    val result = ArrayBuffer[ArrayBuffer[Int]]()
    val flips = Map(0->0, 1->1,6->9,8->8,9->6)
    val nums = flips.keys

    val res = backtrack(n, flips, nums, result, ArrayBuffer[Int]())
    println(res)
  }
}
