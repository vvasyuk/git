package com.tryout.DailyCodingProblems.p20

//A Collatz sequence in mathematics can be defined as follows. Starting with any positive integer:
//if n is even, the next number in the sequence is n / 2
//if n is odd, the next number in the sequence is 3n + 1
//It is conjectured that every such sequence eventually reaches the number 1. Test this conjecture.
//Bonus: What input n <= 1000000 gives the longest sequence?

object n210_collatz_sequence {

  def execute(v: Int): Unit = {
    var n = v
    if(n==1){
      println("true")
      return
    }

    while (n!=1){
      if(n%2==0){
        n=n/2
      }else{
        n=3*n+1
      }
    }
    println("n: " + n + " true")
  }

  def collatz_length(v: Int): Int = {
    var n = v
    var length = 0
    while (n!=1){
      if(n%2==0){
        n=n/2
        length+=1
      }else{
        n=3*n+1
        length+=1
      }
    }
    length
  }

  def main(args: Array[String]):Unit={
    //execute(5)
    //println("length: " + collatz_length(5))
    var max = (0,0)
    //Range(1,1000000).foreach(x=>{
    Range(1,1000).foreach(x=>{
      max = (Math.max(collatz_length(x),max._1), x)
    })

    println("length: " + max._1 + " int: " + max._2)
  }
}
