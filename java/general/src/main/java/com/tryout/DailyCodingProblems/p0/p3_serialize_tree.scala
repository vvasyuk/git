package com.tryout.DailyCodingProblems.p0

import com.tryout.DailyCodingProblems._

import scala.collection.mutable

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
      Node(2),
      Node(3,Node(4,null),Node(5))
    )

    printBTree(root)
    println(serialize(root))
    val des = deserialize(serialize(root))
    printBTree(des)
  }

  def serialize(root: Node):String={
    if (root==null){
      "#"
    }else{
      s"${root.v} ${serialize(root.l)} ${serialize(root.r)}"
    }
  }

  def deserialize(str: String): Node = {
    val q = mutable.Queue(str.split(" "): _*)
    def _helper(queue: mutable.Queue[String]):Node={
      val el = queue.dequeue()
      if (el == "#"){
        null
      }else{
        Node(el.toInt, _helper(queue), _helper(queue))
      }
    }
    _helper(q)
  }
}
