package com.tryout.DailyCodingProblems.p27

import scala.collection.mutable.ArrayBuffer

// Given an integer N, construct all possible binary search trees with N nodes.
object n278_possible_trees {
  def makeTrees(low: Int,high: Int): ArrayBuffer[Node] = {
    val trees=new ArrayBuffer[Node]()
    if (low>high){return trees+=null}

    for(i<-low to high){
      val left = makeTrees(low, i-1)
      val right = makeTrees(i+1, high)

      for(l<-left; r<-right){
        val node = Node(i,l,r)
        trees+=node
      }
    }
    trees
  }

  def main(args: Array[String]): Unit = {
    val N = 3
    val res = makeTrees(1,N)
    println(res.size)
  }
}
case class Node (k: Int, l:Node=null, r:Node=null)