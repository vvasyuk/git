package general.abasics.monads.ioAndState

import cats.data.StateT
import cats.effect.IO
import cats.effect.unsafe.implicits.global

object ioAndStateT3Test {
  def main(args: Array[String]): Unit = {
    val forExpression: StateT[IO, IntState, Int] = for {
      _ <- add(2)
      _ <- add(3)
      x <- multiply(10)
    } yield x

    val res = forExpression.run(IntState(1))
    // res.map(t => println(t))
    val res1 = res.unsafeRunSync()
    println(res1)
  }

  case class IntState(i: Int)

  def add(i:Int): StateT[IO, IntState, Int] =StateT[IO, IntState, Int] { (oldState: IntState) =>
    val newValue = i + oldState.i
    val newState = oldState.copy(i = newValue)
    IO(newState, newValue)
  }

  def multiply(i:Int): StateT[IO,IntState,Int]= StateT[IO,IntState,Int]{ (oldState: IntState)=>
    val newValue = i * oldState.i
    val newState = oldState.copy(i = newValue)
    IO(newState, i)
  }
}
