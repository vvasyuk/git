package dcp.common.tree

import dcp.common.tree.MyTree.treeToArrayOfLists

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

trait MyTree:
  def beautify: String =
    val depth = MyTree.depth(this)
    val arrPads = Array.fill(depth+1)((1,1))
    for{
      i <- (1 until depth).reverse
    } arrPads(i) = (arrPads(i+1)._1*2, arrPads(i+1)._2*2+1)
    
    val arr = treeToArrayOfLists(this)
    val res: StringBuilder = StringBuilder()
    for{
      i <- 1 until arr.size
    } res.append(" " * arrPads(i)._1 + arr(i).mkString(" " * arrPads(i)._2) + "\n")
    res.toString()

object emptyNode extends MyTree
case class Node(value: Int, left: MyTree = emptyNode, right: MyTree = emptyNode) extends MyTree

object MyTree:
  def depth(root: MyTree): Int =
    def _depthHelp(n: MyTree, level: Int): Int = n match
        case node: Node => Math.max(_depthHelp(node.left, level + 1), _depthHelp(node.right, level + 1))
        case emptyNode => level-1
    
    _depthHelp(root, 1)

  def treeToArrayOfLists(root: MyTree): Array[List[Int]] =
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

  def countNodes(root: MyTree): Int =root match
    case node: Node => 1 + countNodes(node.left) + countNodes(node.right)
    case emptyNode => 1

  def bfs(root: MyTree): List[Int] = {
    val res = ListBuffer[Int]()
    val q = mutable.Queue[MyTree]()
    q += root

    while(q.nonEmpty)
      val n = q.dequeue()
      n match
        case node: Node =>
          res.append(node.value)
          q += node.left
          q += node.right
        case _ =>
    res.toList
  }

  def deserialize(root: MyTree): String = bfs(root).mkString(",")
    