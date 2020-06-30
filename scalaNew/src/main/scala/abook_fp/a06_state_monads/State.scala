package abook_fp.a06_state_monads

case class State[S, +A](run: S => (A, S)) {
  def map[B](f: A => B): State[S, B] = State((s: S) => {
    val (a, ss) = run(s)
    (f(a), ss)
  })
  def map2[B, C](sb: State[S, B])(f: (A, B) => C): State[S, C] = State((s: S) => {
    val (a, ss) = run(s)
    val (b, sss) = sb.run(ss)
    (f(a, b), sss)
  })
  def flatMap[B](f: A => State[S, B]): State[S, B] = State((s: S) => {
    val (a, ss) = run(s)
    f(a).run(ss)
  })
}

object State {
  type Rand[A] = State[RNG, A]

  def unit[S, A](a: A): State[S, A] = State((s: S) => (a, s))

  def sequence[S, A](fs: List[State[S, A]]): State[S, List[A]] =
    fs.foldRight(unit[S, List[A]](List()))((s, sl) => {s.map2(sl)(_ :: _)})

  def get[S]: State[S, S] = State(s => (s, s))

  def set[S](s: S): State[S, Unit] = State(_ => ((), s))

  def modify[S](f: S => S): State[S, Unit] = for {
    s <- get
    _ <- set(f(s))
  } yield ()
}

object testState{
  import State._
  def main(args: Array[String]): Unit = {
    val x = unit[Int,Int](1)
    val x1 = x.map(_ + 1).map(_ + 1)
    val x2 = x1.run
    val x3 = x2(5)
    println(x3)


  }
}