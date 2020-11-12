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

    // pass a function to State that
    // accept state as parameter to generate new value and new state
    val s = State( (state:Int) => {
      val value = state*10
      val newState = state+1
      (value, newState)})

    println(s.run(1))
    println(s.run(1))

    val s1 = s.map(_ + 1).map(_ + 1).map(_ + 1)
    println(s1.run(1))

  }
}