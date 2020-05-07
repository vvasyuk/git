package com.tryout.DailyCodingProblems.p33

import scala.collection.mutable

// At a party, there is a single person who everyone knows, but who does not know anyone in return (the "celebrity").
// To help figure out who this is, you have access to an O(1) method called knows(a, b), which returns True if person a knows person b, else False.
//
//Given a list of N people and the above operation, find a way to identify the celebrity in O(N) time.
object p333_celebrity_on_party {
  // Note that for any call to knows, we can eliminate one of the two people from contention.
  // If i knows j, it is impossible for i to be the celebrity, since the celebrity does not know anyone.
  // On the other hand, if i does not know j, j cannot be the celebrity.

  def execute(in: mutable.ArrayDeque[Int]): Int = {
    while(in.size>1){
      val (a,b) = (in.removeHead(),in.removeHead())

      if(knows(a,b)){
        in.append(b)
      }else{
        in.append(a)
      }
    }
    in.removeHead()
  }

  def main(args: Array[String]): Unit = {
    val in = mutable.ArrayDeque(1,2,3,4,5,6,7,8,9,10)

    println(execute(in))
  }

  def knows(a:Int,b:Int):Boolean={
    if(b==5) true else false
  }
}
