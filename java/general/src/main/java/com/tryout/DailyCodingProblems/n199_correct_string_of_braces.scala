package com.tryout.DailyCodingProblems

import scala.Char
import scala.collection.mutable.ArrayBuffer

object n199_correct_string_of_braces {


  def execute(value: String): Unit = {
    val res = ArrayBuffer[Char]()
    var open_brackets = 0

    value.foreach(x=>{
      x match{
        case '(' => {
          res.append('(')
          open_brackets+=1
        }
        case ')' => {
          if(open_brackets>0){
            open_brackets-=1
            res.append(')')
          }
          //no need to cover case when char is ) and open_brackets <0 - it will just no be added to res
        }
      }
    })

    println("open brackets: " + open_brackets)
    while (open_brackets>0){
      open_brackets-=1
      res.append(')')
    }
    res.foreach(println(_))
  }

  def main(args: Array[String]):Unit={
    execute(")(()")
  }
}
