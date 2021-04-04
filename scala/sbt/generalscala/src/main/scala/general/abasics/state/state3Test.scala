package general.abasics.state

object state3Test {
  def main(args: Array[String]): Unit = {
    val initState = GolfState(0)
    val s1 = swing(20)
    val a = s1.run(initState)
    println(a)

    val b = swing(10).flatMap(_ => swing(10)).run(initState)
    println(b)

    val c = swing(10)
    val c1 = c.map(a => a+1)
    val resC = c1.run(initState)
    println(resC)

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

  // State class has a run field which is a a function - S => (S,A)
  case class State[S, A](run: S => (S, A)) {
    // flatMap takes a FIP and returns new State with run that expects GolfState as input
    // makes a functions composition of
    // this.run and input FIP
    def flatMap[B](f: A => State[S, B]): State[S, B] = State { (s0: S) =>
      val (s1, a) = run(s0)
      val r1 = f(a)
      val r2 = r1.run(s1)
      r2
    }

    // map takes FIP - A=>B, and returns a new State
    // by applying FIP to return value (B)

//  def map[B](f: A => B): State[S, B] = flatMap(a => State.point(f(a)))
    def map[B](f: A => B): State[S, B] = State { (s0: S) =>
      val (s1, a) = run(s0)
      (s1, f(a))
    }
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
