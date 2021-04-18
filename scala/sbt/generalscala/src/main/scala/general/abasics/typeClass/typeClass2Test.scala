package general.abasics.typeClass

object typeClass2Test {
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
  case class Pizza (crustSize: CrustSize, crustType: CrustType, toppings: Seq[Topping])

  trait ToString[A]{
    def toString(a:A):String
  }

  object ToStringInstances{
    implicit val pizzaAsString = new ToString[Pizza] {
      override def toString(a: Pizza): String = s"${a.crustSize}, ${a.crustType}, ${a.toppings}"
    }
  }
  // a
  object ToString{
    def asString[A](a:A)(implicit toStringInstance: ToString[A]):String = {
      toStringInstance.toString(a)
    }
  }
  // b
  object ToStringSyntax{
    implicit class ToStringOps[A](value:A){
      def asString(implicit toStringInstance: ToString[A]): String ={
        toStringInstance.toString(value)
      }
    }
  }

  def main(args: Array[String]): Unit = {
    import ToStringInstances.pizzaAsString
    import ToStringSyntax._
    val p = Pizza(LargeCrustSize, ThinCrustType, Seq(Cheese, Pepperoni, Sausage))
    // a
    println(ToString.asString(p))
    // b
    println(p.asString)
  }



}
