package com.tryout.spark

import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.functions._
import org.apache.spark
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

object JoinTest {
  def main(args: Array[String]): Unit = {
    def spark = SparkSession
      .builder()
      .appName("SparkSessionExample")
      .config("spark.master", "local")
      .getOrCreate()

    val A = Seq(
      Row(1, "bat"),
      Row(2, "mouse"),
      Row(3, "horse")
    )
    val B = Seq(
      Row(1),
      Row(2),
      Row(4),
      Row(5)
    )

    val ASchema = List(StructField("number", IntegerType, true), StructField("word", StringType, true))
    val BSchema = List(StructField("number", IntegerType, true))
    val aDF = spark.createDataFrame(spark.sparkContext.parallelize(A), StructType(ASchema))
    val bDF = spark.createDataFrame(spark.sparkContext.parallelize(B), StructType(BSchema))

//    aDF.show(false)
//    bDF.show(false)

    bDF.join(aDF, bDF("number") ===  aDF("number"), "left").show(false)
  }
}
