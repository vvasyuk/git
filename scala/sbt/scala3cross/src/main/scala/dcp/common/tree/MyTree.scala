package dcp.common.tree

import dcp.common.tree.MyTree.treeToArray

trait MyTree:
  def beautify: Int =
    val depth = MyTree.depth(this)
    val arrPads = Array.fill(depth+1)((1,1))
    for{
      i <- (1 until depth).reverse
    } arrPads(i) = (arrPads(i+1)._1*2, arrPads(i+1)._2*2+1)
    
    val arr = treeToArray(this)
    val res = StringBuilder()
    for{
      i <- 1 until arr.size
    } res.append()
    0

object emptyNode extends MyTree
case class Node(value: Int, left: MyTree = emptyNode, right: MyTree = emptyNode) extends MyTree

object MyTree:
  def depth(root: MyTree): Int =
    def _depthHelp(n: MyTree, level: Int): Int = n match
        case node: Node => Math.max(_depthHelp(node.left, level + 1), _depthHelp(node.right, level + 1))
        case emptyNode => level-1
    
    _depthHelp(root, 1)

  def treeToArray(root: MyTree): Array[List[Int]] =
    def _treeToArrayHelp(a: Array[List[Int]], n: MyTree, level: Int): Array[List[Int]] = n match
      case node: Node => {
        a(level) = node.value :: a(level)
        _treeToArrayHelp(a, node.left, level+1)
        _treeToArrayHelp(a, node.right, level+1)
        a
      }
      case emptyNode => a
    
    val depth = MyTree.depth(root)
    val arr = Array.fill(depth+1)(List[Int]())
    _treeToArrayHelp(arr, root, 1)
    for{
      i <- 1 until arr.size
    } arr(i) = arr(i).reverse
    arr
    
    
    