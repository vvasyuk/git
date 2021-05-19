trait Expr                                                              
case class Var(name: String) extends Expr                               
case class Num(number: Double) extends Expr                             
case class UnOp(operator: String, arg: Expr) extends Expr               
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr
object Main {

  def main(args: Array[String]): Unit = {
    println("Hello world!")
    println(msg)
  }

  def msg = "I was compiled by Scala 3. :)"

  def simplifyTop(expr: Expr): Expr =
    expr match
      case UnOp("-", UnOp("-", e)) => e // Double negation
      case BinOp("+", e, Num(0)) => e // Adding zero
      case BinOp("*", e, Num(1)) => e // Multiplying by one
      case _ => expr

  object Predef:
  type Map[A, +B] = collection.immutable.Map[A, B]
  type Set[A] = collection.immutable.Set[A]
  val Map = collection.immutable.Map
  val Set = collection.immutable.Set
  // ...
  end Predef
}

object Predef{
  type Map[A, +B] = collection.immutable.Map[A, B]
  type Set[A] = collection.immutable.Set[A]
  val Map = collection.immutable.Map
  val Set = collection.immutable.Set
}
sdf
fdsgzgf

