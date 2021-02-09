package general.abasics.collections.mutable.seq.indexedSeq

import scala.collection.mutable.ArraySeq

// A class for polymorphic arrays of elements that's represented internally by an array of objects.
// This means that elements of primitive types are boxed.

// ArraySeq stores its data in a plain old Java array, but it no longer stores arrays of primitives; everything is an array of objects.
// This means that primitives get boxed on the way in. That's actually convenient if you want to use the primitives many times; since you've got boxed
// copies stored, you only have to unbox them, not box and unbox them on every generic operation.
object arraySeqTest {
  def main(args: Array[String]): Unit = {
    // Builders
    val as = ArraySeq[Int](1,2,3);
    assert(as == ArraySeq(1, 2, 3))
    val as2 = ArraySeq[Int](10)
    as2(0) = 1; assert(as2 == ArraySeq(1))


    // modify
    val ar1 = ArraySeq(1, 2); ar1(1) = 22; assert(ar1(1) == 22); assert(ar1 == ArraySeq(1, 22))
    val ar2 = ArraySeq(1, 2); ar2.update(1, 22);assert(ar2(1) == 22)    // update
    ar2(0) = 11; assert(ar2 == ArraySeq(11, 22))
    // +:  - returns new collections with update
    assert(ar2 :+ 33 == ArraySeq(11,22,33)); assert(ar2 == ArraySeq(11, 22))


    // Factory Methods
    assert(ArraySeq.fill(3)(7) == ArraySeq(7, 7, 7))
    assert(ArraySeq.tabulate(3)(n => s"hi$n") == (ArraySeq("hi0", "hi1", "hi2")))


    // Transforms
    assert(ArraySeq(1, 2, 3).map(i => i * 2) == ArraySeq(2, 4, 6))
    assert(ArraySeq(1, 2, 3).filter(i => i % 2 == 1) == ArraySeq(1, 3))
    assert(ArraySeq(1, 2, 3).take(2) == ArraySeq(1, 2))
    assert(ArraySeq(1, 2, 3).drop(2) == ArraySeq(3))
    assert(ArraySeq(1, 2, 3).slice(0, 1) == ArraySeq(1))
    assert(ArraySeq(1, 3, 3).distinct == ArraySeq(1, 3))
    assert(ArraySeq("apple", "pear").flatMap(_.toUpperCase()) == ArraySeq('A', 'P', 'P', 'L', 'E', 'P', 'E', 'A', 'R'))
    assert(ArraySeq(1, 3, 2).sorted == ArraySeq(1, 2, 3))
    assert(ArraySeq(1, 3, 2).sortWith(_ < _) == ArraySeq(1, 2, 3))
    assert(ArraySeq(1, 3, 2).sortWith(_ > _) == ArraySeq(3, 2, 1))
    assert(ArraySeq(1, 2).zip(ArraySeq(3, 4)) == ArraySeq((1, 3), (2, 4)))
    assert(ArraySeq(11, 22, 33).zipWithIndex == ArraySeq((11, 0), (22, 1), (33, 2)))
    assert(ArraySeq(1, 3, 2).collect({case i if i%2==0 => i}) == ArraySeq(2))        // applies partial func, returning elements, fir which func is defined
    val x = ArraySeq(11, 22, 33).lift; assert(x(0) == Some(11))
    val pf = ArraySeq(11, 22, 33).andThen(x => x+1); assert(pf(0) == 12)
    //ArraySeq(11, 22, 33).compose(s => 1)  // (f andThen g)(x) = g(f(x))


    // Queries
    assert(ArraySeq(1, 2, 3).find(_ == 2) == Some(2))
    assert(ArraySeq(1, 2, 3).exists(_ == 2) == true)

    // Aggregations
    assert(ArraySeq(1, 2, 3).mkString(",") == "1,2,3")
    assert(ArraySeq(1, 2, 3).fold(0)(_ + _) == 6) // fold can only aggregate into elements of subtype of original collections
    assert(ArraySeq(1, 2, 3).foldLeft(0)(_ + _) == 6)
    assert(ArraySeq(1, 2, 3).reduce(_ + _) == 6)
    assert(ArraySeq(1, 2, 3).groupBy(_ % 2) == Map(0 -> ArraySeq(2), 1 -> ArraySeq(1, 3)))
  }
}
