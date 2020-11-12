package com.tryout.spark.Ingest

import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.{DataFrame, Encoder, SparkSession}

abstract class AbstractIngest (schema: StructType)(implicit spark: SparkSession) {
  def enrich(df: DataFrame): DataFrame

  def start()(implicit spark: SparkSession):Unit={
    spark.sparkContext.setLogLevel("ERROR")

    val df = spark.read.option("header", "true")
      .schema(schema)
      .csv("src/main/resources/ingest.csv")
    val enriched = enrich(df)
    enriched.show(false)
  }
}


//abstract class AbstractIngest[T, U] (implicit spark: SparkSession, enc1: Encoder[T], enc2: Encoder[U]) {
//  def enrich(df: DataFrame): DataFrame
//
//  def start()(implicit spark: SparkSession):Unit={
//    spark.sparkContext.setLogLevel("ERROR")
//    val df = spark.read.option("header", "true")
//      .csv("src/main/resources/ingest.csv")
//    val enriched = enrich(df)
//    enriched.show(false)
//  }
//}