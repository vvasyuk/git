package general.abasics.a04CompositionInheritence.test1

//class LineElement(s: String) extends ArrayElement(Array(s)) { // Array(s) passed to primary constructor of a parent class (like in java)
//  override def width = s.length
//  override def height = 1
//}

class LineElement(s: String) extends Element {
  val contents = Array(s)
  override def width = s.length
  override def height = 1
}