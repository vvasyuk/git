package com.tryout.DailyCodingProblems.p31

import scala.collection.mutable

// You are given a circular lock with three wheels, each of which display the numbers 0 through 9 in order. Each of these
// wheels rotate clockwise and counterclockwise.
//
//In addition, the lock has a certain number of "dead ends", meaning that if you turn the wheels to one of these combinations,
// the lock becomes stuck in that state and cannot be opened.
//
//Let us consider a "move" to be a rotation of a single wheel by one digit, in either direction. Given a lock initially set to 000,
// a target combination, and a list of dead ends, write a function that returns the minimum number of moves required to reach the
// target state, or None if this is impossible.

// TODO: make it more functional
object p313_circular_lock {
  def main(args: Array[String]): Unit = {
    println(unlock("123", "133"))
  }

  def unlock(start:String, target: String): Int ={
    val q = mutable.Queue[String]()
    val visited = mutable.Set[String]()
    var level = 0
    var found = false
    q.enqueue(start)

    while(q.nonEmpty){
      for(curr <- (q.dequeueAll(_=>true)) if (!found)){
        if (curr==target){
          found=true
          return level
        }
        for(i<-0 until curr.size){
          val tlower = (((curr(i).toString.toInt)-1)%10).toString
          val thigher = (((curr(i).toString.toInt)+1)%10).toString

          val lower = curr.updated(i,tlower(0))
          val higher = curr.updated(i,thigher(0))
          println("level: " + level + " lower:" + lower + " higher: " + higher)

          if (!visited.contains(lower)){
            visited+=lower
            q.enqueue(lower)
          }
          if (!visited.contains(higher)){
            visited+=higher
            q.enqueue(higher)
          }
        }
      }
      level+=1
    }
    level
  }

}
