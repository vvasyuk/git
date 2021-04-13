package general.abasics.apps

import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Gen, Properties}

object ScalaCheckTest extends Properties("IncreaseRandomlySpec") {

  private def getRandomIntFrom1To100(): Int = scala.util.Random.nextInt(100) + 1
  def increaseRandomly(i: Int): Long = {
    val randomNum = getRandomIntFrom1To100()
    i + randomNum.toLong
  }

  property("increaseRandomly") = forAll { input: Int =>
    println(s"input: $input")
    val result = increaseRandomly(input)
    result > input
  }
}
