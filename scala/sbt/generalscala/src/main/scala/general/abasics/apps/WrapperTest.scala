package general.abasics.apps

object WrapperTest {
  def main(args: Array[String]): Unit = {
    println("WrapperTest")

    val wr0 = Wrapper(1)
    println(wr0)
    val wr1 = wr0.map(a=>a+1)
    println(wr0)
    println(wr1)

    // map allow one generator map expression
    assert(
      (for (i <- wr0) yield i+1)
        == Wrapper(2))

    // flatMap allows multiple generator for expressions
    assert((for{
      a <- Wrapper(1)
      b <- Wrapper(2)
      c <- Wrapper(3)
    } yield a+b+c)
    == Wrapper(6))
  }

  case class Wrapper[A](v: A){
    def map[B](f: A => B): Wrapper[B] = Wrapper(f(v))
    def flatMap[B](f: A=> Wrapper[B]): Wrapper[B] = f(v)
  }
}
