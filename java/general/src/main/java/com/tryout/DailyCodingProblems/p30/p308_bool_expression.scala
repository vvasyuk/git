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

  def execute(in: Array[String]): Int = {
    val(operands, operators) = split(in)

    val n = operands.size
    val T = ArrayBuffer.fill(n,n)(0)
    val F = ArrayBuffer.fill(n,n)(0)

    for(i<-0 until n){
      operands(i) match{
        case "T" => {T(i)(i)=1; F(i)(i)=0}
        case _ => {T(i)(i)=0; F(i)(i)=1}
      }
    }

    for(gap <- 1 until n;
        i <- 0 until n-gap;
        j = i + gap;
        k <- i until j
      // first 3 lines  if n = 5
      //0:1
      //1:2
      //2:3
      //3:4
      //0:2
      //1:3
      //2:4
      //0:3
      //1:4
      //0:4
        ){
      val allOptions = (T(i)(k) + F(i)(k))*(T(k+1)(j) + F(k+1)(j))

      ////        A   B   A|B A&B A^B ~A
      ////        0   0   0   0   0   1
      ////        1   0   1   0   1   0
      ////        0   1   1   0   1   1
      ////        1   1   1   1   0   0

      //T       F
      //1 0 0   1 0 0
      //0 1 0   0 0 0
      //0 0 1   0 0 0
      operators(k) match {
        case "&" =>{
          T(i)(j)=T(i)(j)+(T(i)(k)*T(k+1)(j))
          F(i)(j)=F(i)(j)+(allOptions - T(i)(j))
        }
        case "|" =>{
          F(i)(j)=F(i)(j)+(F(i)(k) * F(k+1)(j))
          T(i)(j)=T(i)(j)+(allOptions - F(i)(j))
        }
        case "^" =>{
          T(i)(j)=T(i)(j)+(F(i)(k) * T(k+1)(j) + T(i)(k) * F(k+1)(j))
          F(i)(j)=F(i)(j)+(T(i)(k) * T(k+1)(j) + F(i)(k) * F(k+1)(j))
        }
      }
    }

    T(0)(n-1)
  }

  def main(args: Array[String]): Unit = {
    val in = Array("F", "|", "T", "&", "T")
    println(execute(in))

  }
}
