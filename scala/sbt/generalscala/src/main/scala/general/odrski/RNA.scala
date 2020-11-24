package odrski

object RNA {
  def main(args: Array[String]): Unit = {
    val f: Int=>Int = {
      Array(1,2,3).apply(_)
    }

    println(Base.fromInt(1))
    println(Base.f1(1))
    println(Base.f2(1))
  }
}
abstract class Base
case object A extends Base
case object T extends Base
case object G extends Base
case object U extends Base
object Base {
  val fromInt: Int => Base = {
    val ar = Array(A, T, G, U)
    val res = ar.apply(_)
    res
  }
  val f1: Int => Int = {_+1}
  val f2: Int => Int = Int => 0
}
//trait test
//object test {
//  def apply(i: Int): Int=>Int = {Int=>0}
//}
