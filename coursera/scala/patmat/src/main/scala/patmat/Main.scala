package patmat

import patmat.Huffman._

object Main {
  def main(args: Array[String]): Unit = {
    val sampleTree = makeCodeTree(
      makeCodeTree(Leaf('x', 1), Leaf('e', 1)),
      Leaf('t', 2)
    )
    val g = List("a", "b", "a").groupBy(x=>x)
    print("end")
  }
}
