package com.tryout.DailyCodingProblems.p29

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

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
//  v 5          25          |20       10
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
//  v 15                                     |
//+----+                                     |
//| 2  | ------------------------------------+
//+----+
object p294_uphill_runner_route {
// 1. create three collections
  // uphill
  // (0,List((3,15), (2,8), (1,10)))
  // downhill
  // (0,List((4,10), (3,17))), (3,List((1,12))), (4,List((3,5), (2,10)))
  // g
  //0 =  "(0,List((3,15), (2,8), (1,10)))"
  //1 =  "(1,List((3,12)))"
  //2 =  "(2,List((4,10)))"
  //3 =  "(3,List((4,5), (0,17)))"
  //4 =  "(4,List((0,10)))"
// 2. sort graph topologically using stack
  // take start node, visit all its neighbors, add it to stack, soo all its neighbors will already be on stack
  // stack
  // 0, 2, 1, 3, 4
// 3. cal distances for uphill and downhill
  // go through nodes in topological order
  // if topologocal node is in graph - calculate optimal distances to all its neighbors
  // uphill distances to 0
  //0 = 0
  //1 = 10
  //2 = 8
  //3 = 15
  //4 = 2147483647
  // downhill distances to 0
  //0 = 0
  //1 = 29
  //2 = 20
  //3 = 15
  //4 = 10

  def helper(v: Int, visited: mutable.Set[Int], g:  Map[Int,ArrayBuffer[(Int, Int)]], stack: mutable.Stack[Int]):Unit = {
    visited+=v
    for(neighbor <- g(v) if !visited.contains(neighbor._1)){
      helper(neighbor._1, visited, g, stack)
    }
    stack.push(v)
  }

  def topologicalSort(g:  Map[Int,ArrayBuffer[(Int, Int)]]): mutable.Stack[Int] = {
    val visited = mutable.Set[Int]()
    val stack = mutable.Stack[Int]()

    for(v <- g.keySet if !visited.contains(v)){
      helper(v, visited, g, stack)
    }
    stack
  }

  def getDistances(g: mutable.Map[Int, ArrayBuffer[(Int, Int)]], topo: mutable.Stack[Int]) = {

    val dist = ArrayBuffer.fill(topo.size)(Int.MaxValue)
    dist(0) = 0

    for (
      v<-topo.popAll().reverse if g.contains(v);
      neighbor<-g(v)){
//      println(s"topo: $v")
//      println(neighbor)
      if (dist(neighbor._1)>dist(v)+neighbor._2){
        dist(neighbor._1)=dist(v)+neighbor._2
      }
    }
    dist
  }

  def main(args: Array[String]): Unit = {
    val elevations = Map(0 -> 5, 1 -> 25, 2 -> 15, 3 -> 20, 4 -> 10)
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

    val uphill = mutable.Map[Int,ArrayBuffer[(Int, Int)]]()
    val downhill = mutable.Map[Int,ArrayBuffer[(Int, Int)]]()
    val g = mutable.Map[Int,ArrayBuffer[(Int, Int)]]()

    paths.toList.sorted.foreach(p =>{
      val (from, to, dist) = (p._1._1, p._1._2, p._2)
      g.getOrElseUpdate(from, ArrayBuffer[(Int, Int)]())+=((to, dist))
      if (elevations(from) < elevations(to)){
        uphill.getOrElseUpdate(from, ArrayBuffer[(Int, Int)]())+=((to, dist))
      }else{
        downhill.getOrElseUpdate(to, ArrayBuffer[(Int, Int)]())+=((from, dist))
      }
    })

    val topo = topologicalSort(g.toMap)
    val topo2 = topologicalSort(g.toMap)
//
//    //val dist = getDistances(g,topo)
//
    val uphillDist = getDistances(uphill,topo)
    val downhillDist = getDistances(downhill,topo2)

    val res = for((x,y)<-uphillDist.tail.zip(downhillDist.tail))yield{
      x.toLong+y.toLong
    }
    println("res: " + res.min)
  }
}
