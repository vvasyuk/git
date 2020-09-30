package com.tryout.spark.Ingest.AnotherNonSerializable

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.functions.udf

object HolderBase {
  //def fDate(in: DateTimeFormatter, out: DateTimeFormatter):UserDefinedFunction = udf{ x: String =>ZonedDateTime.parse(s"$x UTC", in).format(out)}
//  val fDate: (DateTimeFormatter,DateTimeFormatter) => UserDefinedFunction =
//    (in,out) => udf{ x: String =>ZonedDateTime.parse(s"$x UTC", in).format(out)}
}
