package com.tryout.DailyCodingProblems.p35

// Write a program that determines the smallest number of perfect squares that sum up to N.
//
//Here are a few examples:
//
//Given N = 4, return 1 (4)
//Given N = 17, return 2 (16 + 1)
//Given N = 18, return 2 (9 + 9)
object p350_smallest_perfect_squares {
// A more efficient implementation of this idea will be to use dynamic programming. In this version, we keep track of an array that holds the minimal number of perfect squares required to sum to each integer between 0 and N. Initially, the value for each element in our array can be equal to the index itself, since we know an upper bound can be found for any number by adding 1 repeatedly.
  //
  //For each index i of the array from 2 up to N, then, we loop through each smaller perfect square p, and see if a more optimal solution can be found by adding one to the value found at the i - pth index.

  // def min_squares(n):
  //    perfect_squares = []
  //    for i in range(1, int(n ** 0.5) + 1):
  //        if i * i <= n:
  //            perfect_squares.append(i * i)
  //
  //    min_squares = [i for i in range(n + 1)]
  //
  //    for i in range(2, n + 1):
  //        for p in perfect_squares:
  //            min_squares[i] = min(min_squares[i], 1 + min_squares[i - p])
  //
  //    return min_squares[-1]
}
