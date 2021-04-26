package general.abasics.monads.io

import cats.effect.IO
import cats.effect.unsafe.implicits.global

object ioTest {
  def main(args: Array[String]): Unit = {
    def forExpression: IO[Unit] = for {
      _ <- putStrLn("First name?")
      firstName <- getLine
      _ <- putStrLn(s"Last name?")
      lastName <- getLine
      fNameUC = firstName.toUpperCase
      lNameUC = lastName.toUpperCase
      _ <- putStrLn(s"First: $fNameUC, Last: $lNameUC")
    } yield ()
    // run the block of code whenever you want to ...
    forExpression.unsafeRunSync()
  }
  def getLine: IO[String] = IO(scala.io.StdIn.readLine())
  def putStrLn(s: String): IO[Unit] = IO(println(s))
}
