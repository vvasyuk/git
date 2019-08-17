package coursera.w3

object IntSetTest {
  def main(args: Array[String]): Unit = {
    val t1 = new NonEmpty(3, Empty, Empty)
    //val t1 = new NonEmpty(3, Empty, new NonEmpty(2, Empty, Empty))
    //val t1 = new NonEmpty(5, new NonEmpty(2, Empty, Empty), new NonEmpty(7, Empty, Empty))
    //val t2 = t1 incl 4		// IntSet = {.3{.4.}}
    val t3 = new NonEmpty(4, new NonEmpty(1, Empty, Empty), Empty)

    val t4 = t3 union t1

    println(t1)
    println(t3)
    //val t5 = t3.incl(2)
    //println("old:" + t3 + " new:" + t5)
    println(t4)

    println(t4.mostRetweeted)
  }
}
