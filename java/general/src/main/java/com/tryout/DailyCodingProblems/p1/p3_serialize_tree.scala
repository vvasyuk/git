package com.tryout.DailyCodingProblems.p1

import com.tryout.DailyCodingProblems._

// Given the root to a binary tree, implement serialize(root), which serializes the tree into a string, and deserialize(s), which deserializes the string back into the tree.
//
//For example, given the following Node class
//
//class Node:
//    def __init__(self, val, left=None, right=None):
//        self.val = val
//        self.left = left
//        self.right = right
//The following test should pass:
//
//node = Node('root', Node('left', Node('left.left')), Node('right'))
//assert deserialize(serialize(node)).left.left.val == 'left.left'
object p3_serialize_tree {


  def main(args: Array[String]): Unit = {
    val root = Node(1,
      Node(2,Node(3,Node(3),Node(4)),Node(4,Node(3),Node(4))),
      Node(5,Node(6,null,Node(4)),Node(7,Node(3),Node(4)))
    )

    printBTree(root)
  }

}
