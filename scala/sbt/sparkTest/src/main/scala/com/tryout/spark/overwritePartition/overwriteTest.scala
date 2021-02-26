package com.tryout.spark.overwritePartition

import org.apache.spark.sql.{SaveMode, SparkSession}

object overwriteTest {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("SparkSessionExample")
      .config("spark.master", "local")
      .getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")
    spark.conf.set("spark.sql.sources.partitionOverwriteMode", "dynamic")
    import spark.implicits._

    val df0 = Seq(("1", "bat", "2020-09-08"),
      ("0", "bat", "2020-09-09")
    )
      .toDF("number", "word", "date")

    df0.write.partitionBy("date").mode(SaveMode.Overwrite).parquet("D:\\work\\tryout\\scala\\sbt\\sparkTest\\src\\main\\scala\\com\\tryout\\spark\\overwritePartition\\result")
    val df1 = spark.read.parquet("D:\\work\\tryout\\scala\\sbt\\sparkTest\\src\\main\\scala\\com\\tryout\\spark\\overwritePartition\\result")
    df1.show(false)

    val df2 = Seq(("1", "bat1", "2020-09-08")).toDF("number", "word", "date")
    df2.write.partitionBy("date").mode(SaveMode.Overwrite).parquet("D:\\work\\tryout\\scala\\sbt\\sparkTest\\src\\main\\scala\\com\\tryout\\spark\\overwritePartition\\result")

    val df3 = spark.read.parquet("D:\\work\\tryout\\scala\\sbt\\sparkTest\\src\\main\\scala\\com\\tryout\\spark\\overwritePartition\\result")
    df3.show(false)
  }
}
