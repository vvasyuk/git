package com.tryout.spark.aggr

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
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

    val df0 = Seq(("1", "bat", "2020-09-08 07:51:14"),
      ("", "bat", "2020-09-08 07:51:14")
    )
      .toDF("number", "word", "date")

    val df = Seq(
      ("7.345", "bat", "2020-09-08 07:51:14"),
      ("64", "mouse", "2020-09-08 07:51:14"),
      ("64", "mouse", "2020-12-08 02:51:14"),
      ("64", "mouse", "2020-09-08 12:51:14"),
      ("64", "mouse", "2020-09-08 03:51:14")//,
      //(-27, "horse", "2020-09-08 07:51:14")
    ).toDF("number", "word", "date")

    val dfCnt = Seq(
      ("13", "id2", "3", "10"),
      ("13", "id2", "3", "10"),
      ("13", "id2", "4", "10"),
      ("14", "id3", "4", "10"),
      ("15", "id4", "4", "10")
    ).toDF("id1", "id2", "id3", "number")
    dfCnt.printSchema()
    val res = dfCnt.groupBy("id1", "id2", "id3")
      .agg(sum("number"))
      .withColumn("windowCnt", count(lit(1)).over(Window.partitionBy("id1", "id2")))
      .filter(col("windowCnt") === "1")
      //.show(false)
    res.printSchema()
    res.show(false)



//    val dfDouble = df.withColumn("number", 'number.cast(DoubleType))
//    val df0Double = df0.withColumn("number", 'number.cast(DoubleType))
//
//    val unioned = dfDouble.union(df0Double)
//
//    val res = unioned.groupBy(col("word"))
//      .agg(
//        count(lit(1)) as "cnt",
//        sum("number") as "number",
//        max("date") as "maxDate",
//        min("date") as "minDate")
//
//    res.show(false)


//    df1.show(false)
//    +-----+---+
//    |word |cnt|
//    +-----+---+
//    |horse|1  |
//    |bat  |1  |
//    |mouse|1  |
//    +-----+---+
//    df1.agg(sum(col("cnt"))).show(false)
//    +--------+
//    |sum(cnt)|
//    +--------+
//    |3       |
//    +--------+
  }
}
