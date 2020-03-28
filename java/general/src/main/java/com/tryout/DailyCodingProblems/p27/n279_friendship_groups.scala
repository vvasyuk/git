package com.tryout.DailyCodingProblems.p27

import java.util

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer


// A classroom consists of N students, whose friendships can be represented in an adjacency list. For example, the following descibes a situation where 0 is friends with 1 and 2, 3 is friends with 6, and so on.
//
//{0: [1, 2],
// 1: [0, 5],
// 2: [0],
// 3: [6],
// 4: [],
// 5: [1],
// 6: [3]}

//Each student can be placed in a friend group, which can be defined as the transitive
// closure of that student's friendship relations. In other words, this is the smallest
// set such that no student in the group has any friends outside this group.
// For the example above, the friend groups would be {0, 1, 2, 5}, {3, 6}, {4}.
//
//Given a friendship list such as the one above, determine the number of friend groups in the class.
object n279_friendship_groups {


  def dfs(idx: Int, m: Map[Int, List[Int]], visited: mutable.Set[Int], group: mutable.Set[Int]):(mutable.Set[Int], mutable.Set[Int]) = {
    val g = mutable.Set[Int]()
    val v = mutable.Set[Int]()
    g+=idx
    v+=idx
    m(idx).filter(!group.contains(_)).foreach(e=>{
      g+=e
      val t=dfs(e, m,visited, g)
      g++=t._2
      v++=t._1
    })
    (v,g)
  }

  def main(args: Array[String]): Unit = {
    val m = Map(
      0 -> List(1,2),
      1 -> List(0,5),
      2 -> List(0),
      3 -> List(6),
      4 -> List(),
      5 -> List(1),
      6 -> List(3)
    )


    val visited = mutable.Set[Int]()
    val groups = mutable.ArrayBuffer[mutable.Set[Int]]()
    m.keySet.foreach(k=>{
      if(!visited.contains(k)){
        val t = dfs(k, m, visited ,mutable.Set[Int]())
        visited++=t._1
        groups+=t._2
      }
    })
    groups.foreach(g=>{println(g)})
  }

}
