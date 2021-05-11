package general.abasics.a04CompositionInheritence.test1

class UniformElement(ch: Char,override val width: Int,override val height: Int) extends Element {
  private val line = ch.toString * width
  def contents = Array.fill(height)(line)
}
