package com.tryout.DailyCodingProblems.p1

import scala.collection.mutable

// Given a list of numbers and a number k, return whether any two numbers from the list add up to k.
// For example, given [10, 15, 3, 7] and k of 17, return true since 10 + 7 is 17.
// Bonus: Can you do this in one pass?
object p1_two_sum {

  def iter(in: List[Int], k: Int, set: Set[Int]): Boolean = in match {
    case e::rest if set.contains(k-e) => true
    case e::rest  => iter(in.tail, k, set+in.head)
    case Nil => false
  }

  def main(args: Array[String]): Unit = {
    val in  = List(10, 15, 3, 7)
    val k = 17

    val res = iter(in, k, Set[Int]())
    println(res)
  }
}
