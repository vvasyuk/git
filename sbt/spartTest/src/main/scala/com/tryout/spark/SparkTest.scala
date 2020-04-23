package com.tryout.spark

import java.io.File

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import resource._


object SparkTest {
  def main(args: Array[String]): Unit = {
    println("SparkTest")
//    getListOfFiles("D:\\work\\tryout\\sbt\\data\\in").foreach(println(_))
//    val dirs = new File("D:\\work\\tryout\\sbt\\data").listFiles().filter(_.isDirectory)
//    val files = dirs.flatMap(x=>{
//      if(x.isFile){
//        println(x.getName)
//        x.getName
//      }
//      ""
//    })

//    spark.catalog.listDatabases.show(false)
//    spark.catalog.listTables.show(false)

    //val spark = getSession
    //dummyJob1(spark)
    val files = List("D:\\work\\tryout\\sbt\\data\\in\\a.csv", "D:\\work\\tryout\\sbt\\data\\in\\b.csv", "D:\\work\\tryout\\sbt\\data\\in\\c.csv")
    managed(getSession).acquireAndGet(implicit spark => dummyJob3Alt(files))
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
    import spark.implicits._
    // read one file
    //val df = spark.read.option("header", "true").csv("D:\\work\\tryout\\sbt\\data\\in\\a.csv")

    // read from multiple folders
    //val folders = List("D:\\work\\tryout\\sbt\\data\\in")         // "dir1", "dir2"
    //val df = spark.read.option("header", "true").csv(folders: _*)

    // list of filenames
    val paths = List("D:\\work\\tryout\\sbt\\data\\in\\a.csv", "D:\\work\\tryout\\sbt\\data\\in\\b.csv", "D:\\work\\tryout\\sbt\\data\\in\\c.csv")
    val df = spark.read.option("header", "true").csv(paths: _*)
    val df1 = df.withColumn("file", substring_index(input_file_name(), "/", -1))
    val df2 = List("c.csv").toDF("file")

    val cols1 = df1.columns.toSet
    val cols2 = df2.columns.toSet
    val total = cols1 ++ cols2 // union

    val order = df1.columns ++  df2.columns
    val sorted = total.toList.sortWith((a,b)=> order.indexOf(a) < order.indexOf(b))

    def expr(myCols: Set[String], allCols: List[String]) = {
      allCols.map( {
        case x if myCols.contains(x) => col(x)
        case y => lit(null).as(y)
      })
    }

    val df3 = df1.select(expr(cols1, sorted): _*).unionAll(df2.select(expr(cols2, sorted): _*))

    df1.show(15)
    df2.show(15)
    df3.show(15)
  }

  def dummyJob3Alt(cfg: List[String], s: String = "xyi")(implicit spark: SparkSession):Unit={
    println(s)
    def _getFiles = Utility.getFiles(cfg)
    val paths = _getFiles
    val df = spark.read.option("header", "true").csv(paths: _*)
      .withColumn("file", substring_index(input_file_name(), "/", -1))
    import spark.implicits._
    val df2 = List(("a.csv",0),("b.csv",0),("c.csv",0)).toDF("file", "cnt")
    val dfGrouped = df.groupBy("file").agg(count(lit(1)) as "cnt")

    val unioned = dfGrouped.select("file", "cnt")
      .join(df2, dfGrouped("file") ===  df2("file"), "full")
      .select(coalesce(dfGrouped("file"),df2("file")).as("file"), coalesce(dfGrouped("cnt"),df2("cnt")).as("cnt"))


    //unioned.write.format("csv").save("myFile.csv")
    unioned.show(15)
    //df2.show(2)

  }



  def getListOfFiles(dir: String):List[File] = {
    val d = new File(dir)
    if (d.exists && d.isDirectory) {
      d.listFiles.filter(_.isFile).toList
    } else {
      List[File]()
    }
  }
}

object Utility{
  def getFiles(l: List[String]): List[String] = {
    l
  }
}