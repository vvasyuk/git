package general.abasics.collections.immutable.seq.set

object setTest {
  def main(args: Array[String]): Unit = {
    // Builders
    val s1 = Set(1, 2)
    val s11 = s1.collect { case e: Int => e.toString }

    // modify
    assert(s1 + 3 == Set(1, 2, 3))
    assert(s1 - 2 == Set(1))
    assert(s1 ++ Set(3) == Set(1, 2, 3))
    assert(s1 -- Set(2) == Set(1))

    // set specific
    val s2 = Set(2, 3)
    assert(s1.intersect(s2) == Set(2))
    assert(s1.diff(s2) == Set(1))
    assert(s1.subsetOf(Set(1, 2, 3)) == true)

    // Transforms
    assert(s1.map(_ * 2) == Set(2, 4))
    assert(s1.filter(_ % 2 == 1) == Set(1))
    assert(s1.take(2) == Set(1, 2))
    assert(s1.drop(2) == Set())
    assert(s1.slice(0, 2) == Set(1, 2))
    assert(Set("apple", "pear").flatMap(_.toUpperCase()) == Set('A', 'P', 'L', 'E', 'P', 'R'))
    assert(s1.zip(s2) == Set((1, 2), (2, 3)))
    assert(s1.zipWithIndex == Set((1, 0), (2, 1)))


    // Queries
    assert(s1.find(_ == 2) == Some(2))
    assert(s1.exists(_ == 2) == true)

    // Aggregations
    assert(s1.mkString(",") == "1,2")
    assert(s1.fold(0)(_ + _) == 3) // fold can only aggregate into elements of subtype of original collections
    assert(s1.foldLeft(0)(_ + _) == 3)
    assert(s1.reduce(_ + _) == 3)
    assert(s1.groupBy(_ % 2) == Map(0 -> Set(2), 1 -> Set(1)))
  }
}
