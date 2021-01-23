package general.abasics.collections

// immutable indexed seq collection
// can access elements very quickly (quicker then in lists)
object vectorTest {
  def main(args: Array[String]): Unit = {
    // Builders
    assert(Vector(1, 2, 3) == Vector(1, 2, 3))
    Vector[Int]() // empty vector
    assert((1 to 3).toVector == Vector(1, 2, 3))
    assert((1 until 3).toVector == Vector(1, 2))
    assert(Vector.range(1, 4) == Vector(1, 2, 3))

    // modify
    assert(Vector(1,2,3).appended(4) == Vector(1,2,3,4))
    assert(Vector(1,2,3):+4 == Vector(1,2,3,4))
    assert(Vector(1,2,3).prepended(0) == Vector(0,1,2,3))
    assert(0+:Vector(1,2,3) == Vector(0,1,2,3))
    assert(Vector(1,2,3) :++ (Vector(1,2,3)) == List(1,2,3,1,2,3))
    assert(Vector(1,2,3).updated(0,11) == Vector(11,2,3))
    val x = Vector(Some(1), None, Some(3), None); assert(x.collect{case Some(i) => i} == Vector(1, 3))  // applies partial func, returning elements, fir which func is defined

    // vector specific


    // Factory Methods
    assert(Vector.fill(3)(7) == Vector(7,7,7))
    assert(Vector.tabulate(3)(n=>s"hi$n") == Vector("hi0","hi1","hi2"))
    assert(Vector(1,2) ++ Vector(3,4) == Vector(1,2,3,4))

    // Transforms
    assert(Vector(1,2,3).map(_*2) == Vector(2,4,6))
    assert(Vector(1,2,3).filter(_%2==1) == Vector(1,3))
    assert(Vector(1,2,3).take(2) == Vector(1,2))
    assert(Vector(1,2,3).drop(2) == Vector(3))
    assert(Vector(1,2,3).slice(0,2) == Vector(1,2))
    assert(Vector(1,2,2,3).distinct == Vector(1,2,3))
    assert(Vector("apple", "pear").flatMap(_.toUpperCase()) == Vector('A', 'P', 'P', 'L', 'E', 'P', 'E', 'A', 'R'))
    assert(Vector(1,3,2).sorted == Vector(1,2,3))
    assert(Vector(1,3,2).sortWith(_ < _) == Vector(1,2,3))
    assert(Vector(1,3,2).sortWith(_ > _) == Vector(3,2,1))
    assert(Vector(1,2).zip(Vector(3,4)) == Vector((1,3), (2,4)))
    assert(Vector(11, 22, 33).zipWithIndex == Vector((11,0),(22,1),(33,2)))


    // Queries
    assert(Vector(1,2,3).find(_ == 2) == Some(2))
    assert(Vector(1,2,3).exists(_ == 2) == true)

    // Aggregations
    assert(Vector(1,2,3).mkString(",") == "1,2,3")
    assert(Vector(1,2,3).fold(0)(_+_) == 6)   // fold can only aggregate into elements of subtype of original collections
    assert(Vector(1,2,3).foldLeft(0)(_+_) == 6)
    assert(Vector(1,2,3).reduce(_ + _) == 6)
    assert(Vector(1,2,3).groupBy(_%2) == Map(0 -> Vector(2), 1 -> Vector(1,3)))
  }
}
