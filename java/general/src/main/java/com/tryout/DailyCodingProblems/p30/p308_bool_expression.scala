package com.tryout.DailyCodingProblems.p30

import scala.collection.mutable.ArrayBuffer

// You are presented with an array representing a Boolean expression. The elements are of two kinds:
//
//T and F, representing the values True and False.
//&, |, and ^, representing the bitwise operators for AND, OR, and XOR.
//Determine the number of ways to group the array elements using parentheses so that the entire expression evaluates to True.
//
//For example, suppose the input is ["F", "|", "T", "&", "T"]. In this case, there are two acceptable groupings: (F | T) & T and F | (T & T).
object p308_bool_expression {

  def split(in: Array[String]): (Array[String],Array[String]) = {
    val operands = ArrayBuffer[String]()
    val operators = ArrayBuffer[String]()

    for(c<-in){
      if (Seq("T","F").contains(c)){
        operands+=c
      }else{
        operators+=c
      }
    }
    (operands.toArray, operators.toArray)
  }

  def execute(in: Array[String]): Boolean = {
    val(operands, operators) = split(in)

    val n = operands.size
    val T = ArrayBuffer.fill(n,n)(false)
    val F = ArrayBuffer.fill(n,n)(false)

    for(i<-0 until n){
      operands(i) match{
        case "T" => T(i)(i)=true
        case _ =>
      }
    }

    true
  }

  def main(args: Array[String]): Unit = {
    val in = Array("F", "|", "T", "&", "T")
    execute(in)
  }
}
