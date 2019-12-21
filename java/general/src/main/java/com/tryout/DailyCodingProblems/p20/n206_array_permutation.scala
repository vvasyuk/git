package com.tryout.DailyCodingProblems.p20

import scala.collection.mutable.ArrayBuffer

object n206_array_permutation {

  def executeConstantSpace(arr: ArrayBuffer[String], permutation: Array[Int]) = {
    //ArrayBuffer("a","b","c","d"), Array(3, 0, 2, 1)  // should be ("d","a","c","b")
    for(n<-Range(0,arr.size)){
      var element = arr(n)
      var p = permutation(n)
      var idx = n

      while(n!=p){
        arr(idx) = arr(p)
        permutation(idx)=idx
        idx = p
        p = permutation(p)

      }
      arr(idx)=element
      permutation(idx)=idx
    }
    arr.foreach(println(_))
  }

//  def executeConstantSpace(arr: ArrayBuffer[String], permutation: Array[Int]) = {
//    //ArrayBuffer("a","b","c","d"), Array(3, 0, 2, 1)
//    for(n<-Range(0,arr.size)){
//      var p = permutation(n)
//      var element = arr(n)
//
//      while (n!=p){
//        val tmp = arr(p)
//        arr(p) = element
//        element=tmp
//
//        val tmpP = permutation(p)
//        permutation(p)=p
//        p = tmpP
//      }
//      arr(n) = element
//      permutation(n)=p
//    }
//    arr.foreach(println(_))
//  }

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
//  array[p], element = element, array[p]
//  permutation[p], p = p, permutation[p]
//
//array[i], permutation[i] = element, p
//return array


//arr: [A, B, C]	// [C, A, B] e=A
//per: [2, 0, 1]
//
//arr: [A, B, A]  e=C
//per: [2, 0, 2]  p=1
//
//arr: [A, C, A]  e=B
//per: [2, 0, 2]  p=1


