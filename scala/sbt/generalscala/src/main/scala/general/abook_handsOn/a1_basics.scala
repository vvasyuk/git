package general.abook_handsOn

object a1_basics {
  def main(args: Array[String]): Unit = {
    //tuples
    println("\n### tuples")
    val t = (1,"a", 'c')
    val (t1,t2,t3) = t; println(s"$t1$t2$t3")

    println("\n### arrays")
    val a = Array[Int](1, 2, 3, 4)
    a(0) = 11; println(a(0))

    println("\n### option")
    for (s <- Some("name")) println(s"Option $s")
    for (s <- None) println(s"Option $s")

    val os1: Option[String] = Some("name")
    val os2: Option[String] = None
    val o1 = os1.map(_.length).getOrElse(-1); println(s"length of some $o1")
    val o2 = os2.map(_.length).getOrElse(-1); println(s"length of none $o2")

    println("\n### comprehensions")
    val a1 = Array(1, 2); val a2 = Array("hello", "world")
    val flattened = for (i <- a1; s <- a2) yield s + i; println(s"flattened ${flattened.mkString(",")}")
    val flattened2 = for {
      i <- a1
      s <- a2
    } yield s + i; println(s"flattened2 ${flattened2.mkString(",")}")

  }

}
