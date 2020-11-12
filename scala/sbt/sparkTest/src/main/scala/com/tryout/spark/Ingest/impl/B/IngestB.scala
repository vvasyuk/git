package com.tryout.spark.Ingest.impl.B

import com.tryout.spark.Ingest.AbstractIngest
import com.tryout.spark.Ingest.model.A.{AIn, AOut}
import org.apache.spark.sql.{DataFrame, Encoder, SparkSession}

class IngestB(implicit spark: SparkSession) extends AbstractIngest(AIn.getSchema){
  def enrich(df: DataFrame): DataFrame ={
    import spark.implicits._
    df.as[AIn].mapPartitions(x => new BMapper("yyyy-MM-dd HH:mm:ss z").map(x)).toDF()
  }
}


//class IngestB(implicit spark: SparkSession, enc1: Encoder[AIn], enc2: Encoder[AOut]) extends AbstractIngest[AIn, AOut]{
//  def enrich(df: DataFrame): DataFrame ={
//    df.as[AIn].mapPartitions(x => new BMapper("yyyy-MM-dd HH:mm:ss z").map(x)).toDF()
//  }
//}