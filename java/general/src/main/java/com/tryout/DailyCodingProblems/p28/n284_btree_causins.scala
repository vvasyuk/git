package com.tryout.DailyCodingProblems.p28

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

// Two nodes in a binary tree can be called cousins if they are on the same level of the tree but have different parents. For example, in the following diagram 4 and 6 are cousins.
//
//    1
//   / \
//  2   3
// / \   \
//4   5   6
//Given a binary tree and a particular node, find all cousins of that node.
object n284_btree_causins {

  def rec(root: Node, level: Int, parent: Int, pyramid: mutable.Map[Int,ArrayBuffer[Int]], vToParent: mutable.Map[Int,(Int,Int)]): Unit = {
    root match {
      case null => return
      case _ => {
        pyramid.getOrElseUpdate(level,ArrayBuffer())+=root.v
        vToParent.put(root.v, (parent,level))
        rec(root.l,level+1, root.v, pyramid,vToParent)
        rec(root.r,level+1, root.v, pyramid,vToParent)
      }
    }

  }

  def main(args: Array[String]): Unit = {
    val x = 4
    val root = Node(1,
      Node(2,Node(4),Node(5)),
      Node(3,null,Node(6)))

    val level = mutable.Map[Int,ArrayBuffer[Int]]()
    val vToParent = mutable.Map[Int,(Int,Int)]()
    rec(root,1, -1, level,vToParent)

    println("level of x is: " + vToParent(x)._2)
    println("all causins at level " +vToParent(x)._2 + ":")
    level(vToParent(x)._2)
      .filter(a=>vToParent(a)._1!=vToParent(x)._1)
      .foreach(println(_))
  }
}
case class Node(v:Int, l:Node=null,r:Node=null)