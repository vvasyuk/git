package com.tryout.DailyCodingProblems.p20

object n204_btree_nodes_cnt {

  def main(args: Array[String]):Unit={
    execute(Node(6, Node(5,Node(3),Node(4)),Node(9,Node(8))))
  }

  def execute(root: Node):Unit={
    var left_cnt = 0
    var right_cnt = 0
    var tmp = root

    while (tmp!=null){
      left_cnt+=1
      tmp=tmp.left
    }

    tmp=root
    while (tmp!=null){
      right_cnt+=1
      tmp=tmp.right
    }

    if (left_cnt==right_cnt){
      println((2<<left_cnt-1)-1)
    }else{
      println((2<<left_cnt-1)-2)
    }
  }
}
case class Node(value:Int, left:Node=null, right:Node=null)