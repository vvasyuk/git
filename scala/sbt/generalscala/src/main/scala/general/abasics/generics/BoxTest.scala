package generics

object BoxTest {
  class Box[+T] (val x: T)

  def main(args: Array[String]): Unit = {
    val s = new Box[String]("a")
    val i = new Box[Int](1)

    val l = List(s,i)
    l.foreach(x=>println(x.x))
    boxChecker(s)

    val listBoxStr = List(s)
    val listBoth = i::listBoxStr
  }

  //def boxChecker(b: Box)={}     // does not wor
  def boxChecker(b: Box[Any])={
    println("box checker")
    println(b.x)
  }
}
