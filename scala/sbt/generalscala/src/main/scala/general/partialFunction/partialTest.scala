package partialFunction

object partialTest {
  def main(args: Array[String]): Unit = {
    val in = "_mid_"

    val partSuffix: String=>String = addSuffix(_, "suffix")
    val partPrefix: String=>String = addPrefix(_, "prefix")

    println(partSuffix.andThen(partPrefix)(in))
  }

  def addSuffix(s: String, suffix: String):String= s+suffix
  def addPrefix(s: String, prefix: String):String= prefix+s
}
