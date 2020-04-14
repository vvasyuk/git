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

    managed(getSession).acquireAndGet(spark => dummyJob3(spark))
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

  def dummyJob3(spark: SparkSession):Unit={
    // read one file
    //val df = spark.read.option("header", "true").csv("D:\\work\\tryout\\sbt\\data\\in\\a.csv")

    // list of filenames
    val paths = List("D:\\work\\tryout\\sbt\\data\\in\\a.csv", "D:\\work\\tryout\\sbt\\data\\in\\b.csv", "D:\\work\\tryout\\sbt\\data\\in\\c.csv")
    val df = spark.read.option("header", "true").csv(paths: _*)

    // read from multiple folders
    //val folders = List("D:\\work\\tryout\\sbt\\data\\in")         // "dir1", "dir2"
    //val df = spark.read.option("header", "true").csv(folders: _*)

    df.show(15)
  }
}



// ok-input dir
//ok-output dir (enriched files)
//ok-processed dir (from input)
//ok-get files from directory input
//ok-read csv from input - res dataset
//-enrich (df.withColumn)
//
//-df.write.orc(stagePath)
//-move files to processed