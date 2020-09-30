package com.tryout.spark

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._


object aggTest {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("SparkSessionExample")
      .config("spark.master", "local")
      .getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")
    import spark.implicits._

    val df = Seq(
      (8, "bat", "2020-09-08 07:51:14"),
      (64, "mouse", "2020-09-08 07:51:14"),
      (64, "mouse", "2020-12-08 02:51:14"),
      (64, "mouse", "2020-09-08 12:51:14"),
      (64, "mouse", "2020-09-08 03:51:14"),
      (-27, "horse", "2020-09-08 07:51:14")
    ).toDF("number", "word", "date")

    val df1 = df.groupBy(col("word"))
      .agg(count(lit(1)) as "cnt",
        max("date") as "maxDate",
        min("date") as "minDate")

    df1.show(false)
//    +-----+---+
//    |word |cnt|
//    +-----+---+
//    |horse|1  |
//    |bat  |1  |
//    |mouse|1  |
//    +-----+---+
    df1.agg(sum(col("cnt"))).show(false)
//    +--------+
//    |sum(cnt)|
//    +--------+
//    |3       |
//    +--------+
  }
}
