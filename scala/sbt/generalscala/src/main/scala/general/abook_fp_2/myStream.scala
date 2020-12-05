package general.abook_fp_2

import general.abook_fp_2.myStream.cons

sealed trait myStream[+A]{
  def headOption: myOption[A] = this match {
    case StrEmpty => None
    case StrCons(h, t) => Some(h())
  }

  def toListFast: List[A] = {
    val buf = new collection.mutable.ListBuffer[A]
    @annotation.tailrec
    def go(s: myStream[A]): List[A] = s match {
      case StrCons(h,t) =>
        buf += h()
        go(t())
      case _ => buf.toList
    }
    go(this)
  }

  def take(n: Int): myStream[A] = this match {
    case StrCons(h, t) if n > 1 => cons(h(), t().take(n - 1))
    case StrCons(h, _) if n == 1 => cons(h(), myStream.empty)
    case _ => myStream.empty
  }

  @annotation.tailrec
  final def drop(n: Int): myStream[A] = this match {
    case StrCons(_, t) if n > 0 => t().drop(n - 1)
    case _ => this
  }

  def exists(p: A => Boolean): Boolean = this match {
    case StrCons(h, t) => p(h()) || t().exists(p)
    case _ => false
  }

  def exists2(p: A => Boolean): Boolean =
    foldRight(false)((a, b) => p(a) || b)

  def foldRight[B](z: => B)(f: (A, => B) => B): B = this match {
      case StrCons(h,t) => f(h(), t().foldRight(z)(f))
      case _ => z
  }
}

case object StrEmpty extends myStream[Nothing]
case class StrCons[+A](h: () => A, t: () => myStream[A]) extends myStream[A]

object myStream {
  def cons[A](hd: => A, tl: => myStream[A]): myStream[A] = {
    lazy val head = hd
    lazy val tail = tl
    StrCons(() => head, () => tail)
  }

  def empty[A]: myStream[A] = StrEmpty
  def apply[A](as: A*): myStream[A] =
    if (as.isEmpty) empty else cons(as.head, apply(as.tail: _*))
}