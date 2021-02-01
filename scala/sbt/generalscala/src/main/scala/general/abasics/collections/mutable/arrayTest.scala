package general.abasics.collections.mutable

// scala array is mutable, elements can change, but size of array cannot change
// if you need mutable indexed sequence whos size can change - you need ArrayBuffer
object arrayTest {
  def main(args: Array[String]): Unit = {
    // Builders
    val b = Array.newBuilder[Int]
    b += 1; b += 2; b += 3
    assert(b.result().sameElements(Array(1,2,3)))
    assert(b.result().sameElements(Array(1,2,3)))
    assert(new Array[Int](3).size==3)

    // modify
    val ar1 = Array(1,2,3); ar1(1)=22; assert( ar1(1)== 22)
    val ar2 = Array(1,2,3); ar2.update(1,22); assert( ar2(1)== 22)
    assert((ar2:+4).sameElements(Array(1,22,3,4)))  // append
    assert((0+:ar2).sameElements(Array(0,1,22,3)))  // prepend
    assert((ar2:++ar1).sameElements(Array(1,22,3,1,22,3)))
    assert((ar2++:ar1).sameElements(Array(1,22,3,1,22,3)))
    ar2.appended()
    // array specific
    assert(Array(1,2,3)(1) == 2)
    import Array._
    assert(concat(Array(1,2), Array(3,4)).sameElements(Array(1,2,3,4)))
    val arr3 = new Array[Int](3); arr3(0)=1;arr3(1)=2;arr3(2)=3; assert(arr3.sameElements(Array(1,2,3)))
    val multiDim = Array.ofDim[Int](3,3); for(r<- 0 until 3; c<- 0 until 3) multiDim(r)(c)=r*3+c+1; assert(multiDim(2)(2)==9)


    // Factory Methods
    assert(Array.fill(3)(7).sameElements(Array(7,7,7)))
    assert(Array.tabulate(3)(n=>s"hi$n").sameElements(Array("hi0","hi1","hi2")))


    // Transforms
    assert(Array(1, 2, 3).map(i => i * 2).sameElements(Array(2,4,6)))
    assert(Array(1, 2, 3).filter(i => i % 2 == 1).sameElements(Array(1,3)))
    assert(Array(1, 2, 3).take(2).sameElements(Array(1,2)))
    assert(Array(1, 2, 3).drop(2).sameElements(Array(3)))
    assert(Array(1, 2, 3).slice(0, 1).sameElements(Array(1)))
    assert(Array(1, 3, 3).distinct.sameElements(Array(1,3)))

    // Queries
    assert(Array(1,2,3).find(_ == 2) == Some(2))
    assert(Array(1,2,3).exists(_ == 2) == true)

    // Aggregations
    assert(Array(1,2,3).mkString(",") == "1,2,3")
    assert(Array(1,2,3).foldLeft(0)(_+_) == 6)
    assert(Array(1,2,3).groupBy(_%2).view.mapValues(x=>x.mkString(",")).toMap == Map(0 -> Array(2).mkString(","), 1 -> Array(1,3).mkString(",")))
  }
}
