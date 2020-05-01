package com.tryout.DailyCodingProblems.p32

import scala.runtime.Nothing$

// A Cartesian tree with sequence S is a binary tree defined by the following two properties:
//
//It is heap-ordered, so that each parent value is strictly less than that of its children.
//An in-order traversal of the tree produces nodes with values that correspond exactly to S.
//For example, given the sequence [3, 2, 6, 1, 9], the resulting Cartesian tree would be:
//
//      1
//    /   \
//  2       9
// / \
//3   6
//Given a sequence S, construct the corresponding Cartesian tree.
object p326_Cartesian_tree {

  def buildTree(in: Array[Int]) = {
    val n = in.size
    val (parent, left, right) = (Array.fill[Option[Int]](n)(None), Array.fill[Option[Int]](n)(None), Array.fill[Option[Int]](n)(None))

    var root = 0
    for(i<-1 until n){
      var prev = i-1

      while(in(i)<in(prev) && prev != root)
        prev = parent(prev).get

      if(in(i) < in(prev)){
        left(i) = Some(root)
        parent(root) = Some(i)
        root = i
      }else{
        if (!right(prev).isEmpty){
          parent(right(prev).get) = Some(i)
          left(i) = right(prev)
        }
        parent(i) = Some(prev)
        right(prev) = Some(i)
      }
    }
    helper(Some(root), in, left,right)
  }

  def helper(root: Option[Int], in: Array[Int], left: Array[Option[Int]], right: Array[Option[Int]]):Node = {
    if(root.isEmpty){
      return null
    }
    val x = root.get
    val node = Node(in(x), helper(left(x), in, left, right), helper(right(x), in, left, right))
    node
  }

  def main(args: Array[String]): Unit = {
    val in = Array(3, 2, 6, 1, 9)
    val tree = buildTree(in)
    print()
  }
}
case class Node(v: Int, l:Node=null, r:Node=null)