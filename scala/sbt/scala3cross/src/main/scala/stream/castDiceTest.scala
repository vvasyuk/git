package stream

import cats.effect.IO
import cats.effect.unsafe.implicits.global
//import fs2.{Pure, Stream}

object castDiceTest {
  def main(args: Array[String]): Unit = {

    val numbers = Stream(1,2,3)
    val oddNumbers = numbers.filter(_ % 2 != 0)
    println(numbers)
    println(oddNumbers)

    assert(numbers.toList == List(1,2,3))
    assert(oddNumbers.toList == List(1,3))
    assert(oddNumbers.map(_+17).take(1).toList == List(18))


    // append and take
    val stream1 = Stream(1,2,3)
    val stream2 = Stream(4,5,6)
    assert(
      stream1.append(stream2).toList == List(1,2,3,4,5,6))
    assert(
      stream1.append(stream1).take(4).toList == List(1,2,3,1))


    // infinite streams
    def numbersStream(): Stream[Int] =
      Stream(1,2,3).append(numbersStream())

    assert(
      numbersStream().take(8).toList == List(1,2,3,1,2,3,1,2))


    // infinite streams using repeat
//    val numbersRepeat = Stream(1,2,3).repeat
//    assert(
//      numbersRepeat().take(8).toList == List(1,2,3,1,2,3,1,2))


    // streams of IO values
    import CastingDieImpure.NoFailures.castTheDieImpure

    def castTheDie(): IO[Int] = IO.delay(castTheDieImpure())
    castTheDie().unsafeRunSync()


  }
}
