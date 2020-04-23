package com.tryout.DailyCodingProblems.p31

import scala.collection.immutable.TreeSet
import scala.collection.mutable

// You are the technical director of WSPT radio, serving listeners nationwide. For simplicity's sake we can consider each listener
// to live along a horizontal line stretching from 0 (west) to 1000 (east).
//
//Given a list of N listeners, and a list of M radio towers, each placed at various locations along this line, determine what
// the minimum broadcast range would have to be in order for each listener's home to be covered.
//
//For example, suppose listeners = [1, 5, 11, 20], and towers = [4, 8, 15]. In this case the minimum range would be 5,
// since that would be required for the tower at position 15 to reach the listener at position 20.
object p314_radio_coverage {
  def main(args: Array[String]): Unit = {
    val listeners = Array(1, 5, 11, 20)
    val towers = Array(8, 4, 15, Int.MaxValue, Int.MinValue)        // res 5

    val s = mutable.TreeSet[Int](towers:_*)

    val m = (for(l<-listeners)yield{
      val a = s.rangeTo(l).last
      val b = s.rangeFrom(l).head
      val min = Math.min(Math.abs(l-a), Math.abs(l-b))
      min
    }).max

    println(m)
  }
}
