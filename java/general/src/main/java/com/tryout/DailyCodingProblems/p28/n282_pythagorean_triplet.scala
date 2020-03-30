package com.tryout.DailyCodingProblems.p28

import scala.annotation.tailrec

// Given an array of integers, determine whether it contains a Pythagorean triplet. Recall that a Pythogorean triplet (a, b, c) is defined by the equation a^2+ b^2= c^2.
object n282_pythagorean_triplet {
  //assume that a < b < c
  //let us assume that c is the last element in the array. Then the lowest possible value of a will be the first element, and the highest possible value of b will be the second-to-last element.
  // Now we repeatedly compare a + b against c, and perform the following:
  //
  //If a + b < c, move the index of a up in the list, to make our squared total higher.
  //If a + b > c, move the index of b down in the list, to make our squared total lower.
  //If a + b = c, return True, as we have found a solution.
  def main(args: Array[String]): Unit = {
    val in = Array(1,2,3,4,5,6)
    val arr = in.map(x=>x*x).sorted

    rec(arr,0,arr.size-2,arr.size-1)

  }
  @tailrec
  def rec(arr: Array[Int],a: Int, b: Int, c: Int): Unit = c match {
    case x if arr(x)==arr(a)+arr(b)=> println("" + arr(a) + ";" + arr(b) + ";" + arr(x))
    case x if arr(x)>arr(a)+arr(b) && a<b => rec(arr,a+1,b,x)
    case x if arr(x)<arr(a)+arr(b) && a<b => rec(arr,a,b-1,x)
    case x if a>=b && x>2 => rec(arr,0,x-2,x-1)
    case _ => println("nope")
  }

//  def rec(arr: Array[Int],a: Int, b: Int, c: Int): Unit = {
//    if(arr(a)+arr(b)==arr(c)){println("" + arr(a) + ";" + arr(b) + ";" + arr(c))}
//    if(arr(a)+arr(b)>arr(c) && a<b){rec(arr,a,b-1,c)}
//    if(arr(a)+arr(b)<arr(c) && a<b){rec(arr,a+1,b,c)}
//    if (a>=b && c>2){
//      rec(arr,0,c-2,c-1)
//    }else{
//      return
//    }
//  }
}

//    array = sorted([x ** 2 for x in array])
//
//    for c in range(len(array) - 1, 1, -1):
//        a, b = 0, c - 1
//
//        while a < b:
//            if array[a] + array[b] == array[c]:
//                return True
//            elif array[a] + array[b] < array[c]:
//                a += 1
//            else:
//                b -= 1