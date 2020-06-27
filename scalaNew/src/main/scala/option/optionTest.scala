package option

object optionTest {
  def main(args: Array[String]): Unit = {
//    val o = Option(null)
//    val o1 = Option("maybe")
//    o.foreach(println(_))
//    o1.foreach(println(_))
    optionMapTest
  }

  def optionMapTest()={
    val o = Some(1)

    val m1 = o.map(x=>x+1).map(x=>x+1)
    println(m1.get)
  }
}
