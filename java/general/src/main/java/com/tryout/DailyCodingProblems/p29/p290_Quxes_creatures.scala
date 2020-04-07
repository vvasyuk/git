package com.tryout.DailyCodingProblems.p29

import scala.collection.immutable

// On a mysterious island there are creatures known as Quxes which come in three colors: red, green, and blue. One power of the Qux is that if two of them are standing next to each other, they can transform into a single creature of the third color.
//
//Given N Quxes standing in a line, determine the smallest number of them remaining after any possible sequence of such transformations.
//
//For example, given the input ['R', 'G', 'B', 'G', 'B'], it is possible to end up with a single Qux through the following steps:
//
//        Arrangement       |   Change
//----------------------------------------
//['R', 'G', 'B', 'G', 'B'] | (R, G) -> B
//['B', 'B', 'G', 'B']      | (B, G) -> R
//['B', 'R', 'B']           | (R, B) -> G
//['B', 'G']                | (B, G) -> R
//['R']                     |
object p290_Quxes_creatures {

  def execute(in: Array[Char]): Int = {
    val counts = immutable.Map('R'->0, 'G'->0, 'B'->0)
    in.foreach(x=>{
      counts.updated(x, counts(x)+1)
    })

    if (in.toSet.size==1){
      in.size
    }else if(counts('R')%2==counts('G')%2==counts('B')%2){
      2
    }else{
      1
    }

  }

  def main(args: Array[String]): Unit = {
    val in = Array('R', 'G', 'B', 'G', 'B')
    val in2 = Array('R', 'R', 'R', 'R', 'R')

    println(execute(in))
  }
}
