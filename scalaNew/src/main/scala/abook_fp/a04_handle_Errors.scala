package abook_fp

import scala.util.Try

object a04_handle_Errors {
  def main(args: Array[String]): Unit = {
    println(strToIntOption("123"))
    println(strToIntEither("123"))
    println(strToIntEither("oops"))
  }

  def strToIntOption(s: String):Option[Int] ={
    val r = Try(s.toInt).toOption
    r
  }
  def strToIntEither(s: String):Either[String, Int] ={
    Try(s.toInt).toOption match {
      case Some(x) => Right(x)
      case _ => Left("its bad")
    }
  }
}
