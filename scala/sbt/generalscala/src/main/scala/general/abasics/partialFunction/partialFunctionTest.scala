package general.abasics.partialFunction

// function that will only work for a subset of possible input values
// or you want to define a series of functions that only work for a subset of input values,
// and combine those functions to completely solve a problem
object partialFunctionTest {
  def addSuffix(s: String, suffix: String):String= s+suffix
  def addPrefix(s: String, prefix: String):String= prefix+s
  def udf (f: Function[String, String]):Function[String, String] = f

  def main(args: Array[String]): Unit = {
    val divide = new PartialFunction[Int,Int] {
      override def isDefinedAt(x: Int): Boolean = x!=0
      override def apply(x: Int): Int = 42/x
    }
    if (divide.isDefinedAt(1)) divide(1)

    val divide2: PartialFunction[Int, Int] = { case d: Int if d != 0 => 42 / d }
    if (divide2.isDefinedAt(1)) divide(1)


    // example 2
    // converts 1 to "one", etc., up to 5
    val convert1to5 = new PartialFunction[Int, String] {
      val nums = Array("one", "two", "three", "four", "five")
      def apply(i: Int) = nums(i-1)
      def isDefinedAt(i: Int) = i > 0 && i < 6
    }
    val convert1to5Short: PartialFunction[Int, String] = {
      case i: Int if i > 0 && i < 6 => Array("one", "two", "three", "four", "five").apply(i-1)
    }

    // converts 6 to "six", etc., up to 10
    val convert6to10 = new PartialFunction[Int, String] {
      val nums = Array("six", "seven", "eight", "nine", "ten")
      def apply(i: Int) = nums(i-6)
      def isDefinedAt(i: Int) = i > 5 && i < 11
    }
    val convert6to10Short: PartialFunction[Int, String] = {
      case i: Int if i > 5 && i < 11 => Array("six", "seven", "eight", "nine", "ten").apply(i-6)
    }
    val handle1to10 = convert1to5 orElse convert6to10
    val handle1to10Short = convert1to5Short orElse convert6to10Short
    assert(handle1to10(3) == "three"); assert(handle1to10(8) == "eight")
    assert(handle1to10Short(3) == "three"); assert(handle1to10Short(8) == "eight")


    // andThen
    val in = "_mid_"
    val partSuffix: String=>String = addSuffix(_, "suffix")
    val partPrefix: String=>String = addPrefix(_, "prefix")
    assert(partSuffix.andThen(partPrefix)(in) == "prefix_mid_suffix")


    //pipeline
    val partSuffix2: String=>String = s => "suffix2" + s
    def partPrefix2(additionalP1: String,additionalP2: String)(s:String):String ={
      //println(s"$additionalP1 $additionalP2")
      s + "prefix2"
    }
    val x = udf(partSuffix2.andThen(partPrefix2("foo", "bar")))
    assert(x("_mid2_") == "suffix2_mid2_prefix2")

  }
}
