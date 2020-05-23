package bounds

class C {
  type D >: List[Int]
  def foo(a : D) = a
}

object C {
  def main(args: Array[String]): Unit = {
    val x = new C { type D = Traversable[Int] }
    println(x.foo(Set(1,2,3)))

  }
}