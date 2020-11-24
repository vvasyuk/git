package tryy

import scala.util.{Failure, Try}

object tryTest {
  def main(args: Array[String]): Unit = {
    Try(func(true))
      .onF(e => println(e))
      .map(_ => println("it was a success"))

    println("------------------")

    Try(func(false))
      .onF(e => println(e))
      .map(_ => println("it was a success"))
  }

  def func(b: Boolean) = {
    if(b) 1 else throw new Exception("oops")
  }

  implicit class RichTry[T](val value: Try[T]) extends AnyVal{
    def onF(action: Throwable => Unit): Try[T] = {
      value match {
        case Failure(exception: Exception) => action(exception)
        case _ =>
      }
      value
    }
  }

}

