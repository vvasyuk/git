package com.tryout.DailyCodingProblems.p0

// Given an array of integers, return a new array such that each element at index i of the new array is the product of all the numbers in the original array except the one at i.
// For example, if our input was [1, 2, 3, 4, 5], the expected output would be [120, 60, 40, 30, 24]. If our input was [3, 2, 1], the expected output would be [2, 3, 6].
object p2_array_of_products {
  def main(args: Array[String]): Unit = {
    val in = List(1, 2, 3, 4, 5)
    val sum = in.foldLeft(1) { (e,acc) => acc*e }
    val newList = in.map(sum/_)
    println(newList)
  }
}
