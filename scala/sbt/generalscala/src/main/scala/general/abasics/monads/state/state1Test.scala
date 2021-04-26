package general.abasics.monads.state

object state1Test {
  def main(args: Array[String]): Unit = {
    val state1 = GolfState(20)
    val state2 = nextStroke(state1, 15)
    val state3 = nextStroke(state2, 0)
    println(state3) //prints "GolfState(35)"
  }
  def nextStroke(previousState: GolfState, distanceOfNextHit: Int): GolfState = {
    GolfState(previousState.distance + distanceOfNextHit)
  }
  case class GolfState(distance: Int)
}
