package dcp.p1

//import cats.effect._
import cats.effect.IO
import cats.effect.unsafe.implicits.global
import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

object q010_delay:
//  implicit val timer = IO.timer(ExecutionContext.global)

  def main(args: Array[String]): Unit =
    // delay(5000, () => println("running"))
    delay2(2, () => println("running"))

  def delay(n: Int, f: => Unit): Unit =
    println("sleeping")
    Thread.sleep(n)
    f

  def delay2(n: Int, f: () => Unit): Unit =
    println("sleeping")
    IO.sleep(n.seconds).unsafeRunSync()
    IO(f()).unsafeRunSync()
