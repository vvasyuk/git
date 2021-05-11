package general.abasics.a04CompositionInheritence.test1

import Element.elem
object SpiralMain {
  def main(args: Array[String]) = {
    val nSides = 9
    println(spiral(nSides, 0))
  }

  val space = elem(" ")
  val corner = elem("+")
  def spiral(nEdges: Int, direction: Int): Element = {
    if (nEdges == 1)
      elem("+")
    else {
      val sp = spiral(nEdges - 1, (direction + 3) % 4)
      def verticalBar = elem('|', 1, sp.height)
      def horizontalBar = elem('-', sp.width, 1)
      if (direction == 0)                                         // bottom to up
        (corner beside horizontalBar) above (sp beside space)
      else if (direction == 1)                                    // right to left
        (sp above space) beside (corner above verticalBar)
      else if (direction == 2)                                    // top to bottom
        (space beside sp) above (horizontalBar beside corner)
      else                                                        // right to left
        (verticalBar above corner) beside (space above sp)
    }
  }
}
