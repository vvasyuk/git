package general.abasics.collections.mutable.map

import scala.collection.mutable.Map

object mapMutableMap {
  def main(args: Array[String]): Unit = {
    // Builders
    val m1 = Map(1 -> "one", 2 -> "two")

    // modify
    m1(3) = "three";
    assert(m1 == Map(1 -> "one", 2 -> "two", 3 -> "three"));
    m1 -= 3
    m1 += (3 -> "three");
    assert(m1 == Map(1 -> "one", 2 -> "two", 3 -> "three"))
    m1 -= (3);
    assert(m1 == Map(1 -> "one", 2 -> "two"))
    m1 ++= Map(3 -> "three");
    assert(m1 == Map(1 -> "one", 2 -> "two", 3 -> "three"))
    m1 --= List(3);
    assert(m1 == Map(1 -> "one", 2 -> "two"))

    assert(m1.getOrElseUpdate(2, "two") == "two")
    assert(m1.getOrElseUpdate(3, "three") == "three")
    assert(m1 == Map(1 -> "one", 2 -> "two", 3 -> "three"))
    m1 -= 3
  }
}
