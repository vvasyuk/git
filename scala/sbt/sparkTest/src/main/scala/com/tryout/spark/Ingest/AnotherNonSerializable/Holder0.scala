package com.tryout.spark.Ingest.AnotherNonSerializable

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

import org.apache.spark.sql.functions.udf

object Holder0 {
  val in = {
    println("creating DateTimeFormatter")
    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z")
  }
  val out = DateTimeFormatter.ofPattern("yyyy")

  val fDate = udf{ x: String =>
    println("running udf")
    ZonedDateTime.parse(s"$x UTC", in).format(out)}
}
