package com.tryout.DailyCodingProblems.p19

object n196_subtree_max {
  val m = scala.collection.mutable.Map[Int,Int]()

  def main(args: Array[String]):Unit={
    execute(Node(5, Node(2), Node(-5)))
    m.foreach(x=> println(x._1 + " : " + x._2))
  }

  def execute(root: Node):Int={
    root match{
      case null => 0
      case _ => {
        val sum = root.value + execute(root.left) + execute(root.right)
        m.get(sum) match{
          case Some(e) => m+=((sum, e+1))
          case None => m.+=((sum, 1))
        }
        sum
      }
    }
  }
}
case class Node(value:Int, left:Node=null, right:Node=null)