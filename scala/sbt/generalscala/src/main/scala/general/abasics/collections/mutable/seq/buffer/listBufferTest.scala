package general.abasics.collections.mutable.seq.buffer

import scala.collection.mutable.ListBuffer

// if you want to use a mutable list but not indexed
// prepend and append are constant
// random access is linear, if you need random access - use ArrayBuffer
object listBufferTest {
  def main(args: Array[String]): Unit = {
    // Builders
    val ab = ListBuffer[Int]();
    ab += 1;
    ab += 2;
    ab += 3;
    assert(ab == ListBuffer(1, 2, 3))
    assert((1 to 3).toBuffer == ListBuffer(1, 2, 3))
    assert((1 until 3).toBuffer == ListBuffer(1, 2))
    assert(ListBuffer.range(1, 4) == ListBuffer(1, 2, 3))

    // modify
    val ar1 = ListBuffer[Int]()
    ar1+=1; ar1+=2; assert(ar1 == ListBuffer(1,2))
    ar1(1) = 22; assert(ar1 == ListBuffer(1,22))
    val ar2 = ListBuffer(1, 2);ar2.update(1, 22);assert(ar2(1) == 22)
    // += append
    // +=: prepend
    // +=: - updates in place
    // +:  - returns new collections with update
    0 +=: ar2; assert(ar2 == ListBuffer(0, 1, 22)) // prepend (prepend)
    ar2 ++= Seq(4, 5); assert(ar2 == ListBuffer(0, 1, 22, 4, 5)) // appendAll
    ar2 ++=: ar1; assert(ar1 == ListBuffer(0, 1, 22, 4, 5, 1, 22)) // prependAll
    ar2.insert(1, 11); assert(ar2 == ListBuffer(0, 11, 1, 22, 4, 5)) // insert
    ar2 -= 11; assert(ar2 == ListBuffer(0, 1, 22, 4, 5)) // insert


    // Factory Methods
    assert(ListBuffer.fill(3)(7) == ListBuffer(7, 7, 7))
    assert(ListBuffer.tabulate(3)(n => s"hi$n") == (ListBuffer("hi0", "hi1", "hi2")))


    // Transforms
    assert(ListBuffer(1, 2, 3).map(i => i * 2) == ListBuffer(2, 4, 6))
    assert(ListBuffer(1, 2, 3).filter(i => i % 2 == 1) == ListBuffer(1, 3))
    assert(ListBuffer(1, 2, 3).take(2) == ListBuffer(1, 2))
    assert(ListBuffer(1, 2, 3).drop(2) == ListBuffer(3))
    assert(ListBuffer(1, 2, 3).slice(0, 1) == ListBuffer(1))
    assert(ListBuffer(1, 3, 3).distinct == ListBuffer(1, 3))
    assert(ListBuffer("apple", "pear").flatMap(_.toUpperCase()) == ListBuffer('A', 'P', 'P', 'L', 'E', 'P', 'E', 'A', 'R'))
    assert(ListBuffer(1, 3, 2).sorted == ListBuffer(1, 2, 3))
    assert(ListBuffer(1, 3, 2).sortWith(_ < _) == ListBuffer(1, 2, 3))
    assert(ListBuffer(1, 3, 2).sortWith(_ > _) == ListBuffer(3, 2, 1))
    assert(ListBuffer(1, 2).zip(ListBuffer(3, 4)) == ListBuffer((1, 3), (2, 4)))
    assert(ListBuffer(11, 22, 33).zipWithIndex == ListBuffer((11, 0), (22, 1), (33, 2)))

    // Queries
    assert(ListBuffer(1, 2, 3).find(_ == 2) == Some(2))
    assert(ListBuffer(1, 2, 3).exists(_ == 2) == true)

    // Aggregations
    assert(ListBuffer(1, 2, 3).mkString(",") == "1,2,3")
    assert(ListBuffer(1, 2, 3).fold(0)(_ + _) == 6) // fold can only aggregate into elements of subtype of original collections
    assert(ListBuffer(1, 2, 3).foldLeft(0)(_ + _) == 6)
    assert(ListBuffer(1, 2, 3).reduce(_ + _) == 6)
    assert(ListBuffer(1, 2, 3).groupBy(_ % 2) == Map(0 -> ListBuffer(2), 1 -> ListBuffer(1, 3)))
  }
}
