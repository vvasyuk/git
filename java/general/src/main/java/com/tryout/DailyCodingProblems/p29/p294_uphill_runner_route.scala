package com.tryout.DailyCodingProblems.p29

import scala.collection.mutable

// A competitive runner would like to create a route that starts and ends at his house, with the condition that the route goes entirely uphill at first, and then entirely downhill.
//
//Given a dictionary of places of the form {location: elevation}, and a dictionary mapping paths between some of these locations to their corresponding distances, find the length of the shortest route satisfying the condition above. Assume the runner's home is location 0.
//
//For example, suppose you are given the following input:
//
//elevations = {0: 5, 1: 25, 2: 15, 3: 20, 4: 10}
//paths = {
//    (0, 1): 10,
//    (0, 2): 8,
//    (0, 3): 15,
//    (1, 3): 12,
//    (2, 4): 10,
//    (3, 4): 5,
//    (3, 0): 17,
//    (4, 0): 10
//}
//In this case, the shortest valid path would be 0 -> 2 -> 4 -> 0, with a distance of 28.

//        17
//  +------------------------+
//  v                        |
//+----+  10   +---+  12   +---+  5   +---+
//|    | ----> | 1 | ----> | 3 | ---> | 4 | <+
//|    |       +---+       +---+      +---+  |
//|    |        15           ^          |    |
//| 0  | --------------------+          |    |
//|    |                                |    |
//|    |  10                            |    |
//|    | <------------------------------+    | 10
//+----+                                     |
//  |                                        |
//  | 8                                      |
//  v                                        |
//+----+                                     |
//| 2  | ------------------------------------+
//+----+
object p294_uphill_runner_route {

  def helper(v: Int, visited: mutable.Set[Int], g:  Map[Int, scala.List[Int]], stack: mutable.Stack[Int]):Unit = {
    visited+=v
    for(neighbor <- g(v) if !visited.contains(neighbor)){
      helper(neighbor, visited, g, stack)
    }
    stack.append(v)
  }

  def topologicalSort(g:  Map[Int, scala.List[Int]]): mutable.Stack[Int] = {
    val visited = mutable.Set[Int]()
    val stack = mutable.Stack[Int]()

    for(v <- g.keySet if !visited.contains(v)){
      helper(v, visited, g, stack)
    }
    stack
  }

  def main(args: Array[String]): Unit = {
    val paths = Map(
      (0,1)->10,
      (0,2)->8,
      (0,3)->15,
      (1,3)->12,
      (2,4)->10,
      (3,4)->5,
      (3,0)->17,
      (4,0)->10
    )
    val g = paths.foldLeft(mutable.Map[Int,List[Int]]()){(a,b)=>{
      val from = b._1._1
      val to = b._1._2
      if(a.contains(from)){
        a.update(from, to :: a(from))
      }else{
        a.put(from,List(to))
      }
      a
    }}

    val topo = topologicalSort(g.toMap)
    println("")
  }
}
