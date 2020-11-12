package partialFunction

object buildPipeline {
  def udf (f: Function[String, String]):Function[String, String] = f

  def main(args: Array[String]): Unit = {
    val in = "_mid_"

    val partSuffix: String=>String = s => "suffix" + s
    def partPrefix(additionalP1: String,additionalP2: String)(s:String):String ={
      println(s"$additionalP1 $additionalP2")
      s + "prefix"
    }

    //println(partSuffix.andThen(partPrefix)(in))

    val x = udf(partSuffix.andThen(partPrefix("foo", "bar")))
    println(x(in))

  }
}
