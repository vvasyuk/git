package caseClasses

object caseTest {
  def main(args: Array[String]): Unit = {
    val a = one("1")
    val b = two("2")

    println(a)
    println(b)
  }
}

case class one(one: String)
case class two(two: String)