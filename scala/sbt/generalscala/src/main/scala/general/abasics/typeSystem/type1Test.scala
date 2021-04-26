package general.abasics.typeSystem

object type1Test {
  trait T[A]{
    val vT: A
    def mT = vT
  }

  class C0 extends T[String]{
    override val vT: String = "test[T]"
  }

  // parametrized method
  def myToList[A](xs: A*): List[A] = xs.toList

  // variance
  class CSuper { def msuper = println("CSuper") }
  class C extends CSuper { def m = println("C") }
  class CSub extends C { def msub = println("CSub") }

  def main(args: Array[String]): Unit = {
    val c = new C0
    val clazz = c.getClass; println(clazz)                                  // class general.abasics.typeSystem.type1Test$C
    val clazz2 = classOf[C]; println(clazz2)                                // class general.abasics.typeSystem.type1Test$C

    // parametrized method
    myToList(List(1,23,3))

    // variance
    var f: C => C = (c: C) => new C // #1   Function1(-C,+C)
    f = (c: CSuper) => new CSub     // #2   -> because first argument is contravariant -CSUPER works; because second argument is covariant - CSub works
    f = (c: CSuper) => new C        // #3
    f = (c: C) => new CSub          // #4
    //f = (c: CSub) => new CSuper     // #5: ERROR!
  }
}
