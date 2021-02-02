package general.abasics.collections.immutable.seq.linearSeq

object listTest {
  def main(args: Array[String]): Unit = {
    // Builders
    val empty = List() // or Nil
    val l = List(1, 2, 3)
    val l1 = 1 :: 2 :: 3 :: Nil
    assert(l == l1)

    // modify
    assert(List(1, 2, 3).appended(4) == List(1, 2, 3, 4))
    assert(List(1, 2, 3) :+ 4 == List(1, 2, 3, 4))
    assert(0 :: List(1, 2, 3) == List(0, 1, 2, 3))
    assert(List(1, 2, 3).prepended(0) == List(0, 1, 2, 3))
    assert(List(1, 2, 3).:::(List(1, 2, 3)) == List(1, 2, 3, 1, 2, 3))
    assert(List(1, 2, 3).updated(0, 11) == List(11, 2, 3))

    // list specific
    assert(List(1, 2, 3).head == 1)
    assert(List(1, 2, 3).tail == List(2, 3))


    // Factory Methods
    assert(List.fill(3)(7) == List(7, 7, 7))
    assert(List.tabulate(3)(n => s"hi$n") == List("hi0", "hi1", "hi2"))
    assert(List(1, 2) ++ List(3, 4) == List(1, 2, 3, 4))

    // Transforms
    assert(List(1, 2, 3).map(_ * 2) == List(2, 4, 6))
    assert(List(1, 2, 3).filter(_ % 2 == 1) == List(1, 3))
    assert(List(1, 2, 3).take(2) == List(1, 2))
    assert(List(1, 2, 3).drop(2) == List(3))
    assert(List(1, 2, 3).slice(0, 2) == List(1, 2))
    assert(List(1, 2, 2, 3).distinct == List(1, 2, 3))

    // Queries
    assert(List(1, 2, 3).find(_ == 2) == Some(2))
    assert(List(1, 2, 3).exists(_ == 2) == true)

    // Aggregations
    assert(List(1, 2, 3).mkString(",") == "1,2,3")
    assert(List(1, 2, 3).foldLeft(0)(_ + _) == 6)
    assert(List(1, 2, 3).groupBy(_ % 2) == Map(0 -> List(2), 1 -> List(1, 3)))
  }
}
