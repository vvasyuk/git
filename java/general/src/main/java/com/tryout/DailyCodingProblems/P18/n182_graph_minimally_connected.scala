package com.tryout.DailyCodingProblems.P18

object n182_graph_minimally_connected {

  // 1 option to identify that graph is minimally connected is to find if it has cycles (if yes then it is not minimally connected)
  // 2 option is simplier - each vertex must have exactly one edge coming out of it (must be n-1 edges)

  def execute(g: Map[Int, List[Int]]): Unit ={
    val vertexCnt = g.size
    val edges = g.foldLeft(0)((a, l) => a + l._2.size)
    println("vertexCnt: " + vertexCnt)
    println("edges: " + edges)

    if ((vertexCnt-1)==edges/2) println("graph is minimally connected ")
  }

  def main(args: Array[String]): Unit = {
    println("graph_minimally_connected_182 started")
    val g:Map[Int, List[Int]] = Map(
      0 -> List(1),
      1 -> List(2, 0, 3),
      2 -> List(4, 1),
      3 -> List(5, 1),
      4 -> List(2),
      5 -> List(3))
//  0
//  |
//  1
// / \
//2   3
//|   |
//4   5
    execute(g)
  }
}
