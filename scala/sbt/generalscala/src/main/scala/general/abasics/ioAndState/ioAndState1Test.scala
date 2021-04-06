package general.abasics.ioAndState

import cats.data.State
import cats.effect.IO

object ioAndState1Test {
//  def main(args: Array[String]): Unit = {
//    val res = for {
//      _ <- putStrLn("Type anything:") //IO
//      input <- getLine //IO
//      _ <- push(input) //State
//      _ <- putStrLn(s"Input: $input") //IO
//    } yield ()
//  }

  def getLine: IO[String] = IO(scala.io.StdIn.readLine)
  def putStrLn(s: String): IO[Unit] = IO(println(s))

  type Stack = List[String]
  def push(x: String): State[Stack, Unit] = State[Stack, Unit] {
    xs => (x :: xs, ())
  }
}
