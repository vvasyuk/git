package com.tryout.DailyCodingProblems.P18

import scala.collection.mutable.ArrayBuffer

object n186_subsets_combination_min_diff {
  def combinations(inArray: Array[Int], accum: Array[Int], res: ArrayBuffer[Array[Int]], length: Int):Unit={
    if (inArray.nonEmpty){
      val tempAcc = accum:+inArray.head
      if (accum.size == length-1){
        res.append(tempAcc)
        combinations(inArray.tail, accum, res, length)
      }else{
        combinations(inArray.tail, tempAcc, res, length)
        combinations(inArray.tail, Array(), res, length)
      }
    }
  }

  def main(args: Array[String]): Unit = {
    println("test started")
    val a = Array(5, 10, 15, 20, 25)
    val combs: ArrayBuffer[Array[Int]] = ArrayBuffer()

    for(n <- Range(0,a.size)){
      combinations(a, Array(), combs, n)
    }
    //create two subsets
    val sets = combs.map(x=> (x, a.diff(x)))
    //combs.foreach(x=>println(x.mkString(",")))
    sets.foreach(x=>println(x._1.mkString(",") + " : " + x._2.mkString(",")))

    //calculate min subsets
    val answer = sets.minBy(x=>Math.abs(x._1.sum-x._2.sum))
    println("answer: " + answer._1.mkString(",") + " : " + answer._2.mkString(","))

  }
}
