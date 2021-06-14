package dcp

import dcp.common.tree.MyTree
import dcp.common.tree.Node
import dcp.common.tree.emptyNode

object q003_serializeTree {
  def main(args: Array[String]): Unit =

    val n3 = Node(3)
    val n4 = Node(4)
    val n5 = Node(5)
    val n6 = Node(6)
    val n1 = Node(1, n3, n4)
    val n2 = Node(2, n5, n6)
    val root = Node(0, n1, n2)
    println(root)

  def deserialize(root: MyTree): String =
    val s = StringBuilder("")
    s.toString
}

