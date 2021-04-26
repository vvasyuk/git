package general.abasics.monads.state

object state4Test {

  def main(args: Array[String]): Unit = {
    val initState = GolfState(0)

    val r1 = plus(2)
    val res1 = r1.run(initState)
    println(res1)

    val x = (_: Int) => plus(2)
    val res2 = x(13).run(initState)
    println(res2)
  }

  case class State[S, A](run: S => (S, A)) {
    def flatMap[B](g: A => State[S, B]): State[S, B] = State { (s0: S) =>
      val (s1, a) = run(s0)
      val res = g(a).run(s1)
      res
    }
    def map[B](f: A => B): State[S, B] = flatMap(a => State.point(f(a)))
  }
  object State {
    def point[S, A](v: A): State[S, A] = State(run = s => (s, v))
  }

  case class GolfState(amnt: Int)
  def plus(amnt: Int): State[GolfState,Int] = State{ (s:GolfState) =>
    val newAmnt = s.amnt + amnt
    (GolfState(newAmnt), newAmnt)
  }
}
