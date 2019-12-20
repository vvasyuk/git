package com.tryout.DailyCodingProblems

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object n207_graph_bipartite {

  def execute(graph: Array[(Int, Array[Int])]): Boolean = {
    var queue = mutable.Queue[Int]()
    val colors = ArrayBuffer.fill(graph.size)(0)
    queue+=0
    colors(0)=1

    while(queue.nonEmpty){
      val current = queue.dequeue()

      graph(current)._2.foreach(v=>{
        if (colors(v)==0){
          colors(v)=(-colors(current))
          queue+=v
        }else if(colors(v)==colors(current)){
          return false
        }
      })
    }
    return true
  }

  def main(args: Array[String]):Unit={
    //graph is bipartite if its vertices can be divided into two independent sets, U and V, such that no edge connects vertices of the same set.
    // idea is to split all vertices into two colors
    // if each vertex is connected to other color then itself then its a bipartite graph

    // false
    val r = execute(Array((0 -> Array(1, 2)),
      (1 -> Array(0, 2)),
      (2 -> Array(0, 1, 3)),
      (3 -> Array(2))
    ))

    // true
    val r2 = execute(Array((0 -> Array(1, 2)),
      (1 -> Array(0)),
      (2 -> Array(0, 3)),
      (3 -> Array(2))
    ))

    println("bipartite: " + r + " " +r2)
  }
}

//  0
// / \
//1---2
//     \
//      3
