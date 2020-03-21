package com.tryout.DailyCodingProblems.p27

import scala.collection.mutable

object n274_eval_string {

  def main(args: Array[String]): Unit = {
    val in = List("1", "-", "(", "2", "+", "3", ")")
    val ops = mutable.Stack[Char]()
    val values = mutable.Stack[Int]()

    parse(in, ops, values)
    eval(ops, values)
    println(values.pop())
  }

  def parse(in: List[String], ops: mutable.Stack[Char], values: mutable.Stack[Int]): Unit = {
    in.mkString("").toCharArray.foreach(c=>c match{
      case i if i.isDigit => values.push(i.asDigit)
      case '+' => ops.push(c)
      case '-' => ops.push(c)
      case '(' => ops.push(c)
      case ')' => ops.push(c)
      case _ =>
    })
  }
  // 1+(1+1)
  // 1+(1+1)+1
  def eval(ops: mutable.Stack[Char], values: mutable.Stack[Int]) = {
    while(ops.nonEmpty){
      var op = ops.pop()
      if (op == ')') {
        ops.popWhile(_ != '(').foreach(x => {
          oneOp(values, x)
        })
        ops.pop()
      }else{
        oneOp(values, op)
      }
  }
}

  def oneOp(values: mutable.Stack[Int], op: Char)={
    val v1 = values.pop()
    val v2 = values.pop()
    if (op=='+'){
      values.push(v1+v2)
    }else{
      values.push(v2-v1)
    }
  }
}
