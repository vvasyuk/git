package general.abook_fp_2

sealed trait myOption[+A] {
  def map[B](f: A => B): myOption[B] = this match {
    case None => None
    case Some(x) => Some(f(x))
  }
  def getOrElse[B >: A](default: => B): B = this match {
    case None => default
    case Some(x) => x
  }
  def flatMap[B](f: A => myOption[B]): myOption[B] = {
    map(f).getOrElse(None)
  }
  def flatMap_1[B](f: A => myOption[B]): myOption[B] = this match {
    case None => None
    case Some(a) => f(a)
  }

  def orElse[B>:A](ob: => myOption[B]): myOption[B] =
    this map (Some(_)) getOrElse ob
  def orElse_1[B>:A](ob: => myOption[B]): myOption[B] = this match {
    case None => ob
    case _ => this
  }

  def filter_1(f: A => Boolean): myOption[A] =
    flatMap(a => if (f(a)) Some(a) else None)
  def filter(f: A => Boolean): myOption[A] = this match {
    case Some(a) if f(a) => this
    case _ => None
  }
}

case class Some[+A](get: A) extends myOption[A]
case object None extends myOption[Nothing]
