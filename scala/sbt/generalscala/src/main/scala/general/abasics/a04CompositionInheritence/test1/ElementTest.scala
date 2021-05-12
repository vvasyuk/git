package general.abasics.a04CompositionInheritence.test1
import Element.elem

object ElementTest {
  def main(args: Array[String]): Unit = {
    val a1 = elem("A1")
    val a2 = elem("A2")

    val resCol1 = a1.above(a2)
    val b2 = elem("B1")
    val resCol2 = resCol1.beside(b2)

    println(resCol2)

    // direction changes
    println("direction test")
    val direction = 3
    val d1 = (direction + 1) % 4; println(d1)
    val d2 = (d1 + 1) % 4; println(d2)
    val d3 = (d2 + 1) % 4; println(d3)
    val d4 = (d3 + 1) % 4; println(d4)
    val d5 = (d4 + 1) % 4; println(d5)
  }
}
