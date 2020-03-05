package odrski

object RNA {
  def main(args: Array[String]): Unit = {
    println("asdf")
    println(Base.fromInt(0))
  }
}
abstract class Base
case object A extends Base
case object T extends Base
case object G extends Base
case object U extends Base
object Base {
  val fromInt: Int => Base = Array(A, T, G, U)
  val xyi: Int => Int = Rna
}
object Rna {
  def apply(i: Int): Int = 0
}
