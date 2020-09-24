package com.tryout.spark.Ingest.impl.B

import com.tryout.spark.Ingest.getSession
import resource.managed

object BRun {
  def main(args: Array[String]): Unit = {
    managed(getSession).acquireAndGet(implicit spark => {
      new IngestB().start()
    })
  }
}
