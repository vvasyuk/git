package com.tryout.DailyCodingProblems

import scala.collection.mutable.ArrayBuffer

object aarrayBuffer {
  def main(args: Array[String]): Unit = {
    val x = ArrayBuffer[Int]()
    x.append(1)
    x.append(2)
    x.append(3)
    println(x)
    x.remove(x.size-1)
    println(x)

    val init =  ArrayBuffer(1,2,3).init
    println(init)
  }
}
