package general.abook_handsOn

object a5_5_exsz_1_EXPR_pattern_match {
//  Exercise: Define a function that uses pattern matching on the Exprs we saw earlier to perform
//  simple algebraic simplifications:
//    See example 5.7 - Simplify
//  (1 + 1)           // 2
//  ((1 + 1) * x)     // (2 * x)
//  ((2 - 1) * x)     // x
//    (((1 + 1) * y) + ((1 - 1) * x))   // (2 * y)


  def main(args: Array[String]): Unit = {
    val t1 = BinOp(Literal(1), "+", Literal(1))
    val t2 = BinOp(BinOp(Literal(1), "+", Literal(1)), "*", Variable("x"))
    val t3 = BinOp(BinOp(Literal(2), "-", Literal(1)), "*", Variable("x"))
    val t4 = BinOp(
      BinOp(BinOp(Literal(1), "+", Literal(1)), "*", Variable("y")),
      "+",
      BinOp(BinOp(Literal(1), "-", Literal(1)), "*", Variable("x")),
    )


    println(simplify(t1))
    println(simplify(t2))
    println(simplify(t3))
    println(simplify(t4))

  }

  def simplify(expr: Expr): Expr = {
    val res = expr match{
      case BinOp(Literal(left), "+", Literal(right)) => Literal(left + right)
      case BinOp(Literal(left), "-", Literal(right)) => Literal(left - right)
      case BinOp(Literal(left), "*", Literal(right)) => Literal(left * right)

      case BinOp(left, "*", Literal(1)) => simplify(left)
      case BinOp(Literal(1), "*", right) => simplify(right)

      case BinOp(left, "+", Literal(0)) => simplify(left)
      case BinOp(Literal(0), "+", right) => simplify(right)

      case BinOp(left, "-", Literal(0)) => simplify(left)

      case BinOp(left, "*", Literal(0)) => Literal(0)
      case BinOp(Literal(0), "*", right) => Literal(0)

      case BinOp(left, "+", right) => BinOp(simplify(left), "+", simplify(right))
      case BinOp(left, "-", right) => BinOp(simplify(left), "-", simplify(right))
      case BinOp(left, "*", right) => BinOp(simplify(left), "*", simplify(right))

      case Literal(value) => Literal(value)
      case Variable(name) => Variable(name)
    }
    if (res == expr) res
    else simplify(res)
  }
}

sealed trait Expr
case class BinOp(left: Expr, op: String, right: Expr) extends Expr
case class Literal(value: Int) extends Expr
case class Variable(name: String) extends Expr


