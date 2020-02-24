package com.tryout.DailyCodingProblems.p27

import scala.collection.mutable.ArrayBuffer

object n271_find_with_fibonacci {

  def main(args: Array[String]): Unit = {
    val x=11
    var arr = Array(4, 7, 11, 16, 27, 45, 55, 65, 80, 100)
    var n=arr.size
    val fibs=getFibSequence(arr.size)

    var offset=0
    var p=fibs.size-2
    var q=fibs.size-1

    while(p>0){
      var idx=Math.min(offset+fibs(p), n-1)
      if (x==arr(idx)){
        println("found")
      }else if(x<arr(idx)){
        p -= 2; q -= 2
      }else{
        p -= 1; q -= 1
        offset = idx
      }
    }

  }

  def getFibSequence(n:Int):ArrayBuffer[Int]={
    val res = new ArrayBuffer[Int]()
    var a = 0
    var b = 1
    res+=a

    while (a<n){
      val tmp=a
      a=b
      b=tmp+b
      res+=a
    }

    res
  }
}

// offset = 0
//    p, q = len(fibs) - 2, len(fibs) - 1
//
//    while q > 0:
//        index = min(offset + fibs[p], n - 1)
//        if x == array[index]:
//            return True
//        elif x < array[index]:
//            p -= 2; q -= 2
//        else:
//            p -= 1; q -= 1
//            offset = index


