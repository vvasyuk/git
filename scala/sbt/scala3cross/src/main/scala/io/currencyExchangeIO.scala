package io

import cats.effect.IO
import cats.effect.unsafe.implicits.global
import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import cats.implicits.catsSyntaxApplicativeError

import java.util.concurrent.TimeUnit
import scala.concurrent.duration.FiniteDuration
import scala.jdk.CollectionConverters.MapHasAsScala


// PROBLEMS: just one decision, we'd like to repeat until successful + hardcoded 3 currencyTable fetches
object currencyExchangeIO {
  case class Currency(name: String) extends AnyVal

  import CurrencyExchangeImpure.exchangeRatesTableApiCall
  def exchangeTable(from: Currency): IO[Map[Currency, BigDecimal]] =
    IO(exchangeRatesTableApiCall(from.name).asScala.map {
      case (currencyName, rate) => (Currency(currencyName), BigDecimal(rate))
    }.toMap)


  def extractSingleCurrencyRate(currencyToExtract: Currency)(table: Map[Currency, BigDecimal]): Option[BigDecimal] =
    table.get(currencyToExtract)


  def retry[A](action: IO[A], maxRetries: Int): IO[A] = {
    List
      .range(0, maxRetries)
      .map(_ => action)
      .foldLeft(action)((program, nextAction) => {
        program.orElse(nextAction)
      })
  }


  def lastRates(from: Currency, to: Currency): IO[List[BigDecimal]] =
    for {
      table1     <- retry(exchangeTable(from), 10)
      table2     <- retry(exchangeTable(from), 10)
      table3     <- retry(exchangeTable(from), 10)
      lastTables = List(table1, table2, table3)
    } yield lastTables.flatMap(extractSingleCurrencyRate(to))


  def trending(rates: List[BigDecimal]): Boolean =
    rates.size > 1 &&
      rates.zip(rates.drop(1))
        .forall(ratePair => ratePair match {
          case (previousRate, rate) => rate > previousRate
        })


  def exchangeIfTrending(amount: BigDecimal, from: Currency, to: Currency): IO[Option[BigDecimal]] =
    lastRates(from, to)
      .map(rates => if (trending(rates)) Some(amount * rates.last) else None)


  def main(args: Array[String]): Unit = {
    assert(trending(List.empty) == false)
    assert(trending(List(BigDecimal(1), BigDecimal(2), BigDecimal(3), BigDecimal(8))) == true)
    assert(trending(List(BigDecimal(1), BigDecimal(4), BigDecimal(3), BigDecimal(8))) == false)

    val usdExchangeTables = List(
      Map(Currency("EUR") -> BigDecimal(0.82)),
      Map(Currency("EUR") -> BigDecimal(0.83)),
      Map(Currency("JPY") -> BigDecimal(104))
    )

    println(usdExchangeTables.map(extractSingleCurrencyRate(Currency("EUR"))))
    exchangeIfTrending(BigDecimal(1000), Currency("USD"), Currency("EUR")).unsafeRunSync()

  }


}
