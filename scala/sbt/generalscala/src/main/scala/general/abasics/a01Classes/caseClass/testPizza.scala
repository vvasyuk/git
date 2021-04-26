package general.abasics.a01Classes.caseClass

object testPizza {
  def main(args: Array[String]): Unit = {

    val ham = Ham()
    val mozzarella = Mozzarella()

    val hamToppingsPizza = Pizza(List(ham))
    Pizza.printIngridients(hamToppingsPizza)


    val hamAndMozzarella = Pizza.addTopping(hamToppingsPizza, mozzarella)
    Pizza.printIngridients(hamAndMozzarella)
  }
}
