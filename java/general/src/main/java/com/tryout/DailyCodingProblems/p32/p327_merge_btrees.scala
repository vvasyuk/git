package com.tryout.DailyCodingProblems.p32

import com.tryout.DailyCodingProblems._

// Write a program to merge two binary trees. Each node in the new tree should hold a value equal to the sum of the values of the corresponding nodes of the input trees.
//
//If only one input tree has a node in a given position, the corresponding node in the new tree should match that input node.
object p327_merge_btrees {

  def merge(a: Node, b: Node):Node ={
    if(a==null && b==null) {
      null
    }else if(a==null){
      b
    }else if (b==null) {
      a
    }else {
      Node(a.v+b.v, merge(a.l,b.l), merge(a.r,b.r))
    }
  }


  def main(args: Array[String]): Unit = {
    val a = getBTree(Array(5,2,1,3))
    val b = getBTree(Array(30,10))

    printBTree(a)
    printBTree(b)

    val c = merge(a,b)
    printBTree(c)
  }
}

