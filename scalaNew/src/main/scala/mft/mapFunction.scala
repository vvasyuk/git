package mft

import scala.util.{Failure, Success, Try}

object mapFunction {
  // bad
  def divide(n1: Double, n2: Double): Try[Double] =
    if (n2 == 0) Failure(new RuntimeException("Division by zero!"))
  else Success(n1 / n2)

  // need to build a function based on division which computed 2/x+3
  def f1(x: Double): Try[Double] = divide(2, x) match {
      case Success(res) => Success(res + 3)
      case f: Failure[Double] => f
    }

  // most effect types have map function
  // computation that returns an effect type, and we need to continue it with another computation that returns
  // a raw value that is not wrapped in an effect type,
  // You can think of the map function as a lift that allows you to produce functions that work
  // under the Try effect type from functions that work on the raw values.
  def f1Map(x: Double): Try[Double] =
    divide(2, x).map(r => r + 3)

  // map on aa try
  // map[U](f: T => U): Try[U] = Try[U](f(value))

  def main(args: Array[String]): Unit = {
    println(f1(0))
    println(f1Map(0))

    val rMap = divide(2,0).map(x=>x)
    val rFlatMap = divide(2,1).flatMap(x=>divide(x,2))
  }




  case class Person(name: String, age: Int, drivingLicense: Option[String])
  case class Car(model: String, owner: Option[Person], registrationPlate: Option[String])

  def ownerName(car: Car): Option[String] = car.owner.map(p => p.name)

//  The function map on Option
//  def map[B](f: A => B): Option[B] =
//    this match {   //#A
//      case Some(a) => Some(f(a))
//      case None => None
//    }
//
// Some(a) -> map(f) => Some(f(a)) = Some(b)

// The function flatMap on Option
//  def flatMap[B](f: A => Option[B]): Option[B] =
//    this match {
//      case Some(a) => f(a)
//      case None => None
//    }
// Some(a) -> flatMap(f) => f(a) = optB
}
