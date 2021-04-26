package general.abasics.`implicit`

object implicitTest {
  def main(args: Array[String]): Unit = {
    println("ImplicitTest")
    transaction{implicit s => f}
//   same as
//    transaction{ s1 => {
//      implicit val s = s1
//      f
//    }}
  }

  def f(implicit s1: String)={
    println("inside f:" + s1)
  }

  def transaction[T](op: String => T)={
    val trans : String = "1: one"
    op(trans)
  }
}
