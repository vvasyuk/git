package com.tryout.spark

import org.apache.spark.scheduler.{SparkListener, SparkListenerJobStart, SparkListenerStageSubmitted, SparkListenerTaskStart}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, count, lit, sum}

import scala.collection.mutable

object jobDescription {
  def main(args: Array[String]): Unit = {
    var stack = List("job1", "job2")

    val spark = SparkSession
      .builder()
      .appName("SparkSessionExample")
      .config("spark.master", "local")
      .getOrCreate()

    spark.sparkContext.addSparkListener(new myListener(stack))
    import spark.implicits._

    val df = Seq(
      (8, "bat"),
      (64, "mouse"),
      (-27, "horse")
    ).toDF("number", "word")

    val df1 = df.groupBy(col("word")).agg(count(lit(1)) as "cnt").cache()
    df1.count()
    df1.collect()
    Thread.sleep(500000)
  }
}

class myListener(var stack: List[String]) extends SparkListener {
  override def onJobStart(jobStart: SparkListenerJobStart): Unit = {
    jobStart.properties.setProperty("spark.job.description", s"onJobStart: ${stack.head}")
    stack = stack.tail
  }
}