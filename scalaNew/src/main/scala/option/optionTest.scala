package option

object optionTest {
  def main(args: Array[String]): Unit = {
    val o = Option(null)
    val o1 = Option("maybe")
    o.foreach(println(_))
    o1.foreach(println(_))
  }
}
