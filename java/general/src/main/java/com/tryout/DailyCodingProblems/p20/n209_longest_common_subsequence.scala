package com.tryout.DailyCodingProblems.p20

import scala.collection.mutable.ArrayBuffer

// Write a program that computes the length of the longest common subsequence of three given strings.
// For example, given "epidemiologist", "refrigeration", and "supercalifragilisticexpialodocious",
// it should return 5, since the longest common subsequence is "eieio".
object n209_longest_common_subsequence {


  def execute(str1: String, str2: String): Unit = {

    val matrix = ArrayBuffer.fill(str2.size+2)(ArrayBuffer.fill(str1.size+1)(0))

    str1.zipWithIndex.foreach(x=>{
      str2.zipWithIndex.foreach(y=>{
        //println(x._1 + " ? " + y._1)
        if(x._1==y._1) {
          matrix(x._2+1)(y._2+1) = matrix(x._2)(y._2)+1
        }else{
          matrix(x._2+1)(y._2+1) = Math.max(matrix(x._2+1)(y._2),matrix(x._2)(y._2+1))
        }
      })
    })
    matrix.foreach(x=>{
      println()
      x.foreach(print(_))
    })

    println("\nmax: " + matrix(matrix.size-1)(matrix(0).size-1))
  }

  def main(args: Array[String]):Unit={
    execute("epidemiologist", "refrigeration")
    //execute("qwert", "zwcxe")
  }
}
