package dcp

import dcp.common.tree.MyTree
import dcp.common.tree.Node
import dcp.common.tree.EmptyNode

// A unival tree (which stands for "universal value") is a tree where all nodes under it have the same value.
//
//Given the root to a binary tree, count the number of unival subtrees.

// For example, the following tree has 5 unival subtrees:
//   0
//  / \
// 1   0
//    / \
//   1   0
//  / \
// 1   1
object q008_univalTree {
  def main(args: Array[String]): Unit = {
    val n3 = Node(3)
    val n4 = Node(4)
    val n5 = Node(1, Node(1), Node(1))
    val n6 = Node(0)
    val n1 = Node(1)
    val n2 = Node(0, n5, n6)
    val root = Node(0, n1, n2)
    println(root.beautify)
    println(univalCount(root))
  }

  def univalCount(root: MyTree): Int =
    def _helper(r: MyTree): (Int, Boolean) = r match{
      case EmptyNode => (0, true)
      case n: Node   => {
        val (leftCount, leftUnival) = _helper(n.left)
        val (rightCount, rightUnival) = _helper(n.right)
        val total = leftCount + rightCount

        val res = if leftUnival && rightUnival then
          if n.left != EmptyNode && n.value != getChildValue(n.left, n=>n.value).get then (total, false)
          else if n.right != EmptyNode && n.value != getChildValue(n.right, n=>n.value).get then (total, false)
          else (total+1, true)
        else
          (total, false)
        res
      }
    }

    _helper(root)._1

  def getChildValue(root: MyTree, f: Node=>Int): Option[Int] = root match{
    case EmptyNode => None
    case n: Node => Some(f(n))
  }
}
