package io

import cats.effect.IO
import cats.effect.unsafe.implicits.global

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import cats.implicits.catsSyntaxApplicativeError
import io.currencyExchangeIO.{Currency, exchangeIfTrending}

import java.util.concurrent.TimeUnit
import scala.concurrent.duration.FiniteDuration
import scala.jdk.CollectionConverters.MapHasAsScala


// PROBLEMS: we analyse three elements and discard them, we don't use a sliding window, each computation is isolated, no time between calls
object currencyExchangeIORec {
  case class Currency(name: String) extends AnyVal

  import CurrencyExchangeImpure.exchangeRatesTableApiCall
  def exchangeTable(from: Currency): IO[Map[Currency, BigDecimal]] =
    IO(exchangeRatesTableApiCall(from.name).asScala.map {
      case (currencyName, rate) => (Currency(currencyName), BigDecimal(rate))
    }.toMap)


  def trending(rates: List[BigDecimal]): Boolean =
    rates.size > 1 &&
      rates.zip(rates.drop(1))
        .forall(ratePair => ratePair match {
          case (previousRate, rate) => rate > previousRate
        })


  def extractSingleCurrencyRate(currencyToExtract: Currency)(table: Map[Currency, BigDecimal]): Option[BigDecimal] =
    table.get(currencyToExtract)


  def retry[A](action: IO[A], maxRetries: Int): IO[A] =
    List
      .range(0, maxRetries)
      .map(_ => action)
      .foldLeft(action)((program, nextAction) => {
        program.orElse(nextAction)
      })


  def currencyRate(from: Currency, to: Currency): IO[BigDecimal] =
    for {
      table <- retry(exchangeTable(from), 10)
      rate <- extractSingleCurrencyRate(to)(table) match {
        case Some(value) => IO.pure(value)
        case None        => currencyRate(from, to)
      }
    } yield rate


  def lastRates(from: Currency, to: Currency, n: Int): IO[List[BigDecimal]] =
    if (n < 1) then IO.pure(List.empty)
    else
      for {
        currencyRate   <- currencyRate(from, to)
        remainingRates <- if (n == 1) IO.pure(List.empty) else lastRates(from, to, n - 1)
      } yield remainingRates.prepended(currencyRate)


  def exchangeIfTrending(amount: BigDecimal, from: Currency, to: Currency): IO[BigDecimal] =
    for {
      rates  <- lastRates(from, to, 3)
      result <- if (trending(rates)) IO.pure(amount * rates.last) else exchangeIfTrending(amount, from, to)
    } yield result


  def main(args: Array[String]): Unit = {
    val x = exchangeIfTrending(BigDecimal(1000), Currency("USD"), Currency("EUR")).unsafeRunSync()
    println(x)
  }
}
