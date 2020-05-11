package com.tryout.DailyCodingProblems.p34

//Given a binary search tree and a range [a, b] (inclusive), return the sum of the elements of the binary search tree within the range.
//
//For example, given the following tree:
//
//    5
//   / \
//  3   8
// / \ / \
//2  4 6  10
//and the range [4, 9], return 23 (5 + 4 + 6 + 8).
object p343_sum_in_bst {
  // If the current node's value is between lower and upper:
  //Return the sum_range of the left child plus the right child plus the current node's value
  //If the current node's value is outside lower and upper:
  //Then either it's lower or higher. If it's lower, then we only need to check the right child (since we know that everything left of it will be smaller. And if it's higher, then we only need to check the left child.
  //def sum_range_fast(node, lower, upper):

  // def sum_range_fast(node, lower, upper):
  //    if node is None:
  //        return 0
  //
  //    if lower <= node.val <= upper:
  //        return sum_range_fast(node.left, lower, upper) + sum_range_fast(node.right, lower, upper) + node.val
  //
  //    if node.left < lower:
  //        return sum_range_fast(node.right, lower, upper)
  //
  //    if node.right > upper:
  //        return sum_range_fast(node.left, lower, upper)
}
