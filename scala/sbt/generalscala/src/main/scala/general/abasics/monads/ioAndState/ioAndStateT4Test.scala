package general.abasics.monads.ioAndState

import cats.data.StateT
import cats.effect.IO
import cats.effect.unsafe.implicits.global

object ioAndStateT4Test {
  def main(args: Array[String]): Unit = {
//    def sumLoop = StateT[IO, SumState, Int] = for {
//      _ <- putStr("\ngive me and int:")
//      input <- getLine()
//      i <- IO(toInt(input))
//      _ <- updateAppState(i)
//      _ <- sumLoop
//    } yield Unit

    def sumLoop: StateT[IO,SumState,Unit] = for {
      _ <- putStrAsStateT("\ngive me and int:")
      input <- getLineAsStateT()
      i <- liftIoIntoStateT(IO(toInt(input)))
      _ <- doSumWithStateT(i)
      _ <- sumLoop
    } yield ()

    sumLoop.run(SumState(0)).unsafeRunSync()

  }

  case class SumState(sum: Int)

  def toInt(s:String):Int = {
    try {
      s.toInt
    } catch {
      case e: NumberFormatException => 0
    }
  }
  def getLine(): IO[String] = IO(scala.io.StdIn.readLine())
  def putStr(s: String): IO[Unit] = IO(print(s))

  def updateAppState(newValue: Int): StateT[IO, SumState, Int] = StateT { (oldState: SumState) =>
    val newSum = newValue + oldState.sum
    val newState = oldState.copy(sum = newSum)
    IO(newState, newSum)
  }

  def doSumWithStateT(newValue: Int): StateT[IO, SumState, Int] = StateT { (oldState: SumState) =>
    val newSum = newValue + oldState.sum
    println(s"updateIntState, old sum: " + oldState.sum)
    println(s"updateIntState, new input: " + newValue)
    println(s"updateIntState, new sum: " + newSum)
    val newState = oldState.copy(sum = newSum)
    IO(newState, newSum)
  }

  def liftIoIntoStateT[A](io: IO[A]):StateT[IO,SumState,A] = StateT{ s: SumState =>
    val result: IO[(SumState,A)] = io.map(a=>(s,a))
    result
  }

  def getLineAsStateT(): StateT[IO,SumState,String] = liftIoIntoStateT(getLine())
  def putStrAsStateT(s:String):StateT[IO,SumState,Unit] = liftIoIntoStateT(putStr(s))

}
