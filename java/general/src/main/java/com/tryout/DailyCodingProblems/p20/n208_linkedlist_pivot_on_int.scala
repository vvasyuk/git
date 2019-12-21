package com.tryout.DailyCodingProblems.p20

import java.util

import scala.collection.mutable.ArrayBuffer

object n208_linkedlist_pivot_on_int {

  def execute(l: util.LinkedList[Int], k: 3): Unit = {
    val itr = l.iterator()
    val cache = ArrayBuffer[Int]()
    while(itr.hasNext()){
      val v = itr.next()
      if (v>k){
        itr.remove()
        cache+=v
      }
    }

    cache.foreach(l.add(_))
    l.forEach(println(_))
  }

  def main(args: Array[String]):Unit={
    val l:util.LinkedList[Int]  = new util.LinkedList[Int]()
    l.add(9)
    l.add(7)
    l.add(5)
    l.add(1)
    l.add(0)

    execute(l, 3)
  }
}
