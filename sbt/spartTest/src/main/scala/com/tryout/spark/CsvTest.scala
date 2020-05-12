package com.tryout.spark

import com.tryout.spark.Tools._
import org.apache.spark.sql.{Encoder, Encoders, Row, SparkSession}
import resource.managed
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object CsvTest {
  def main(args: Array[String]): Unit = {
    managed(getSession).acquireAndGet(implicit spark => readCsv)
  }
  def readCsv()(implicit spark: SparkSession):Unit={
    import spark.implicits._
    val df = spark.read.format("csv")
      .option("header", "true")
      .option("ignoreTrailingWhiteSpace", "true")
      .load("src\\main\\resources\\testCsv.csv").cache()
    df.show(10,false)
    df.printSchema()

    //import org.apache.spark.sql.Row
    //implicit val enc: Encoder[myRow] = Encoders.product[myRow]
    import spark.implicits._
    val dfMap = df.map(row=>{
      val s = row.toSeq.map(c=>c+"UTC")
      //println(s)
      myRow(s(0),s(1),s(2))
      //(s(0),s(1),s(2))
    })
    dfMap.show(10,false)

    import org.apache.spark.sql.functions.udf
    val addUtcUdf = udf{ x: String => x + " UTC" }
    val formatDateUdf = udf{ x: String => ZonedDateTime.parse(x + " UTC", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z")).toString}

    val dfUDF = df
      .withColumn("col1", addUtcUdf('col1))
      .withColumn("col2", formatDateUdf('col2))
    dfUDF.show(10,false)

//    import org.apache.spark.sql.functions._
//    val dfWhen = df.withColumn("col1", when(col("col1") === "name1", col("col1").toString()+"UTC").otherwise(col("col1")));
//    dfWhen.show(10,false)
// df.withColumn('start', concat(
    //                      when(col('start') == 'text', lit('new'))
    //                      .otherwise(col('start))
    //                     , lit('asd')
    //                     )


  }
}
case class myRow(col11:String,col12:String,col13:String)