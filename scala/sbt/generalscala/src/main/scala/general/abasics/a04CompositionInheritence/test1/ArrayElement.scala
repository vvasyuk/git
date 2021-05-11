package general.abasics.a04CompositionInheritence.test1

//class ArrayElement(conts: Array[String]) extends Element {
//  val contents: Array[String] = conts                     // val here overrides def in abstract
//}

class ArrayElement(val contents: Array[String]) extends Element         // val defines at the same time a parameter and field with the same name (with var it can be reassignable)
