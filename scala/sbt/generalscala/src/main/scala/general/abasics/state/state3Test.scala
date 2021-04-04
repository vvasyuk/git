package general.abasics.state

object state3Test {
  def main(args: Array[String]): Unit = {
//    val stateWithNewDistance: State[GolfState, Int] = for {
//      _ <- swing(20)
//      _ <- swing(15)
//      totalDistance <- swing(0)
//    } yield totalDistance

//    val stateWithNewDistance: State[GolfState, Int] =
//      swing(20)                                         // result state - run: S => (S,B)
//        .flatMap(_ => swing(15)                         // result state which madde a composition of two functions - run: S => (S,B)
//          .flatMap(_ => swing(0)                        // result state which madde a composition of two functions - run: S => (S,B)
//              .map(totalDistance => totalDistance)      // result state
//          )
//      )

//    val beginningState = GolfState(0)
//    val result: (GolfState, Int) = stateWithNewDistance.run(beginningState)
//
//    println(s"GolfState: ${result._1}") //GolfState(35)
//    println(s"Total Distance: ${result._2}") //35

    val a = swing(10)
    val res1 = a.run(GolfState(0))
    println(res1)

    val s2 = swing(10).flatMap(_ => swing(10)).map(dist => dist)
    val res2 = s2.run(GolfState(0))
    println(res2)

    val s3 = swing(10).map(dist => dist+1)
    val res3 = s3.run(GolfState(0))
    println(res3)

  }

  // State is a case class with field "run" which is a function that takes GoldState amd returns new (Goldstate, value)
  case class State[S, A](run: S => (S, A)) {
    // flatMap creates a new State ( S => (S,A)) - composition of existing
    // this.run + input run
    def flatMap[B](g: A => State[S, B]): State[S, B] = State { (s0: S) =>
      val (s1, a) = run(s0)
      val res = g(a).run(s1)
      res
    }
    // takes FIP, applies it to input
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
