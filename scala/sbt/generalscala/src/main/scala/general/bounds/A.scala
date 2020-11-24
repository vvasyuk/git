package bounds

class A {
  type B <: Traversable[Int]
  def count(b : B) = b.foldLeft(0)(_+_)
}

object A {
  def main(args: Array[String]): Unit = {
    val x = new A { type B = List[Int] }
    println(x.count(List(1,2)))
  }
}
