package general.abasics.apps

object Pizza2Test {
  def main(args: Array[String]): Unit = {
    import Pizza._

    val pizza1 = Pizza(SmallCrustSize, RegularCrustType, Seq(Cheese))
    val pizza2 = updateCrustSize(pizza1, MediumCrustSize)
    val pizza3 = updateCrustSize(pizza2, MediumCrustSize)
  }

  case class Pizza(crustSize: CrustSize, crustType: CrustType, toppings: Seq[Topping])
  object Pizza{
    type Money = BigDecimal

    def addTopping(pizza: Pizza, topping: Topping): Pizza = ???
    def removeTopping(pizza: Pizza, topping: Topping): Pizza = ???
    def removeAllToppings(pizza: Pizza): Pizza = ???

    def updateCrustSize(pizza: Pizza, cs: CrustSize): Pizza = ???
    def updateCrustType(pizza: Pizza, ct: CrustType): Pizza = ???

    def calculateProce(pizza: Pizza, toppingPrices: Map[Topping, Money], crustSizeProces: Map[CrustSize, Money], crustType: Map[CrustType, Money]): Money = ???
  }

  case class Order(pizzas: Seq[Pizza], custommer: Custommer)
  case class Custommer(name: String, phone: String, address: Address)
  case class Address(street1: String, street2: Option[String], city: String, state: String, zipCode: String)

  sealed trait Topping
  case object Cheese extends Topping
  case object Pepperoni extends Topping
  case object Sausage extends Topping
  case object Mushrooms extends Topping
  case object Onions extends Topping

  sealed trait CrustSize
  case object SmallCrustSize extends CrustSize
  case object MediumCrustSize extends CrustSize
  case object LargeCrustSize extends CrustSize

  sealed trait CrustType
  case object RegularCrustType extends CrustType
  case object ThinCrustType extends CrustType
  case object ThickCrustType extends CrustType
}
