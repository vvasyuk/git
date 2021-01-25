package com.tryout.spark.filter

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object filterSubstrTest {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("SparkSessionExample")
      .config("spark.master", "local")
      .getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")
    import spark.implicits._

    val df0 = Seq(
      ("2", "id2", "d2", "4", "20210125 14:45"),
      ("4", "id4", "d4", "5", "20210125 14:46"),
      ("13", "id13", "d13", "5", "20210125 14:47")
    ).toDF("id1", "id2", "df1_data", "df1_measure", "date")

    df0.filter(substring(col("date"), 0, 8) === "20210125").show()
  }
}
