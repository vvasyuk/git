package com.tryout.spark.aggr

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._

object CountDistinctInWindows {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("SparkSessionExample")
      .config("spark.master", "local")
      .getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")
    import spark.implicits._

    val df = Seq(
      ("1", "a", "c", "c"),
      ("2", "b", "d", "c"),
      ("1", "a", "c", "x"),
      ("2", "b", "e", "c")
    ).toDF("i", "j", "k", "l")

    val df1Col =
      df.withColumn("cnt", size(collect_set("k").over(Window.partitionBy("i", "j"))))
    df1Col.show(false)

    val df2Col =
      df.withColumn("cnt", size(collect_set(concat(col("k"), col("l"))).over(Window.partitionBy("i", "j"))))
    df2Col.show(false)

  }
}
