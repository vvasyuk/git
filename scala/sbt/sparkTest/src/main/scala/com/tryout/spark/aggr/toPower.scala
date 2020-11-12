package com.tryout.spark.aggr

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, lit, pow, sum}

object toPower {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("SparkSessionExample")
      .config("spark.master", "local")
      .getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")
    import spark.implicits._

    val dfPow = Seq(
      ("13","abc", "11111111", "7"),
      ("13","abc", "22222222", "7"),
      ("13","abc", "33333333", "7"),
      ("13","abd", "66666666", "7")
    ).toDF("id1", "id2", "number", "decimal")

    dfPow
      .withColumn("decimals_applied", col("number")/pow(lit(10), col("decimal")))
      .groupBy("id1","id2")
      .agg(
        sum(col("decimals_applied")) as "sum"
      )
      .show(false)
  }
}
