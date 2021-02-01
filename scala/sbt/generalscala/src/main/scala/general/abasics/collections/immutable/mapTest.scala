package general.abasics.collections.immutable

object mapTest {
  def main(args: Array[String]): Unit = {
    // Builders
    Map(1 -> "one", 2 -> "two")
    Map()
    assert((Set("a", "d") collect Map("a" -> 1, "b" -> 2, "c" -> 3)) == Set(1))
    assert(List(1 -> "one", 2 -> "two").foldLeft(Map.empty[Int, String]) {case (map, (key, value)) => map + (key -> value)} == Map(1 -> "one", 2 -> "two"))

    // modify
    var mVar: Map[Int, String] = Map(1 -> "one", 2 -> "two")
    mVar += (3 -> "three"); assert(mVar == Map(1 -> "one", 2 -> "two", 3 -> "three"))

    val m1 = Map(1 -> "one", 2 -> "two")
    val m2 = m1+(3 -> "three"); assert(m1==Map(1 -> "one", 2 -> "two")); assert(m2==Map(1 -> "one", 2 -> "two", 3 -> "three"))
    val m22= m1+(1 -> "1st"); assert(m22==Map(1 -> "1st", 2 -> "two"))

    val m3 = m1++Map(3 -> "three"); assert(m1==Map(1 -> "one", 2 -> "two")); assert(m3==Map(1 -> "one", 2 -> "two", 3 -> "three"))
    assert(m3-3 == Map(1 -> "one", 2 -> "two"))
    assert(m3--List(2,3) == Map(1 -> "one"))

    // map specific
    assert(m1.get(1) == Some("one"))
    assert(m1.getOrElse(3,"zero") == "zero")
    assert(m1(1) == "one")
    //m1(3) // will thro an exception
    val m4 = m1.withDefaultValue("unknown")
    assert(m4(3) == "unknown")
    val m5 = m1.withDefault(x => s"$x unknown")
    assert(m5(3) == "3 unknown")
    assert(m1.keys == Set(1,2))
    assert(m1.values.toList == List("one", "two"))

    // Transforms
    assert(m1.flatMap(a => List(a._1+1 -> a._2)) == Map(2 -> "one", 3 -> "two"))
    assert(m1.map(i => i._1 * 2 -> i._2) == Map(2 -> "one", 4 -> "two"))
    assert(m1.filter(i => i._1 % 2 == 1) == Map(1 -> "one"))
    assert(m1.unzip == (List(1, 2),List("one", "two")))

    m1 foreach (x => (x._1 + "-->" + x._2))
    assert((for ((k,v) <- m1) yield(s"key: $k, value: $v")).toList == List("key: 1, value: one","key: 2, value: two"))
    assert((for (kv <- m1) yield(s"key: ${kv._1}, value: ${kv._2}")).toList == List("key: 1, value: one","key: 2, value: two"))

    // Queries
    assert(m1.find(x => x._2 == "two") == Some((2,"two")))
    assert(m1.exists(_._1 == 1) == true)

    // Aggregations
    assert(m1.mkString(",") == "1 -> one,2 -> two")
    assert(m1.count(x=>x._1 == 1) == 1)
    assert(m1.mkString(",") == "1 -> one,2 -> two")
    assert(m1.foldLeft(0)((a,b) => a+b._1 ) == 3)
    assert(m1.reduce((a,b)=>(a._1+b._1) -> (a._2+b._2)) == (3 -> "onetwo"))
  }
}
