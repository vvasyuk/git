object test{
def simplifyTop(expr: Expr): Expr = expr match
case UnOp("-", UnOp("-", e)) => e // Double negation
case BinOp("+", e, Num(0)) => e // Adding zero
case BinOp("*", e, Num(1)) => e // Multiplying by one
case _ => expr
}