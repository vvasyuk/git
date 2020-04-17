package com.tryout.DailyCodingProblems.p30

import scala.collection.mutable

// On election day, a voting machine writes data in the form (voter_id, candidate_id) to a text file.
// Write a program that reads this file as a stream and returns the top 3 candidates at any given time.
// If you find a voter voting more than once, report this as fraud.

object p300_voting_machine {
  def main(args: Array[String]): Unit = {
    val in = List((1,1),(2,2),(3,2),(4,3),(5,4),(6,5),(7,1),(8,2),(9,3),(9,3))
    val cnt = mutable.Map[Int,Int]()
    val uniqueVoters = mutable.Set[Int]()
    val top = mutable.SortedSet[(Int,Int)]()

    in.foldLeft((cnt,top,uniqueVoters))((a,b)=>{
      val (m, t, s) = a
      val (voter, candidate) = b
      m.put(candidate,m.getOrElseUpdate(candidate, 0)+1)
      val cnt = m.get(candidate).get
      // contain a treeset of 3 elements
      addToTop3(top, (cnt, candidate))

      if(s.contains(voter)){
        println("bad")
      }else{
        s+=voter
      }
      (m, t, s)
    })

    cnt.foreach(println(_))
    println("top")
    top.foreach(println(_))
  }

  // not working need to fix
  def addToTop3(m: mutable.SortedSet[(Int,Int)],t: (Int,Int))=m.size match{
    case 3 => {
      if(m.head._1<t._1){
        m-=m.head
        m+=((t._1,t._2))
      }
    }
    case _=> m+=((t._1,t._2))
  }
}
