package general.abook_fp_2

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

  def drop[A](l: myList[A], n: Int): myList[A] = {
    if (n <= 0) l
    else l match {
      case Nil => Nil
      case Cons(_,t) => drop(t, n-1)
    }
  }

  def dropWhile[A](l: myList[A], f: A => Boolean): myList[A] = l match {
    case Cons(h,t) if f(h) => dropWhile(t, f)
    case _ => l
  }

  def append[A](a1: myList[A], a2: myList[A]): myList[A] = a1 match {
      case Nil => a2
      case Cons(h,t) => Cons(h, append(t, a2))
  }

  def init[A](l: myList[A]): myList[A] =   l match {
    case Nil => sys.error("init of empty list")
    case Cons(_,Nil) => Nil
    case Cons(h,t) => Cons(h,init(t))
  }

  def apply[A](as: A*): myList[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  def foldRight[A,B](as: myList[A], z: B)(f: (A, B) => B): B =as match {
    case Nil => z
    case Cons(x, xs) => f(x, foldRight(xs, z)(f))
  }

  def length(as: myList[Int]): Int = {
    myList.foldRight(as, 0)((x,y) => 1 + y)
  }

  def foldLeft[A,B](l: myList[A], z: B)(f: (B, A) => B): B = l match {
    case Nil => z
    case Cons(h,t) => foldLeft(t, f(z,h))(f)
  }

  def reverse[A](l: myList[A]): myList[A] = myList.foldLeft(l, myList[A]())((acc,h) => Cons(h,acc))

  def appendViaFoldRight[A](l: myList[A], r: myList[A]): myList[A] = foldRight(l, r)(Cons(_,_))

  def map[A,B](l: myList[A])(f: A => B): myList[B] = {
    val buf = new collection.mutable.ListBuffer[B]
    def go(l: myList[A]): Unit = l match {
      case Nil => ()
      case Cons(h,t) => buf += f(h); go(t)
    }
    go(l)
    myList(buf.toList: _*) // converting from the standard Scala list to the list we've defined here
  }

  def concat[A](l: myList[myList[A]]): myList[A] =
    foldRight(l, Nil:myList[A])(append)

  def flatMap[A,B](as: myList[A])(f: A => myList[B]): myList[B] = {
    myList.concat(myList.map(as)(f))
  }
}
