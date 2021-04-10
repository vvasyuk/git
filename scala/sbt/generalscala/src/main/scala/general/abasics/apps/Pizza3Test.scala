package general.abasics.apps

object Pizza3Test {
  def main(args: Array[String]): Unit = {
    object PizzaService extends PizzaService
    import PizzaService._
    val address = Address("1 Main Street", None, "Talkeetna", "AK", "99676")
    val customer = Customer("Alvin Alexander", "907-555-1212", address)
    val o1 = Order(Seq[Pizza](), customer)
    val p1 = Pizza(MediumCrustSize, RegularCrustType, Seq(Cheese))
    val newPizzas = o1.pizzas :+ p1
    val o2 = o1.copy(pizzas = newPizzas)
    val p2 = Pizza(MediumCrustSize,RegularCrustType, Seq(Cheese))
    val p2a = addTopping(p2, Pepperoni)
    val p2b = addTopping(p2a, Mushrooms)
    val p2c = updateCrustType(p2b, ThickCrustType)
    val p2Last = updateCrustSize(p2c, LargeCrustSize)
    val pizzas3 = o2.pizzas :+ p2Last
    val o3 = o2.copy(pizzas = pizzas3)
    println(o3)
    val p2d = updateCrustSize(
      updateCrustType(
        addTopping(
          addTopping(p2, Pepperoni),
          Mushrooms
        ),
        ThickCrustType
      ),
      LargeCrustSize
    )

    import MockDbOrderService._
    val orderPrice = calculateOrderPrice(o3)
    println(s"Order Price = $orderPrice")
    val p5 = Pizza(MediumCrustSize, RegularCrustType, Seq(Cheese, Pepperoni, Pepperoni, Sausage))
    val p5a = removeTopping(p5, Pepperoni)
    println("\nSHOULD BE Cheese/Pepperoni/Sausage:")
    println(p5a)

  }
  type Money = BigDecimal
  case class Pizza(crustSize: CrustSize, crustType: CrustType, toppings: Seq[Topping])
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

  // SERVICE
  trait PizzaServiceInterface{
    def addTopping(p:Pizza, t:Topping): Pizza
    def removeTopping(p:Pizza, t:Topping):Pizza
    def removeAllToppings(p:Pizza):Pizza

    def updateCrustSize(p:Pizza, cs: CrustSize): Pizza
    def updateCrustType(p:Pizza, ct: CrustType): Pizza

    def calculatePizzaPrice(p:Pizza, toppingPrices: Map[Topping, Money], crustSizePrices: Map[CrustSize, Money], crustTypePrices: Map[CrustType, Money]): Money
  }
  trait PizzaService extends PizzaServiceInterface{
    def addTopping(p:Pizza, t:Topping): Pizza = p.copy(toppings = p.toppings :+ t)
    def removeTopping(p:Pizza, t:Topping):Pizza = {
      val (before, atAndAfter) = p.toppings span (_ == t)
      val newToppings = before ++ atAndAfter.drop(1)
      p.copy(toppings = newToppings)
    }
    def removeAllToppings(p:Pizza):Pizza = p.copy(toppings = Seq[Topping]())

    def updateCrustSize(p:Pizza, cs: CrustSize): Pizza = p.copy(crustSize = cs)
    def updateCrustType(p:Pizza, ct: CrustType): Pizza = p.copy(crustType = ct)

    def calculatePizzaPrice(p:Pizza, toppingPrices: Map[Topping, Money], crustSizePrices: Map[CrustSize, Money], crustTypePrices: Map[CrustType, Money]): Money ={
      val base = BigDecimal(10)
      val numToppings = p.toppings.size
      val price = base + 1.00 * numToppings
      price
    }
  }
  object PizzaService extends PizzaService

  // DATABASE
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
    // create a concrete implementation of the trait so we
    // can use its `calculatePizzaPrice` function
    object PizzaService extends PizzaService
    import PizzaService.calculatePizzaPrice
    // all implementations of this trait will use these functions,
    // so go ahead and define them here
    private lazy val toppingPricesMap = database.getToppingPrices()
    private lazy val crustSizePricesMap = database.getCrustSizePrices()
    private lazy val crustTypePricesMap = database.getCrustTypePrices()
    // the publicly-available service
    def calculateOrderPrice(o: Order): Money = calculateOrderPriceInternal(o, toppingPricesMap, crustSizePricesMap, crustTypePricesMap)
    private def calculateOrderPriceInternal(o: Order, toppingPrices: Map[Topping, Money], crustSizePrices: Map[CrustSize, Money], crustTypePrices: Map[CrustType, Money]): Money = {
      val pizzaPrices: Seq[Money] = for {
        pizza <- o.pizzas
      } yield {calculatePizzaPrice(pizza, toppingPrices, crustSizePrices, crustTypePrices)}
      pizzaPrices.sum
    }
  }
  object MockDbOrderService extends AbstractOrderService {
    val database = MockPizzaDao
  }
}
