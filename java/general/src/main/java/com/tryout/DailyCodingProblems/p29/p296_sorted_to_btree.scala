package com.tryout.DailyCodingProblems.p29

// Given a sorted array, convert it into a height-balanced binary search tree.
object p296_sorted_to_btree {

  def constrBTree(in: Array[Int]): Node = {
    if (in.size!=0){
      val m = in.size/2
      val l = in.take(m)
      val r = in.takeRight(in.size-m-1)
      Node(in(m), constrBTree(l), constrBTree(r))
    }else{
      null
    }


  }

  def main(args: Array[String]): Unit = {
    val in = Array(1,2,3,4,5)
//    val m = in.size/2
//    val l = in.take(m)
//    val r = in.takeRight(in.size-m-1)


    val res = constrBTree(in)

    println("x")
  }
}

case class Node(v: Int, l: Node = null, r:Node = null)
