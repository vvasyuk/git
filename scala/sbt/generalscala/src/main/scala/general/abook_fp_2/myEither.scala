package general.abook_fp_2

sealed trait myEither[+E, +A]{
  def map[B](f: A => B): myEither[E, B] = this match {
    case Left(e) => Left(e)
    case Right(r) => Right(f(r))
  }

  def flatMap[EE >: E, B](f: A => myEither[EE, B]): myEither[EE, B] = this match {
    case Left(e) => Left(e)
    case Right(r) => f(r)
  }

  def orElse[EE >: E,B >: A](b: => myEither[EE, B]): myEither[EE, B] = this match {
    case Left(_) => b
    case Right(a) => Right(a)
  }
  def map2[EE >: E, B, C](b: myEither[EE, B])(f: (A, B) => C): myEither[EE, C] = {
    for { a <- this; b1 <- b } yield f(a,b1)
  }
}
case class Left[+E](value: E) extends myEither[E, Nothing]
case class Right[+A](value: A) extends myEither[Nothing, A]