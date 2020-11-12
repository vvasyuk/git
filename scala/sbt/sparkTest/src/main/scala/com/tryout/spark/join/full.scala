package com.tryout.spark.join

import org.apache.spark.sql.{DataFrame, DataFrameNaFunctions, SparkSession}
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._

object full {
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

    val df1 = Seq(
      ("2", "id2", "d2", "1"),
      ("5", "id5", "d5", "2"),
      ("6", "id6", "d6", "3"),
      ("13", "id13", "aaa", "5")
    ).toDF("id1", "id2", "df1_data", "df1_measure")

    val df2 = Seq(
      ("2", "id2", "d2", "4"),
      ("3", "id3", "d3", "5"),
      ("4", "id4", "d4", "6"),
      ("13", "id13", "d13", "5")
    ).toDF("id1", "id2", "df2_data", "df2_measure")


    val dfJoined =  df1.join(df2, Seq("id1", "id2"), "full")
      .select("id1","id2", "df1_data", "df1_measure", "df2_data", "df2_measure")

    dfJoined.show(false)

    val unioned = df0.union(dfJoined)
    val filtered = unioned.filter(col("df1_data").isNotNull)
    val aggregated = filtered
      .groupBy(col("id1"),col("id2"),col("df1_data"),col("df2_data"))
      .agg(
        sum("df1_measure"),
        sum("df2_measure"))



    withCntById(aggregated).filter(col("cnt") === 1)
      .show(false)





  }

  def withCntById(df:DataFrame):DataFrame ={
    df.withColumn("cnt", count(lit(literal = 1)).over(Window.partitionBy("id1","id2")))
  }
}
