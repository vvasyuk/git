package com.tryout.DailyCodingProblems

object postorder_179 {
  case class N(value: String, var left: N = null, var right: N = null)

  def reconstruct(postorder: List[String]):N={
    postorder match{
      case Nil => return null
      case a::Nil => return N(a)
      case _ =>
    }

    val rootVal = postorder.last
    val root = N(rootVal)

    postorder.init.zipWithIndex.foreach{
      case (v, idx) => {
        if (v>rootVal){
          val leftSubTree = reconstruct(postorder.slice(0,idx))
          val rightSubTree = reconstruct(postorder.slice(idx,postorder.size-1))
          root.left = leftSubTree
          root.right = rightSubTree
          return root
        }
      }
    }

    val lSubTree = reconstruct(postorder.init)
    root.left = lSubTree
    return root
  }

  def main(args: Array[String]): Unit = {
    println("postorder_179 started")
    val x = reconstruct(List("2", "4", "3", "8", "7", "5"))
    println("postorder_179 ended")
  }
}