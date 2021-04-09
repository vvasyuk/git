package general.abasics.apps

object BehaviourWithFP {
  def main(args: Array[String]): Unit = {
    IrishSetter.bark
    IrishSetter.wagTail
  }

  object IrishSetter extends AnimalWithTail("black") with DogTailService with DogMouthService

  trait Animal
  abstract class AnimalWithTail(color: String) extends Animal

  trait DogTailService{
    // implementers must be sub type of AnimalWithTail
    // trait can only be mixed into class that extend AnimalWithTail
    this: AnimalWithTail =>


    def wagTail = {
      println(s"wagging tail")
      this.lowerTail
    }
    def lowerTail = {
      println("lowering tail")
      //this.wagTail // recursive method wagTail needs result type
    }
    def raiseTail = println("raising tail")
    def curlTail = println("curling tail")
  }

  trait DogMouthService{
    // implementers must be sub type of AnimalWithTail
    // trait can only be mixed into class that extend AnimalWithTail
    this: AnimalWithTail =>
    def bark = println("bark")
    def lick = println("lick")
  }
}
