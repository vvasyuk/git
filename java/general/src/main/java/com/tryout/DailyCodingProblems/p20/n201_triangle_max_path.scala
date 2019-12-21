package com.tryout.DailyCodingProblems.p20

import scala.collection.mutable.ArrayBuffer

object n201_triangle_max_path {

  def execute(triangle: Array[ArrayBuffer[Int]]):Unit={
    Range(triangle.size-2, -1, -1).foreach(row=>{
      Range(0,row+1).foreach(col=>{
        triangle(row)(col)=triangle(row)(col)+Math.max(triangle(row+1)(col),triangle(row+1)(col+1))
      })
    })

    println("max path sum: " + triangle(0)(0))
  }

  def main(args: Array[String]):Unit={
    execute(Array(ArrayBuffer(1),ArrayBuffer(2,3),ArrayBuffer(1,5,1)))
  }
}
