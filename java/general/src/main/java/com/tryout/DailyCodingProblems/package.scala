package com.tryout

import scala.collection.mutable.ArrayBuffer

package object DailyCodingProblems {
  case class Node(v:Int, l:Node=null, r:Node=null)
  def getBTree(in: Array[Int]):Node={
    if (in.size!=0){
      val m = in.size/2
      val l = in.take(m)
      val r = in.takeRight(in.size-m-1)
      Node(in(m), getBTree(l), getBTree(r))
    }else{
      null
    }
  }
  def printBTree(root: Node):Unit = {
    def _getMaxDepth(root: Node, d: Int):Int = root match{
      case null => d
      case _ => {
        val l = _getMaxDepth(root.l, d+1)
        val r = _getMaxDepth(root.r, d+1)
        math.max(l,r)
      }
    }
    val maxDepth = _getMaxDepth(root, 1)-1
    val res = Array.fill(maxDepth)(ArrayBuffer[String]())

    def _buildArray(root: Node, level: Int, col: Int, res: Array[ArrayBuffer[String]]): Unit = {
      if (root==null) return
      val prefix = if (col==0) math.pow(2,level-1)-1 else math.pow(2,level)-1
      res(level-1)+=" " * prefix.toInt + root.v.toString
      _buildArray(root.l,level-1,col,res)
      _buildArray(root.r,level-1,col+1,res)
    }
    _buildArray(root,maxDepth,0,res)
    res.reverse.foreach(x=>println(x.mkString("")))
    println("")
  }

}
