package general.abasics.collections.mutable.seq.indexedSeq

import scala.collection.mutable.ArrayDeque

// An implementation of a double-ended ArrayDeque that internally uses a resizable circular buffer.
// Append, prepend, removeFirst, removeLast and random-access (indexed-lookup and indexed-replacement) take amortized constant time.
// In general, removals and insertions at i-th index are O(min(i, n-i)) and thus insertions and removals from end/beginning are fast.
object arrayDequeueTest {
  def main(args: Array[String]): Unit = {
    // Builders
    val q1 = ArrayDeque(1, 2)

    // Modify
    q1 += 3; assert(q1 == ArrayDeque(1,2,3))    // append
    0 +=: q1; assert(q1 == ArrayDeque(0,1,2,3)) //prepend
    q1 ++= Seq(4); assert(q1 == ArrayDeque(0,1,2,3,4))
    Seq(-1) ++=: q1; assert(q1 == ArrayDeque(-1,0,1,2,3,4))
    q1 --= Seq(-1,0); assert(q1 == ArrayDeque(1,2,3,4))
    val f = q1.removeHeadOption(); assert(q1 == ArrayDeque(2,3,4)); assert(f==Some(1))
    val l = q1.removeLastOption(); assert(q1 == ArrayDeque(2,3)); assert(l==Some(4))
    q1.remove(0); assert(q1 == ArrayDeque(3))
    q1.insert(0, 2); assert(q1 == ArrayDeque(2, 3))
    assert(q1(0) == 2)
    q1(0) = 1; assert(q1 == ArrayDeque(1, 3))

    // Transforms
    val q2 = ArrayDeque(1, 2)
    assert(q2.map(_ * 2) == ArrayDeque(2, 4));
    assert(q2 == ArrayDeque(1, 2))
    assert(q2.filter(_ % 2 == 1) == ArrayDeque(1))
    assert(ArrayDeque(1, 2, 3).take(2) == ArrayDeque(1, 2))
    assert(ArrayDeque(1, 2, 3).drop(2) == ArrayDeque(3))
    assert(ArrayDeque(1, 2, 3).slice(0, 2) == ArrayDeque(1, 2))
    assert(ArrayDeque(1, 2, 2, 3).distinct == ArrayDeque(1, 2, 3))
    assert(ArrayDeque("apple", "pear").flatMap(_.toUpperCase()) == ArrayDeque('A', 'P', 'P', 'L', 'E', 'P', 'E', 'A', 'R'))
    assert(ArrayDeque(1, 3, 2).sorted == ArrayDeque(1, 2, 3))
    assert(ArrayDeque(1, 3, 2).sortWith(_ < _) == ArrayDeque(1, 2, 3))
    assert(ArrayDeque(1, 3, 2).sortWith(_ > _) == ArrayDeque(3, 2, 1))
    assert(ArrayDeque(1, 2).zip(ArrayDeque(3, 4)) == ArrayDeque((1, 3), (2, 4)))
    assert(ArrayDeque(11, 22, 33).zipWithIndex == ArrayDeque((11, 0), (22, 1), (33, 2)))
    assert(ArrayDeque(1, 3, 2).collect({case i if i%2==0 => i}) == ArrayDeque(2))        // applies partial func, returning elements, fir which func is defined

    // Queries
    val q4 = ArrayDeque(1, 2, 3, 4)
    assert(q4.find(x => x == 1) == Some(1))
    assert(q4.exists(_ == 1) == true)
    assert(q4.head == 1)
    assert(q4.last == 4)
    assert(q4 == ArrayDeque(1, 2, 3, 4))

    // Aggregations
    assert(q4.mkString(",") == "1,2,3,4")
    assert(q4.count(x => x == 1) == 1)
    assert(q4.foldLeft(0)((a, b) => a + b) == 10)
    assert(q4.reduce((a, b) => a + b) == 10)
  }
}
