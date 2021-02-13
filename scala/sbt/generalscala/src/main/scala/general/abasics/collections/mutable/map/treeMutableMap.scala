package general.abasics.collections.mutable.map

import scala.collection.mutable.TreeMap

// A mutable sorted map implemented using a mutable red-black tree as underlying data structure.
object treeMutableMap {
  def main(args: Array[String]): Unit = {
    // Builders
    val m1 = TreeMap(2 -> "two", 1 -> "one")

    // modify
    m1(3) = "three"; assert(m1 == TreeMap(1 -> "one", 2 -> "two", 3 -> "three"));
    m1 -= 3; m1 += (3 -> "three"); assert(m1 == TreeMap(1 -> "one", 2 -> "two", 3 -> "three"))
    m1 -= (3); assert(m1 == TreeMap(1 -> "one", 2 -> "two"))
    m1 ++= TreeMap(3 -> "three"); assert(m1 == TreeMap(1 -> "one", 2 -> "two", 3 -> "three"))
    m1 --= List(3); assert(m1 == TreeMap(1 -> "one", 2 -> "two"))

    assert(m1.getOrElseUpdate(2, "two") == "two")
    assert(m1.getOrElseUpdate(3, "three") == "three")
    assert(m1 == TreeMap(1 -> "one", 2 -> "two", 3 -> "three"))
    m1 -= 3

    // map specific
    val m22 = TreeMap(1 -> "one", 2 -> "two")
    assert(m22.get(1) == Some("one"))
    assert(m22.getOrElse(3, "zero") == "zero")
    assert(m22(1) == "one")
    //m22(3) // will thro an exception
    val m4 = m22.withDefaultValue("unknown")
    assert(m4(3) == "unknown")
    val m5 = m22.withDefault(x => s"$x unknown")
    assert(m5(3) == "3 unknown")
    assert(m22.keys == Set(1, 2))
    assert(m22.values.toList == List("one", "two"))

    // Transforms
    val m3 = TreeMap(1 -> "one", 2 -> "two")
    assert(m3.flatMap(a => List(a._1 + 1 -> a._2)) == TreeMap(2 -> "one", 3 -> "two"))
    assert(m3.map(i => i._1 * 2 -> i._2) == TreeMap(2 -> "one", 4 -> "two"))
    assert(m3.filter(i => i._1 % 2 == 1) == TreeMap(1 -> "one"))
    assert(m3.unzip == (List(1, 2), List("one", "two")))

    m3 foreach (x => (x._1 + "-->" + x._2))
    assert((for ((k, v) <- m3) yield (s"key: $k, value: $v")).toList == List("key: 1, value: one", "key: 2, value: two"))
    assert((for (kv <- m3) yield (s"key: ${kv._1}, value: ${kv._2}")).toList == List("key: 1, value: one", "key: 2, value: two"))

    // Queries
    assert(m3.find(x => x._2 == "two") == Some((2, "two")))
    assert(m3.exists(_._1 == 1) == true)

    // Aggregations
    assert(m3.mkString(",") == "1 -> one,2 -> two")
    assert(m3.count(x => x._1 == 1) == 1)
    assert(m3.mkString(",") == "1 -> one,2 -> two")
    assert(m3.foldLeft(0)((a, b) => a + b._1) == 3)
    assert(m3.reduce((a, b) => (a._1 + b._1) -> (a._2 + b._2)) == (3 -> "onetwo"))
  }
}
