package general.abasics.caseClass

object caseTest {
  def main(args: Array[String]): Unit = {
    val a = one("1")
    val b = two("2")

    println(a)
    println(b)
  }
}

case class one(one: String) extends caseClassTrait {
  override val three: String = "a"
  override val four: String = "b"
}
case class two(two: String)

trait caseClassTrait{
  val three: String
  val four: String
}