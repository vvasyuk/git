package com.tryout.spark.Ingest.AnotherNonSerializable

import com.tryout.spark.Ingest.getSession
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{lit, col}
import resource.managed

object IngestNonSerialized {

  def main(args: Array[String]): Unit = {
    managed(getSession).acquireAndGet(implicit spark => start)
  }

  def start()(implicit spark: SparkSession):Unit={
    spark.sparkContext.setLogLevel("ERROR")

    val df = spark.read.option("header", "true")
      .csv("src/main/resources/ingest.csv")
    df
      .withColumn("newCol", lit("noice"))
      .withColumn("newCol2", Holder.fString(col("col2")))
      .withColumn("newCol3", Holder.fString2()(col("col2")))
      .withColumn("newCol4", Holder.fDate(col("col2")))
      //.withColumn("newCol5", HolderBase.fDate(HolderImpl.in, HolderImpl.out)(col("col2")))
      .show(false)
  }
}
