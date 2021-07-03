package io

import cats.effect.IO
import cats.effect.unsafe.implicits.global
import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import cats.implicits.catsSyntaxApplicativeError


object castDiceWithFail {

  def castDieImpure(): Int =
    println("casting dice")
    val r = new scala.util.Random
    if (r.nextBoolean()) then throw new RuntimeException("Die fell off")
    r.nextInt(6)+1

  def castTheDie(): IO[Int] = IO(castDieImpure())

  def main(args: Array[String]): Unit = {

    val castTwiceIO = for {
      first <- castTheDie().orElse(IO.pure(0))
      second <- castTheDie().orElse(IO.pure(0))
    } yield first + second
    val castTwice = castTwiceIO.unsafeRunSync()
    println(s"cast twice sum: $castTwice")

  }
}
