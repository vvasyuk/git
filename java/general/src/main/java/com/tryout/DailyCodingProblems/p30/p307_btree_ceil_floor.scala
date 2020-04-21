package com.tryout.DailyCodingProblems.p30

import com.tryout.DailyCodingProblems._

// Given a binary search tree, find the floor and ceiling of a given integer. The floor is the highest element
// in the tree less than or equal to an integer, while the ceiling is the lowest element in the tree greater than or equal to an integer.
//
//If either value does not exist, return None.
object p307_btree_ceil_floor {
  // floor - highest element in the tree less than or equal to an integer
  // ceiling - lowest element in the tree greater than or equal to an integer

  def main(args: Array[String]): Unit = {
    val x = getBTree(Array(1, 2, 3, 4, 7, 9, 11))
    printBTree(x)
    println(getBounds(x,5))
  }

  def getBounds(root: Node, x: Int, floor: Option[Int] = None, ceil: Option[Int] = None):(Option[Int],Option[Int]) = {
    if (root == null) {
      return (floor, ceil)
    }
    if (x == root.v) {
      return (Some(x), Some(x))
    }

    x<root.v match{
      case true => getBounds(root.l,x,floor,Some(root.v))       // we know the ceiling can be no greater than node.data
      case _ => getBounds(root.r,x,Some(root.v),ceil)           // we know the floor can be no less than node.data
    }
  }
}