package inheritence

object inhTest {
  def main(args: Array[String]): Unit = {
    //AbsImpl1.start

    println("---------------------------------------")

    AbsImpl2.start
  }
}

abstract class Abs {
  val data: String// = "some data"

  def start = {
    if (true) {
      f(data)
    }
  }

  def f(d: String) = {
    println(d)
  }
}

//object AbsImpl1 extends Abs{
//  val otherData = s"$data AbsImpl1"
//  override def f(d:String) = {
//    super.f(otherData)
//  }
//
//}

object AbsImpl2 extends Abs{
  override val data = s"AbsImpl2 data"
}