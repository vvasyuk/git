package dcp.common.tree

trait MyTree
object emptyNode extends MyTree
case class Node(value: Int, left: MyTree = emptyNode, right: MyTree = emptyNode) extends MyTree
