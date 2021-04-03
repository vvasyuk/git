package general.abasics.state

object state3Test {
  def main(args: Array[String]): Unit = {
//    val stateWithNewDistance: State[GolfState, Int] = for {
//      _ <- swing(20)
//      _ <- swing(15)
//      totalDistance <- swing(0)
//    } yield totalDistance
//
//    val beginningState = GolfState(0)
//    val result: (GolfState, Int) = stateWithNewDistance.run(beginningState)
//
//    println(s"GolfState: ${result._1}") //GolfState(35)
//    println(s"Total Distance: ${result._2}") //35

//    val a = swing(10)
//    val res1 = a.run(GolfState(0))
//    println(res1)

    val s2 = swing(10).flatMap(_ => swing(10))
    val res2 = s2.run(GolfState(0))
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

  case class GolfState(distance: Int)
  def swing(distance: Int): State[GolfState, Int] = State { (s: GolfState) =>
    val newAmount = s.distance + distance
    (GolfState(newAmount), newAmount)
  }
}
