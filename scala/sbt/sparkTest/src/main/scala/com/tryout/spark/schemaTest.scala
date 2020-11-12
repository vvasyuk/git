package com.tryout.spark


import com.tryout.spark.Tools.getSession
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.{Encoders, SparkSession}
import resource.managed

object schemaTest {
  def main(args: Array[String]): Unit = {
    managed(getSession).acquireAndGet(implicit spark => readWithSchema)
  }

  def getSchema: StructType ={
    Encoders.product[Schema].schema
  }

  def readWithSchema()(implicit spark: SparkSession):Unit={
    spark.sparkContext.setLogLevel("ERROR")
    import spark.implicits._
    val df = spark.read.option("headet", "true")
      .schema(getSchema)
      .csv("src/main/resources/schemaTest.csv")
    df.show(false)
    df.printSchema()
  }
}

case class Schema(
  col1: Option[String],
  col2: Option[String]
)

