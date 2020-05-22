package com.tryout.DailyCodingProblems.p36

// Describe an algorithm to compute the longest increasing subsequence of an array of numbers in O(n log n) time.
object p364_longest_increasing_subsequence {
  // [10, 9, 2, 5, 3, 7, 101, 18]
  //Our sorted list would look like this:
  //[10]
  //[9]
  //[2]
  //[2, 5]
  //[2, 3]
  //[2, 3, 7]
  //[2, 3, 7, 101]
  //[2, 3, 7, 18]
  def main(args: Array[String]): Unit = {
    val in = List(10, 9, 2, 5, 3, 7, 101, 18)
    val pq = collection.mutable.PriorityQueue[Int]()(Ordering[(Int)].on(x => x))
    for{ i <- in } {
      if (pq.headOption.getOrElse(0) < i) {
        pq.enqueue(i)
      } else {
        pq.dequeue()
        pq.enqueue(i)
      }
    }
    println(pq.clone.dequeueAll.reverse)
  }
}
