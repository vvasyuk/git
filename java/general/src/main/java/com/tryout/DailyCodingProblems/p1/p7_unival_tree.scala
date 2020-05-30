package com.tryout.DailyCodingProblems.p1

import com.tryout.DailyCodingProblems._

// A unival tree (which stands for "universal value") is a tree where all nodes under it have the same value.
//
//Given the root to a binary tree, count the number of unival subtrees.
//
//For example, the following tree has 5 unival subtrees:
//
//   0
//  / \
// 1   0
//    / \
//   1   0
//  / \
// 1   1
object p7_unival_tree {

  def main(args: Array[String]): Unit = {
    val root = Node(0,
      Node(1),
      Node(0, Node(1,Node(1),Node(1)),Node(0)))
    printBTree(root)

    println(count_unival_subtrees(root))
  }
  def count_unival_subtrees(root: Node): (Int,Boolean) = {
    if (root==null){
      (0,true)
    }else{
      val left = count_unival_subtrees(root.l)
      val right = count_unival_subtrees(root.r)
      val total = left._1+right._1

      if(left._2 && right._2){
        if(root.l!=null && root.v != root.l){(total, false)
        }else if(root.r != null && root.v != root.r){(total, false)
        }else{(total+1,true)}
      }else{
        (total,false)
      }
    }
  }
}
