package com.tryout.spark.Ingest.AnotherNonSerializable

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.functions.udf

object Holder {
  val in = {
    println("creating DateTimeFormatter")
    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z")
  }
  val out = DateTimeFormatter.ofPattern("yyyy-MM-dd")
  //def formatDateUdf(): UserDefinedFunction = udf{x: String => ZonedDateTime.parse(s"$x UTC", f).toString}
  //val formatDateUdf = udf{ x: String => ZonedDateTime.parse(s"$x UTC", f).toString}
  //works
  val fString = udf{ x: String => x + x}
  def fString2(): UserDefinedFunction = udf{ x: String => x + x}

  //works
  val fDate = udf{ x: String =>
    println("running udf")
    ZonedDateTime.parse(s"$x UTC", in).format(out)}
  //def fString2(): UserDefinedFunction = udf{ x: String => x + x}
}
