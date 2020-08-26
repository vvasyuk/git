package com.tryout.spark

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, count, lit, sum}

object jobDescription {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("SparkSessionExample")
      .config("spark.master", "local")
      .getOrCreate()

    import spark.implicits._

    val df = Seq(
      (8, "bat"),
      (64, "mouse"),
      (-27, "horse")
    ).toDF("number", "word")

    val df1 = df.groupBy(col("word")).agg(count(lit(1)) as "cnt")
    //spark.sparkContext.setJobDescription("riders on the storm")
    df1.collect()

  }
}
