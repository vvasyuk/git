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

  def helper(v: Int, visited: mutable.Set[Int], g:  Map[Int,List[(Int, Int)]], stack: mutable.Stack[Int]):Unit = {
    visited+=v
    for(neighbor <- g(v) if !visited.contains(neighbor._1)){
      helper(neighbor._1, visited, g, stack)
    }
    stack.push(v)
  }

  def topologicalSort(g:  Map[Int,List[(Int, Int)]]): mutable.Stack[Int] = {
    val visited = mutable.Set[Int]()
    val stack = mutable.Stack[Int]()

    for(v <- g.keySet if !visited.contains(v)){
      helper(v, visited, g, stack)
    }
    stack
  }

  def getDistances(g: mutable.Map[Int, List[(Int, Int)]], topo: mutable.Stack[Int]) = {

    val dist = ArrayBuffer.fill(topo.size)(Int.MaxValue)
    dist(0) = 0

    for (
      v<-topo.popAll().reverse if g.contains(v);
      neighbor<-g(v)){
      println(s"topo: $v")
      println(neighbor)
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

    val uphill = mutable.Map[Int,List[(Int, Int)]]()
    val downhill = mutable.Map[Int,List[(Int, Int)]]()
    val g = mutable.Map[Int,List[(Int, Int)]]()

    paths.toList.sorted.foreach(p =>{
      val from = p._1._1
      val to = p._1._2
      val dist = p._2
      if(g.contains(from)){
        g.update(from, (to, dist) :: g(from))
      }else{
        g.put(from,List((to, dist)))
      }
      if (elevations(from) < elevations(to)){
        if(uphill.contains(from)){
          uphill.update(from, (to, dist) :: uphill(from))
        }else{
          uphill.put(from,List((to, dist)))
        }
      }else{
        if(downhill.contains(to)){
          downhill.update(to, (from, dist) :: downhill(to))
        }else{
          downhill.put(to,List((from, dist)))
        }
      }

    })

//    val g = paths.foldLeft(mutable.Map[Int,List[(Int, Int)]]()){(a,b)=>{
//      val from = b._1._1
//      val to = b._1._2
//      val dist = b._2
//      if(a.contains(from)){
//        a.update(from, (to, dist) :: a(from))
//      }else{
//        a.put(from,List((to, dist)))
//      }
//      a
//    }}

    val topo = topologicalSort(g.toMap)
    val topo2 = topologicalSort(g.toMap)

    //val dist = getDistances(g,topo)

    val uphillDist = getDistances(uphill,topo)
    val downhillDist = getDistances(downhill,topo2)

    val res = for((x,y)<-uphillDist.zip(downhillDist))yield{
      x+y
    }
    println("res: " + res(2))
  }
}
