package general.abasics.a03Functions.partialFunction

// a function defined as (Int) => String takes any Int and returns a String.
// a Partial Function is only defined for certain values of the defined type. A Partial Function (Int) => String might not accept every Int.
object partial1Test {

  case class PhoneExt(name: String, ext: Int)

  def main(args: Array[String]): Unit = {
    val one: PartialFunction[Int, String] = { case 1 => "one" }

    println(one(1))
    assert(one.isDefinedAt(1) == true)
    assert(one.isDefinedAt(2) == false)

    // composition

    val two: PartialFunction[Int,String] = {case 2 => "two"}
    val three: PartialFunction[Int,String] = {case 3 => "three"}
    val sElse: PartialFunction[Int,String] = {case _ => "something else"}

    val partial = two orElse three orElse sElse
    assert(partial(5)=="something else")
    assert(partial(3)=="three")
    assert(partial(2)=="two")

    // magic

    val extensions = List(PhoneExt("steve", 100), PhoneExt("robey", 200))   // filter takes a function. In this case a predicate function of (PhoneExt) => Boolean.
    val res = extensions.filter{ case PhoneExt(a,b) => b < 200 }            //A PartialFunction is a subtype of Function so filter can also take a PartialFunction!
    println(res)
  }
}
