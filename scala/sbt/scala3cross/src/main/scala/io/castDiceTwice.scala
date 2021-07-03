package io

import cats.effect.IO
import cats.effect.unsafe.implicits.global
import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

object castDiceTwice {

  def castDieImpure(): Int =
    println("casting dice")
    val r = new scala.util.Random
    //  if(rand.nextBoolean())
    //    throw new RuntimeException(ôDie fell offö);
    r.nextInt(6)+1

  def castTheDie(): IO[Int] = IO(castDieImpure())

  def main(args: Array[String]): Unit = {
//    for(
//      _ <- 1 to 100
//    ) println(castDieImpure)

    val dieCast = castTheDie()
    println(dieCast.unsafeRunSync())

    val castTwiceIO = for {
      first <- castTheDie()
      second <- castTheDie()
    } yield first + second
    val castTwice = castTwiceIO.unsafeRunSync()
    println(s"cast twice sum: $castTwice")

  }
}


