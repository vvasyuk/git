package general.abook_fp_2

sealed trait myOption[+A] {
  def map[B](f: A => B): myOption[B] = this match {
    case `myNone` => myNone
    case mySome(x) => mySome(f(x))
  }
  def getOrElse[B >: A](default: => B): B = this match {
    case `myNone` => default
    case mySome(x) => x
  }
  def flatMap[B](f: A => myOption[B]): myOption[B] = {
    map(f).getOrElse(myNone)
  }
  def flatMap_1[B](f: A => myOption[B]): myOption[B] = this match {
    case `myNone` => myNone
    case mySome(a) => f(a)
  }

  def orElse[B>:A](ob: => myOption[B]): myOption[B] =
    this map (mySome(_)) getOrElse ob
  def orElse_1[B>:A](ob: => myOption[B]): myOption[B] = this match {
    case `myNone` => ob
    case _ => this
  }

  def filter_1(f: A => Boolean): myOption[A] =
    flatMap(a => if (f(a)) mySome(a) else myNone)
  def filter(f: A => Boolean): myOption[A] = this match {
    case mySome(a) if f(a) => this
    case _ => myNone
  }
}

case class mySome[+A](get: A) extends myOption[A]
case object myNone extends myOption[Nothing]
