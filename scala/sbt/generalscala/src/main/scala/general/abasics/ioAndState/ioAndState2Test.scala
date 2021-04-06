package general.abasics.ioAndState

import cats.data.StateT
import cats.effect.IO
import cats.effect.unsafe.implicits.global

object ioAndState2Test {
  def main(args: Array[String]): Unit = {
    println("start")
    val a = add(1)              // StateT[IO,IntState,Int]
    val a1 = a.run(IntState(1)) // IO[(IntState, Int)]
    //a1.map(x => println(x))
    val a2 = a1.unsafeRunSync()
    println(a2)
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
