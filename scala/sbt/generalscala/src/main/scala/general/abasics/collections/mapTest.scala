package general.abasics.collections

import org.mockito.DoSomethingMacro.thrownBy

object mapTest {
  def main(args: Array[String]): Unit = {
    // Builders
    Map(1 -> "one", 2 -> "two")
    Map()

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
    assert(m1(1) == "one")
    //m1(3) // will thro an exception
    val m4 = m1.withDefaultValue("unknown")
    assert(m4(3) == "unknown")
    val m5 = m1.withDefault(x => s"$x unknown")
    assert(m5(3) == "3 unknown")

    // Transforms
    println(m1.filter(i => i._1 % 2 == 1))
    assert(m1.map(i => i._1 * 2 -> i._2) == Map(2 -> "one", 4 -> "two"))
    assert(m1.filter(i => i._1 % 2 == 1) == Map(1 -> "one"))


  }
}
