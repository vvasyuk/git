package general.abasics.a03Functions.curryingPartialFunction

object CurryingTest {
  def main(args: Array[String]): Unit = {
    val sum1 = sumCurried(1)_    // val sum1: Int => Int
    assert(sum1(1) == 2)

    val sum2 = mySum(1, _)      // val sum2: Int => Int
  }
  def sumCurried(a:Int)(b:Int) = a+b
  def mySum(a:Int, b:Int) = a+b
}
