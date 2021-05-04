package general.abasics.a03Functions.functionalComposition

object curry {
  def curryTest: Unit ={
    def adder(a: Int)(b: Int)= a+b

    assert(adder(1)(1) == 2)

    val add2 = adder(2)_
    assert(add2(3) == 5)
  }

  def curryAnyFunctionTest: Unit ={
    def adder(a: Int, b: Int)= a+b
    val curried = (adder _).curried // Int => Int => Int
    val addTwo = curried(2)
    assert(addTwo(3) == 5)
  }

  def main(args: Array[String]): Unit = {
    curryTest
    curryAnyFunctionTest
  }
}
