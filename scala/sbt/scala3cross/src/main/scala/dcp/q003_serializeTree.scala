package dcp

import dcp.common.tree.MyTree
import dcp.common.tree.Node
import dcp.common.tree.emptyNode

object q003_serializeTree {
  def main(args: Array[String]): Unit =
// 4*2-1
// 4*2-1
//        0
//    1       2
//  3   4   5   6
// 3 3 4 4 5 5 6 6
  
    val n3 = Node(3)
    val n4 = Node(4)
    val n5 = Node(5)
    val n6 = Node(6)
    val n1 = Node(1, n3, n4)
    val n2 = Node(2, n5, n6)
    val root = Node(0, n1, n2)
    println(root.beautify)

    

    

  def deserialize(root: MyTree): String =
    val s = StringBuilder("")
    s.toString
}

// TODO: build Array[List[Int]]
// TODO: add to string for Node
// TODO: deserialize to a string with comma separator
// TODO: serialize string into array and array into tree
