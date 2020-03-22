package monads
import Stubs._

object Main {
  def main(args: Array[String]): Unit = {
    println("start")
    // stubs
    println("findTheBasePrice:" + findTheBasePrice("13"))
    println("findStateSpecificDiscount:" + findStateSpecificDiscount("13", "04128"))
    println("findProductSpecificDiscount:" + findProductSpecificDiscount("13"))
    println("calculateTax:" + calculateTax("13", 100))

    println("PriceCalculator.calculatePrice:" + PriceCalculator.calculatePrice("13", "04128"))

  }
}
