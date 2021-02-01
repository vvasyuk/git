package general.abasics.collections.mutable

import scala.collection.mutable.ArrayBuffer

// mutable indexed seq collection
// use listBuffer if you prefer linear seq collection
object arrayBufferTest {
  def main(args: Array[String]): Unit = {
    // Builders
    val ab = ArrayBuffer[Int](); ab+=1; ab+=2; ab+=3; assert(ab==ArrayBuffer(1,2,3))
    val ab2 = new ArrayBuffer[Int](100000)
    assert((1 to 3).toBuffer == ArrayBuffer(1, 2, 3))
    assert((1 until 3).toBuffer == ArrayBuffer(1, 2))
    assert(ArrayBuffer.range(1, 4) == ArrayBuffer(1, 2, 3))

    // modify
    val ar1 = ArrayBuffer(1,2); ar1(1)=22; assert( ar1(1)== 22)
    val ar2 = ArrayBuffer(1,2); ar2.update(1,22); assert( ar2(1)== 22)
    ar2+=3; assert(ar2 == ArrayBuffer(1,22,3))                 // append (append)
    // +=: - updates in place
    // +:  - returns new collections with update
    0+=:ar2; assert(ar2 == ArrayBuffer(0,1,22,3))              // prepend (prepend)
    ar2++=Seq(4,5); assert(ar2 == ArrayBuffer(0,1,22,3,4,5))   // appendAll
    ar2++=:ar1; assert(ar1 == ArrayBuffer(0,1,22,3,4,5, 1,22))    // prependAll
    ar2.insert(1,11); assert(ar2 == ArrayBuffer(0,11,1,22,3,4,5))   // insert
    ar2-=11; assert(ar2 == ArrayBuffer(0,1,22,3,4,5))   // insert


    // array specific


    // Factory Methods
    assert(ArrayBuffer.fill(3)(7) == ArrayBuffer(7,7,7))
    assert(ArrayBuffer.tabulate(3)(n=>s"hi$n") == (ArrayBuffer("hi0","hi1","hi2")))


    // Transforms
    assert(ArrayBuffer(1, 2, 3).map(i => i * 2) == ArrayBuffer(2,4,6))
    assert(ArrayBuffer(1, 2, 3).filter(i => i % 2 == 1) == ArrayBuffer(1,3))
    assert(ArrayBuffer(1, 2, 3).take(2) == ArrayBuffer(1,2))
    assert(ArrayBuffer(1, 2, 3).drop(2) == ArrayBuffer(3))
    assert(ArrayBuffer(1, 2, 3).slice(0, 1)== ArrayBuffer(1))
    assert(ArrayBuffer(1, 3, 3).distinct == ArrayBuffer(1,3))
    assert(ArrayBuffer("apple", "pear").flatMap(_.toUpperCase()) == ArrayBuffer('A', 'P', 'P', 'L', 'E', 'P', 'E', 'A', 'R'))
    assert(ArrayBuffer(1,3,2).sorted == ArrayBuffer(1,2,3))
    assert(ArrayBuffer(1,3,2).sortWith(_ < _) == ArrayBuffer(1,2,3))
    assert(ArrayBuffer(1,3,2).sortWith(_ > _) == ArrayBuffer(3,2,1))
    assert(ArrayBuffer(1,2).zip(ArrayBuffer(3,4)) == ArrayBuffer((1,3), (2,4)))
    assert(ArrayBuffer(11, 22, 33).zipWithIndex == ArrayBuffer((11,0),(22,1),(33,2)))

    // Queries
    assert(ArrayBuffer(1,2,3).find(_ == 2) == Some(2))
    assert(ArrayBuffer(1,2,3).exists(_ == 2) == true)

    // Aggregations
    assert(ArrayBuffer(1,2,3).mkString(",") == "1,2,3")
    assert(ArrayBuffer(1,2,3).fold(0)(_+_) == 6)   // fold can only aggregate into elements of subtype of original collections
    assert(ArrayBuffer(1,2,3).foldLeft(0)(_+_) == 6)
    assert(ArrayBuffer(1,2,3).reduce(_ + _) == 6)
    assert(ArrayBuffer(1,2,3).groupBy(_%2) == Map(0 -> ArrayBuffer(2), 1 -> ArrayBuffer(1,3)))
  }
}
