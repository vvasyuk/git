package general.abasics.collections.immutable.seq.linearSeq

import scala.collection.immutable.Queue

// Queue is implemented as a pair of Lists, one containing the in elements and the other the out elements.
// Elements are added to the in list and removed from the out list. When the out list runs dry,
// the queue is pivoted by replacing the out list by in.reverse, and in by Nil.
object queueTest {
  def main(args: Array[String]): Unit = {
    // Builders
    val q1 = Queue(1,2,3)
    assert(q1== Queue(1, 2, 3))
    Queue[Int]() // empty queue
    assert(Queue.range(1, 4) == q1)

    // Modify
    assert(q1.dequeue == (1, Queue(2,3))); assert(q1== Queue(1, 2, 3))
    assert(q1.enqueue(4) == Queue(1, 2, 3, 4)); ; assert(q1== Queue(1, 2, 3))
    q1.collect{ case x if x == 1 => x} == Queue(1)  // applies partial func, returning elements, fir which func is defined

    // Queue specific


    // Factory Methods
    assert(Queue.fill(3)(7) == Queue(7, 7, 7))
    assert(Queue.tabulate(3)(n => s"hi$n") == Queue("hi0", "hi1", "hi2"))
    assert(Queue(1, 2) ++ Queue(3, 4) == Queue(1, 2, 3, 4))

    // Transforms
    assert(Queue(1, 2, 3).map(_ * 2) == Queue(2, 4, 6))
    assert(Queue(1, 2, 3).filter(_ % 2 == 1) == Queue(1, 3))
    assert(Queue(1, 2, 3).take(2) == Queue(1, 2))
    assert(Queue(1, 2, 3).drop(2) == Queue(3))
    assert(Queue(1, 2, 3).slice(0, 2) == Queue(1, 2))
    assert(Queue(1, 2, 2, 3).distinct == Queue(1, 2, 3))
    assert(Queue("apple", "pear").flatMap(_.toUpperCase()) == Queue('A', 'P', 'P', 'L', 'E', 'P', 'E', 'A', 'R'))
    assert(Queue(1, 3, 2).sorted == Queue(1, 2, 3))
    assert(Queue(1, 3, 2).sortWith(_ < _) == Queue(1, 2, 3))
    assert(Queue(1, 3, 2).sortWith(_ > _) == Queue(3, 2, 1))
    assert(Queue(1, 2).zip(Queue(3, 4)) == Queue((1, 3), (2, 4)))
    assert(Queue(11, 22, 33).zipWithIndex == Queue((11, 0), (22, 1), (33, 2)))


    // Queries
    assert(Queue(1, 2, 3).find(_ == 2) == Some(2))
    assert(Queue(1, 2, 3).exists(_ == 2) == true)

    // Aggregations
    assert(Queue(1, 2, 3).mkString(",") == "1,2,3")
    assert(Queue(1, 2, 3).fold(0)(_ + _) == 6) // fold can only aggregate into elements of subtype of original collections
    assert(Queue(1, 2, 3).foldLeft(0)(_ + _) == 6)
    assert(Queue(1, 2, 3).reduce(_ + _) == 6)
    assert(Queue(1, 2, 3).groupBy(_ % 2) == Map(0 -> Queue(2), 1 -> Queue(1, 3)))
  }
}
