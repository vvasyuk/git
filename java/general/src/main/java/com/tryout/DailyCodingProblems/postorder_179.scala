package com.tryout.DailyCodingProblems

object postorder_179 {
  case class BSTNode(value: String, var left: BSTNode = null, var right: BSTNode = null)

  def reconstruct(postorder: List[String]):BSTNode={
    if(postorder.size==1) return BSTNode(postorder(0))

    val rootVal = postorder.last
    val root = BSTNode(rootVal)

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
    val leftSubTree = reconstruct(postorder.init)
    root.left = leftSubTree
    root
  }

  def main(args: Array[String]): Unit = {
    println("postorder_179 started")
  }
}
