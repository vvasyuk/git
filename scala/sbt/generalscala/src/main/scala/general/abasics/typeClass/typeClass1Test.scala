package general.abasics.typeClass

object typeClass1Test {

  // OOP
  //class Pizza(var crustSize: CrustSize, var crustType: CrustType) {
  //val toppings = ArrayBuffer[Topping]()
  //def addTopping(t: Topping): Unit = { toppings += t }
  //def removeTopping(t: Topping): Unit = { toppings -= t }
  //def removeAllToppings(): Unit = { toppings.clear() }
  //}

  // MODULAR
  //case class Pizza (crustSize: CrustSize, crustType: CrustType,toppings: Seq[Topping])
  //ctrait PizzaService {
  //def addTopping(p: Pizza, t: Topping): Pizza = ???
  //def removeTopping(p: Pizza, t: Topping): Pizza = ???
  //def removeAllToppings(p: Pizza): Pizza = ???
  //}

  // to add functionality one would change the code

  // But one can create type class to add behaviour

  sealed trait Animal
  final case class Cat(name: String) extends Animal
  final case class Dog(name: String) extends Animal
  final case class Bird(name: String) extends Animal


  // type class
  trait BehavesLikeHuman[A]{
    def speak(a: A): Unit
  }
  // type class instance
  object BehavesLikeHumanInstances{
    // only for Dog
    implicit val dogBehavesLikeHuman = new BehavesLikeHuman[Dog] {
      override def speak(a: Dog): Unit = {
        println(s"hi, my name is ${a.name}")
      }
    }
  }

  // API a (Interface Objects approach)

  // API b ()
  object BehavesLikeHumanSyntax {
    implicit class BehavesLikeHumanOps[A](value: A) {
      def speak(implicit behavesLikeHumanInstance: BehavesLikeHuman[A]): Unit = {
        behavesLikeHumanInstance.speak(value)
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val aDog = Dog("dog1")
    //API a (Interface Objects approach)
    import BehavesLikeHumanInstances.dogBehavesLikeHuman
    BehavesLikeHumanInstances.dogBehavesLikeHuman.speak(aDog)

    //API b (type class approach)
    import BehavesLikeHumanSyntax.BehavesLikeHumanOps
    aDog.speak
  }
}
