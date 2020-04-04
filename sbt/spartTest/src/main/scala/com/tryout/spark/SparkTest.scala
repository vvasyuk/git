package com.tryout.spark

import org.apache.spark.sql.SparkSession
import resource._

object SparkTest {
  def main(args: Array[String]): Unit = {
    println("SparkTest")


//    spark.catalog.listDatabases.show(false)
//    spark.catalog.listTables.show(false)

    //val spark = getSession
    //dummyJob1(spark)

    managed(getSession).acquireAndGet(spark => dummyJob1(spark))
//    for {
//      spark <- managed(getSession)
//    } {
//      dummyJob1(spark)
//    }

    println("end")
  }

  def dummyJob1(spark: SparkSession):Unit={
    val langPercentDF = spark.createDataFrame(List(("Scala", 35), ("Python", 30), ("R", 15), ("Java", 20)))
    val lpDF = langPercentDF.withColumnRenamed("_1", "language").withColumnRenamed("_2", "percent")
    lpDF.orderBy(("percent")).show(false)
  }

  def dummyJob2(spark: SparkSession):Unit={
    val numDS = spark.range(5, 100, 5)
    numDS.show(5)
  }

  def getSession = SparkSession
    .builder()
    .appName("SparkSessionExample")
    .config("spark.master", "local")
    .getOrCreate()
}

