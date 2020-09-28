package com.tryout.spark.Ingest.AnotherNonSerializable

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.functions.udf

trait HolderTrait {
  // not working
//  val in:  DateTimeFormatter
//  val out: DateTimeFormatter
//
//  val fDate = udf{ x: String =>ZonedDateTime.parse(s"$x UTC", in).format(out)}
}
