package com.tryout.spark.join

import org.apache.spark.sql.SparkSession

object selfJoin {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("SparkSessionExample")
      .config("spark.master", "local")
      .getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")
    import spark.implicits._

    val df0 = Seq(
      ("2", "id2", "d2", "4", "d2", "1"),
      ("4", "id4", "d4", "5", "d4", "5"),
      ("13", "id13", "d13", "5", "d13", "5")
    ).toDF("id1", "id2", "df1_data", "df1_measure", "df2_data", "df2_measure")

    

  }
}
