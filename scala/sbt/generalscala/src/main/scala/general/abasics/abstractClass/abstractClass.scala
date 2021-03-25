package general.abasics.abstractClass

abstract class Pet (name: String) {
  def speak: Unit = println(s"My name is $name")
}

class Dog(name: String) extends Pet(name)
class Dog2(name: String) extends Pet("Fido2")

object abstractClass {
  def main(args: Array[String]): Unit = {
    val d = new Dog("Fido")
    d.speak

    val d2 = new Dog2("Fido")
    d2.speak
  }
}
