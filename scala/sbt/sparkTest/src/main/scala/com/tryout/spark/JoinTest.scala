package com.tryout.spark

import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.functions._
import org.apache.spark
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

object JoinTest {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("SparkSessionExample")
      .config("spark.master", "local")
      .getOrCreate()

    spark.sparkContext.setLogLevel("ERROR")

    val A = Seq(
      Row(1, "bat"),
      Row(2, "mouse"),
      Row(3, "horse")
    )
    val A2 = Seq(
      Row(2, "mouse"),
      Row(3, "horse"),
      Row(4, "dog")
    )
    val B = Seq(
      Row(1),
      Row(2),
      Row(4),
      Row(5)
    )

    val ASchema = List(StructField("number", IntegerType, true), StructField("word", StringType, true))
    val A2Schema = List(StructField("number", IntegerType, true), StructField("word", StringType, true))
    val BSchema = List(StructField("number", IntegerType, true))
    val aDF = spark.createDataFrame(spark.sparkContext.parallelize(A), StructType(ASchema))
    val a2DF = spark.createDataFrame(spark.sparkContext.parallelize(A2), StructType(ASchema))
    val bDF = spark.createDataFrame(spark.sparkContext.parallelize(B), StructType(BSchema))

//    aDF.show(false)
//    bDF.show(false)

    //bDF.join(aDF, bDF("number") ===  aDF("number"), "left").show(false)

    // outter join test
    import spark.implicits._
    val x = Seq((1, "bat"), (2, "mouse"), (3, "horse")).toDF("number", "word")
    val y = Seq((4, "cat"),(5, "cat"), (2, "mouse"), (3, "horse")).toDF("number", "word")

    val joined = x.alias("x").join(y.alias("y"), col("x.number") === col("y.number"), "full")
    joined.show(false)
    joined.where("x.number is null").groupBy("y.word").agg(count("*").alias("cnt")).show(false)
    joined.where("y.number is null").groupBy("x.word").agg(count("*").alias("cnt")).show(false)


    //joined.where("x.number is null").groupBy("y.word").agg(count("*").alias("cnt")).where("cnt > 1").show(false)
  }
}
