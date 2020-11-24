package abook_mft

import scala.util.control.NonFatal
import scala.util.{Failure, Success, Try}

//  flatMap is for situations when you need to continue a computation with another computation, and the continuation will produce a Try as its result.
//  Compare that to the situation with the map function, which requires the continuation to produce a raw value.
object flatMapFunction {
  def main(args: Array[String]): Unit = {
    //we need to create a function that computes the following mathematical expression: (2 / x) / y + 3

    println(fMatch(1,2))
    //println(fNoMap(1,2))
  }

  //bad
  def fMatch(x: Double, y: Double): Try[Double] =
    divide(2, x) match {
      case Success(r1) => divide(r1, y) match {
        case Success(r2) => Success(r2 + 3)
        case f@Failure(_) => f
      }
      case f@Failure(_) => f
    }

  //cannot use map
  //def fNoMap(x: Double, y: Double): Try[Double] =   // does not compile
  //  divide(2, x).map(v=>divide(v, y)).map(v=>v+3)

  // extract the raw result from the Try data structure we got after computing 2/x
  // With the help of flatMap, we can lift the Int => Try[Int] computation into Try[Int] => Try[Int].
  // In other words, once we have computed 2/x, we can divide its result by y
  def f2FlatMap(x: Double, y: Double): Try[Double] =
    divide(2, x).flatMap(r1 => divide(r1, y)).map(r2 => r2 + 3)

  def divide(n1: Double, n2: Double): Try[Double] =
    if (n2 == 0) Failure(new RuntimeException("Division by zero!"))
    else Success(n1 / n2)

  // flatMap on success
  //override def flatMap[U](f: T => Try[U]): Try[U] =
  //  try f(value) catch { case NonFatal(e) => Failure(e) }
}
