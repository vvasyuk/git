package abook_fp.a05_lazy

object a05_Strict_Lazy {
  def main(args: Array[String]): Unit = {
    if2(1 < 22, () => println("a"), () => println("b"))
    if2Next(1 < 22, println("a"), println("b"))

    val x = maybeTwice(true, { println("hi"); 1+41 })
    println(x)

    Stream(1,2,3,4).map(_ + 10).filter(_ % 2 == 0)

  }

  def if2[A](cond: Boolean, onTrue: () => A, onFalse: () => A): A =
    if (cond) onTrue() else onFalse()

  def if2Next[A](cond: Boolean, onTrue: => A, onFalse: => A): A =
    if (cond) onTrue else onFalse

  def maybeTwice(b: Boolean, i: => Int) = {
    lazy val j = i
    if (b) j+j else 0
  }
}
