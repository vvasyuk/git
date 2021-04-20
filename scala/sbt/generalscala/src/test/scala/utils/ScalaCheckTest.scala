package utils

import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Gen, Properties}

//object ScalaCheckTest extends Properties("IncreaseRandomlySpec") {
//
//  private def getRandomIntFrom1To100(): Int = scala.util.Random.nextInt(100) + 1
//  def increaseRandomly(i: Int) = {
//    val randomNum = getRandomIntFrom1To100()
//    i + randomNum
//  }
//
//  property("increaseRandomly") = forAll { input: Int =>
//    val result = increaseRandomly(input)
//    result > input
//  }
//}
