package mixin

object Test {
  def main(args: Array[String]): Unit = {
    val t = new CImpl()
    t.greet()
    t.greet2
  }
}
