package general.abasics.monads.state

object state2Test {
  case class State(value: Int) {
    def flatMap(f: Int => State): State = {
      val newValue = f(value)
      State(newValue.value)
    }

    def map(f: Int => Int): State = State(f(value))
  }

  def main(args: Array[String]): Unit = {
    val res = for {
      a <- State(20)
      b <- State(a+15)  // manually carry a
      c <- State(b+0)   // manually carry b
    } yield c

//    val res = State(20)
//      .flatMap(a =>
//        State(a + 15)
//          .flatMap(b =>
//            State(b + 0)
//              .map(c => c)
//          )
//      )

    println("test")
    println(res)
  }
}
