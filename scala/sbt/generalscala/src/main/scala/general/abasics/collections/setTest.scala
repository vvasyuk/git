package general.abasics.collections

object setTest {
  def main(args: Array[String]): Unit = {
    // Builders
    val s1 = Set(1,2)

    // modify
    assert(s1 + 3 == Set(1,2,3))
    assert(s1 - 2 == Set(1))
    assert(s1 ++ Set(3) == Set(1,2,3))
    assert(s1 -- Set(2) == Set(1))

    // set specific
    val s2 = Set(2,3)
    assert(s1.intersect(s2) == Set(2))
    assert(s1.diff(s2) == Set(1))
    assert(s1.subsetOf(Set(1,2,3)) == true)
  }
}
