package com.tryout.DailyCodingProblems

import scala.collection.mutable.ArrayBuffer

object n206_array_permutation {

  def executeConstantSpace(arr: ArrayBuffer[String], permutation: Array[Int]) = {

    for(n<-Range(0,arr.size)){
      var p = permutation(n)
      var element = arr(n)

      while (n!=p){
        var tmp = arr(p)
        arr(p) = element            // element = d
        element=tmp                 // ("a","b","c","a")

        var tmpP = permutation(p)
        permutation(p) = p          // p = 1
        p=tmpP                      // (3, 0, 2, 3)
      }
      arr(n) = element
      permutation(n) = p
    }
    arr.foreach(println(_))
  }

  def main(args: Array[String]):Unit={
    //execute(Array("a","b","c"), Array(2,1,0))
    executeConstantSpace(ArrayBuffer("a","b","c","d"), Array(3, 0, 2, 1))
  }

  def execute(arr: Array[String], permutation:Array[Int]):Unit={
    var tmp = ArrayBuffer.fill(arr.size)("")

    permutation.zipWithIndex.foreach(x=>{
      tmp(x._2)=arr(x._1)
    })
    tmp.foreach(println(_))
  }
}


//def permute(array, permutation):
//for i in range(len(array)):
//element, p = array[i], permutation[i]
//
//while p != i:
//array[p], element = element, array[p]
//permutation[p], p = p, permutation[p]
//
//array[i], permutation[i] = element, p
//return array