package mixin.simple

object ImplA extends AbstractA {
  override val s: String = "XYI"

  def main(args: Array[String]): Unit = {
    greet()
  }
}
