package com.tryout.DailyCodingProblems.p28

import scala.collection.mutable

// Given an undirected graph, determine if it contains a cycle.

object p280_cycle_in_undirected_graph {


  def dfs(idx: Int, pIdx:Int, g: Map[Int, List[Int]], visited: mutable.Set[Int]): Boolean = {
    visited+=idx
    var tmp = true

    val l = for(newIdx<-g(idx) if (newIdx!=pIdx && tmp))yield{
      if(!visited.contains(newIdx)){
        tmp = dfs(newIdx, idx, g, visited)
        tmp
      }else{
        false
      }
    }
    !l.exists(_==false)
  }

  def execute(g: Map[Int, List[Int]]): Unit = {
    println(dfs(0,0, g, mutable.Set[Int]()))
  }

  def main(args: Array[String]): Unit = {
    println("p280_cycle_in_undirected_graph started")
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


//def DFS(start: Vertex, g: Graph): List[Vertex] = {
//
//  def DFS0(v: Vertex, visited: List[Vertex]): List[Vertex] = {
//    if (visited.contains(v))
//      visited
//    else {
//      val neighbours:List[Vertex] = g(v) filterNot visited.contains
//      neighbours.foldLeft(v :: visited)((b,a) => DFS0(a,b))
//    }
//  }
//  DFS0(start,List()).reverse
//}