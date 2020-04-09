package com.tryout.DailyCodingProblems.p29

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

// A teacher must divide a class of students into two teams to play dodgeball. Unfortunately, not all the kids get along, and several refuse to be put on the same team as that of their enemies.
//
//Given an adjacency list of students and their enemies, write an algorithm that finds a satisfactory pair of teams, or returns False if none exists.
//
//For example, given the following enemy graph you should return the teams {0, 1, 4, 5} and {2, 3}.
//
//students = {
//    0: [3],
//    1: [2],
//    2: [1, 4],
//    3: [0, 4, 5],
//    4: [2, 3],
//    5: [3]
//}
//On the other hand, given the input below, you should return False.
//
//students = {
//    0: [3],
//    1: [2],
//    2: [1, 3, 4],
//    3: [0, 2, 4, 5],
//    4: [2, 3],
//    5: [3]
//}
object p292_divide_students {

  def assign(kids: Map[Int, List[Int]], teams: Map[Int, mutable.Set[Int]], student: Int, visited: mutable.Set[Int]) = {
    val q = mutable.Queue((student,0))

    while(q.nonEmpty){
      val (kid, team) = q.dequeue()
      teams(team)+=kid

      kids(kid).foreach(e=>{
        if(teams(team).contains(e)){
          println("false")
        }else if (!visited.contains(e)){
          q.enqueue((e, 1-team))
        }
      })
      visited+=kid
    }
  }

  def makeTeams(kids: Map[Int, List[Int]]) = {
    val teams = Map(0->mutable.Set[Int](), 1->mutable.Set[Int]())
    val visited = mutable.Set[Int]()

    for(student<-kids){
      if(!visited.contains((student._1))){
        assign(kids, teams, student._1, visited)
      }
    }
    teams.foreach(x=>x._2.foreach(y=>println(x._1 + ":" +y)))
    ()
  }

  def main(args: Array[String]): Unit = {
    val in = Map(0 -> List(3), 1 -> List(2), 2 -> List(1,4), 3 -> List(0,4,5), 4 -> List(2,3), 5 -> List(3))    //  teams {0, 1, 4, 5} and {2, 3}

    makeTeams(in)
  }
}

// def make_teams(kids):
//    teams = {0: [], 1: []}
//    visited = set()
//
//    while kids:
//        start = next(iter(kids))
//        if not assign(kids, teams, start, visited):
//            return False
//
//    return teams
//
//def assign(kids, teams, start, visited):
//    queue = deque([(start, 0)])
//
//    while queue:
//        kid, team = queue.popleft() mnb
//        teams[team].append(kid)
//
//        enemies = kids.pop(kid)
//        for enemy in enemies:
//            if enemy in teams[team]:
//                return False
//            elif enemy not in visited:
//                queue.append((enemy, 1 - team))
//
//        visited.add(kid)
//
//    return True