package general.abasics.collections.mutable

import scala.collection.mutable.{ArrayBuffer, PriorityQueue}

// mutable PriorityQueue is implemented using a heap data structure and only the method dequeue and dequeueAll
// will return the elements in their priority order.
// heap is a tree (max - when parent node key is bigger then its childs)
object priorityQueueTest {
  def main(args: Array[String]): Unit = {
    // Builders
    val q1 = PriorityQueue(3, 1, 2)(Ordering[Int].on(x=>(-x)))  // ascending
    val q2 = PriorityQueue(3, 1, 2)(Ordering[Int].reverse)      // ascending
    val q3 = PriorityQueue(3, 1, 2, 3)                             // descending
    assert(q1.clone.dequeueAll == Seq(1,2,3))
    assert(q2.clone.dequeueAll == Seq(1,2,3))
    assert(q3.clone.dequeueAll == Seq(3,3,2,1))


    // Modify
    val q11 = q1 ++ Seq(5,4); assert(q11.clone.dequeueAll == Seq(1,2,3,4,5)); assert(q1.clone.dequeueAll == Seq(1,2,3))
    q1.enqueue(4); assert(q1.clone.dequeueAll == Seq(1,2,3,4))
    assert(q1.dequeue() == 1); assert(q1.clone.dequeueAll == Seq(2,3,4))
    q2++=Seq(4,5); assert(q2.clone.dequeueAll == Seq(1,2,3,4,5))
    q2+=6; assert(q2.clone.dequeueAll == Seq(1,2,3,4,5,6))


    // Transforms
    implicit val ord = Ordering[Int].reverse
    val q4 = PriorityQueue(1, 2)
    assert(q4.map(_ * 2) == ArrayBuffer(2, 4))
    q4.mapInPlace(_ * 2); assert(q4.clone.dequeueAll == Seq(2, 4));
    assert(q4.filter(_ == 2).clone.dequeueAll == Seq(2))
    assert(PriorityQueue(1, 2, 3).take(2).clone.dequeueAll == Seq(1, 2))
    assert(PriorityQueue(1, 2, 3).drop(2).clone.dequeueAll == Seq(3))
    assert(PriorityQueue(1, 2, 3).slice(0, 2).clone.dequeueAll == Seq(1, 2))
    assert(PriorityQueue("apple", "pear").flatMap(_.toUpperCase()) == ArrayBuffer('P', 'E', 'A', 'R', 'A', 'P', 'P', 'L', 'E'))
    assert(PriorityQueue(1, 2).zip(PriorityQueue(3, 4)) == Seq((1, 3), (2, 4)))
    assert(PriorityQueue(11, 22, 33).zipWithIndex == Seq((11, 0), (22, 1), (33, 2)))

    // Queries
    val q5 = PriorityQueue(1, 2, 3, 4)
    assert(q5.find(x => x == 1) == Some(1))
    assert(q5.exists(_ == 1) == true)
    q5.collectFirst{ case x: Int => x } == Some(1)
    assert(q5.clone.dequeueAll == Seq(1, 2, 3, 4))


    // Aggregations
    assert(q5.mkString(",") == "1,2,3,4")
    assert(q5.count(x => x == 1) == 1)
    assert(q5.foldLeft(0)((a, b) => a + b) == 10)
    assert(q5.reduce((a, b) => a + b) == 10)
  }
}
