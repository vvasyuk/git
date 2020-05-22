package com.tryout.DailyCodingProblems

object apriorityQueue {
  def main(args: Array[String]): Unit = {
    // implicit val ord: Ordering[(Any,Int)] = Ordering.by(_._2)
    // var queue = mutable.PriorityQueue[(Any,Int)]()

    // val pq = collection.mutable.PriorityQueue((0, Int.MaxValue))(Ordering[((Int,Int))].on(x => (-x._1,-x._2)))
    val pq = collection.mutable.PriorityQueue(1, 2, 5, 3, 7)(Ordering[(Int)].on(x => x))
    pq.enqueue(4)
    println(pq.clone.dequeueAll)
    println(pq.head)
    println(pq.dequeue())
    println(pq.head)
    println(pq.dequeue())
    println(pq.clone.dequeueAll)



  }
}
