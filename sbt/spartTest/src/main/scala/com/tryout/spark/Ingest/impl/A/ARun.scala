//package com.tryout.spark.Ingest.impl.A
//
//import com.tryout.spark.Ingest.getSession
//import resource.managed
//
//object ARun {
//  def main(args: Array[String]): Unit = {
//    managed(getSession).acquireAndGet(implicit spark => {
//      import spark.implicits._
//      new IngestA().start()
//    })
//  }
//}
