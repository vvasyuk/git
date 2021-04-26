package general.abasics.a01Classes.caseClass

abstract class Topping(val mass: Int)
case class Mozzarella() extends Topping(100)
case class Ham() extends Topping(100)
case class Bacon() extends Topping(100)

case class Pizza(toppings: List[Topping])

object Pizza{
  def addTopping(p: Pizza, newTopping: Topping): Pizza = {
    p.copy(toppings = p.toppings:+newTopping)
  }

  def printIngridients(p: Pizza): Unit = {
    p.toppings.foreach(t => println(s"${t.getClass.getSimpleName} - ${t.mass}"))
  }

}