package com.tryout.DailyCodingProblems.p30

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

// You are given a list of N numbers, in which each number is located at most k places away from its sorted position.
// For example, if k = 1, a given element at index 4 might end up at indices 3, 4, or 5.
//
//Come up with an algorithm that sorts this list in O(N log k) time.
object p306_nums_k_places_from_sort {
  def main(args: Array[String]): Unit = {
    val k = 1
    val in = Array(1,2,3,5,4)
    val pq = mutable.PriorityQueue[Int]()(Ordering.by(-_))
    val res = ArrayBuffer[Int]()

//    pq.enqueue(3)
//    pq.enqueue(1)
//    pq.enqueue(2)
//    println(pq.clone.dequeueAll)

    for(i<-0 until k){pq.enqueue(in(i))}

    for(i<-k until in.size){
      pq.enqueue(in(i))
      res+=pq.dequeue
    }

    pq.dequeueAll.foreach((x:Int)=>res+=x)

    res.foreach(println(_))
  }
}