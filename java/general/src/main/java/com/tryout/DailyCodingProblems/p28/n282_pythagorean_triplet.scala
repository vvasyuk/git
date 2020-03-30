package com.tryout.DailyCodingProblems.p28

// Given an array of integers, determine whether it contains a Pythagorean triplet. Recall that a Pythogorean triplet (a, b, c) is defined by the equation a^2+ b^2= c^2.
object n282_pythagorean_triplet {
  //assume that a < b < c
  //let us assume that c is the last element in the array. Then the lowest possible value of a will be the first element, and the highest possible value of b will be the second-to-last element.
  // Now we repeatedly compare a + b against c, and perform the following:
  //
  //If a + b < c, move the index of a up in the list, to make our squared total higher.
  //If a + b > c, move the index of b down in the list, to make our squared total lower.
  //If a + b = c, return True, as we have found a solution.
  def main(args: Array[String]): Unit = {
    val in = Array(1,2,3,4,5,6,7,8,9)
  }
}

//    array = sorted([x ** 2 for x in array])
//
//    for c in range(len(array) - 1, 1, -1):
//        a, b = 0, c - 1
//
//        while a < b:
//            if array[a] + array[b] == array[c]:
//                return True
//            elif array[a] + array[b] < array[c]:
//                a += 1
//            else:
//                b -= 1