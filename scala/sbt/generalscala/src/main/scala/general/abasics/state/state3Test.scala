package general.abasics.state

object state3Test {
  def main(args: Array[String]): Unit = {
    val s1 = swing(20)
    val a = s1.run(GolfState(0))
    println(a)

    val b = swing(10).flatMap(_ => swing(10)).run(GolfState(0))
    println(b)

//    val stateWithNewDistance: State[GolfState, Int] = for {
//      _ <- swing(20)
//      _ <- swing(15)
//      totalDistance <- swing(0)
//    } yield totalDistance

//    val stateWithNewDistance: State[GolfState, Int] = swing(20)
//      .flatMap(_ => swing(15)
//          .flatMap(_ => swing(0)
//              .map(totalDistance => totalDistance)
//          )
//      )

//    val beginningState = GolfState(0)
//    val result: (GolfState, Int) = stateWithNewDistance.run(beginningState)
//
//    println(s"GolfState: ${result._1}") //GolfState(35)
//    println(s"Total Distance: ${result._2}") //35
  }

  case class State[S, A](run: S => (S, A)) {
    def flatMap[B](g: A => State[S, B]): State[S, B] = State { (s0: S) =>
      println(s0)
      val (s1, a) = run(s0)
      g(a).run(s1)
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
