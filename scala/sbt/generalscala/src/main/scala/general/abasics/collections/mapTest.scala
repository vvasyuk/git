package general.abasics.collections

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

    val m3 = m1++Map(3 -> "three"); assert(m1==Map(1 -> "one", 2 -> "two")); assert(m3==Map(1 -> "one", 2 -> "two", 3 -> "three"))


  }
}
