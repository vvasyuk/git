package general.abasics.apps.scopt

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import scala.util.{Failure, Success, Try}

object ParseArgs {
  def apply(pattern: DateTimeFormatter, args: Array[String]): Option[ParseArgs] ={
    import scopt.OptionParser

    val parser = new OptionParser[ParseArgs]("AAA"){
      opt[String]("v1").required().text("v1 variable").action{ (a,b) => b.copy(v1=a) }
      opt[String]("v2").required().text("v2 variable").validate(validateDateString(pattern)).action{ (a,b) => b.copy(d1=LocalDate.parse(a, pattern))}
      help("help").text("usage")
    }
    parser.parse(args, ParseArgs("no", null))
  }
  def validateDateString(pattern: DateTimeFormatter)(s: String): Either[String, Unit] = {
    Try(LocalDate.parse(s, pattern)) match {
      case Success(_) => Right(())
      case Failure(exception) => Left(exception.getMessage)
    }
  }
}

case class ParseArgs(v1: String, d1: LocalDate)
