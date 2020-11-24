package mixin.simple

abstract class AbstractA {
  val s: String
  def greet() = println(s"Hi $s")
}
