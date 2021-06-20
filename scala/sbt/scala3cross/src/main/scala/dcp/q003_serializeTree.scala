package dcp

import dcp.common.tree.MyTree
import dcp.common.tree.Node
import dcp.common.tree.emptyNode

import scala.collection.mutable

// Given the root to a binary tree, implement serialize(root), which serializes the tree into a string, and deserialize(s), which deserializes the string back into the tree.

object q003_serializeTree
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
    val deserialized = deserialize(root)
    println(deserialized)

    val serialized = serialize(deserialized)
    println(serialized.beautify)


  def deserialize(root: MyTree): String = MyTree.deserialize(root)

  def serialize(s: String): MyTree =
    def _serializeHelp(idx: Int, arr: Array[Int]): MyTree =
      if idx <= arr.size - 1 then
        Node(arr(idx), _serializeHelp(idx * 2, arr), _serializeHelp(idx * 2 + 1, arr))
      else
        emptyNode

    val arr = ("-1," + s).split(",").map(_.toInt)
    _serializeHelp(1, arr)


