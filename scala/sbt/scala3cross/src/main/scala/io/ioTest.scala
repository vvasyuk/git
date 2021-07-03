package io

import cats.effect.IO
import cats.effect.unsafe.implicits.global
import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

object ioTest {
  def main(args: Array[String]): Unit = {
    delay2(2, () => println("running"))
  }
}

def delay2(n: Int, f: () => Unit): Unit =
  println("sleeping")
  IO.sleep(n.seconds).unsafeRunSync()
  IO(f()).unsafeRunSync()


                                              