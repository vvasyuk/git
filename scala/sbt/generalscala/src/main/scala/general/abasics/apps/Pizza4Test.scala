package general.abasics.apps

// functional objects
object Pizza4Test {
  def main(args: Array[String]): Unit = {
    val pizza1 = Pizza(LargeCrustSize, ThinCrustType, Seq(Cheese))
    val pizza2 = pizza1.addTopping(Pepperoni)
    val pizza3 = pizza2.updateCrustType(ThinCrustType)
    //val price = pizza3.getPrice(toppingPrices, crustSizePrices, crustTypePrices)

    val address = Address("1 Main Street", None, "Talkeetna", "AK", "99676")
    val customer = Customer("Alvin Alexander", "907-555-1212", address)
    val o1 = Order(Seq[Pizza](), customer)
    val price = MockDbOrderService.calculateOrderPrice(o1)
    println(price)
  }

  case class Pizza(crustSize: CrustSize, crustType: CrustType, toppings: Seq[Topping]){
    def addTopping(topping: Topping): Pizza = this.copy(toppings = this.toppings :+ topping)
    def removeTopping(topping: Topping): Pizza = {
      val (before, after) = this.toppings span(_ == topping)
      val newToppings = before ++ after.drop(1)
      this.copy(toppings = newToppings)
    }
    def removeAllToppings(): Pizza = this.copy(toppings = Seq())

    def updateCrustSize(cs: CrustSize): Pizza = this.copy(crustSize = cs)
    def updateCrustType(ct: CrustType): Pizza = this.copy(crustType = ct)

    def getPrice(toppingPrices: Map[Topping, Money], crustSizePrices: Map[CrustSize, Money], crustTypePrices: Map[CrustType, Money]): Money = {
      val base = 10
      val numToppings = this.toppings.size
      val price = base + 1.00 * numToppings
      price
    }
  }

  type Money = BigDecimal

  case class Order(pizzas: Seq[Pizza], custommer: Customer)
  case class Customer(name: String, phone: String, address: Address)
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

  trait PizzaDaoInterface {
    def getToppingPrices(): Map[Topping, Money]
    def getCrustSizePrices(): Map[CrustSize, Money]
    def getCrustTypePrices(): Map[CrustType, Money]
  }
  object MockPizzaDao extends PizzaDaoInterface {
    def getToppingPrices(): Map[Topping, Money] = {
      Map(
        Cheese -> BigDecimal(1),
        Pepperoni -> BigDecimal(1),
        Sausage -> BigDecimal(1),
        Mushrooms -> BigDecimal(1)
      )
    }
    def getCrustSizePrices(): Map[CrustSize, Money] = {
      Map(
        SmallCrustSize -> BigDecimal(0),
        MediumCrustSize -> BigDecimal(1),
        LargeCrustSize -> BigDecimal(2)
      )
    }
    def getCrustTypePrices(): Map[CrustType, Money] = {
      Map(
        RegularCrustType -> BigDecimal(0),
        ThickCrustType -> BigDecimal(1),
        ThinCrustType -> BigDecimal(1)
      )
    }
  }

  // ORDERSERVICE
  trait OrderServiceInterface {
    protected def database: PizzaDaoInterface
    def calculateOrderPrice(o: Order): Money
  }
  trait AbstractOrderService extends OrderServiceInterface {
    private lazy val toppingPricesMap = database.getToppingPrices()
    private lazy val crustSizePricesMap = database.getCrustSizePrices()
    private lazy val crustTypePricesMap = database.getCrustTypePrices()
    // the publicly-available service
    def calculateOrderPrice(o: Order): Money = calculateOrderPriceInternal(o, toppingPricesMap, crustSizePricesMap, crustTypePricesMap)
    private def calculateOrderPriceInternal(o: Order, toppingPrices: Map[Topping, Money], crustSizePrices: Map[CrustSize, Money], crustTypePrices: Map[CrustType, Money]): Money = {
      val pizzaPrices: Seq[Money] = for {
        pizza <- o.pizzas
      } yield {pizza.getPrice(toppingPrices, crustSizePrices, crustTypePrices)}
      pizzaPrices.sum
    }
  }
  object MockDbOrderService extends AbstractOrderService {
    val database = MockPizzaDao
  }
}
