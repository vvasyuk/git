package abook_fp_2

sealed trait myList[+A]{
  def isEmpty: Boolean
  def head: A
  def tail: myList[A]
}
case object Nil extends myList[Nothing] {
  override def isEmpty: Boolean = true
  override def head: Nothing = throw new Exception
  override def tail: myList[Nothing] = throw new Exception
}
case class Cons[+A](head: A, tail: myList[A]) extends myList[A] {
  override def isEmpty: Boolean = false
}

object myList {
  def sum(ints: myList[Int]): Int = ints match {
    case Nil => 0
    case Cons(x,xs) => x + sum(xs)
  }
  def product(ds: myList[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x,xs) => x * product(xs)
  }

  def tail[A](l: myList[A]): myList[A] = l match {
      case Nil => sys.error("tail of empty list")
      case Cons(_,t) => t
  }

  def setHeader[A](l: myList[A], header: A): myList[A] = l match {
    case Nil => Cons(header, Nil)
    case Cons(_,t) => Cons(header, t)
  }

  def apply[A](as: A*): myList[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))
}
