package com.tryout.spark.Ingest.AnotherNonSerializable

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.functions.udf

object HolderImpl {
  val in = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z")
  val out = DateTimeFormatter.ofPattern("yyyy-MM-dd")
}