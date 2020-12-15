package general.abook_fp_2

import general.abook_fp_2.State.unit

case class State[S, +A](run: S => (A, S)) {
  def map[B](f: A => B): State[S, B] =
    flatMap(a => unit(f(a)))
  def map2[B,C](sb: State[S, B])(f: (A, B) => C): State[S, C] =
    flatMap(a => sb.map(b => f(a, b)))
  def flatMap[B](f: A => State[S, B]): State[S, B] = State(s => {
    val (a, s1) = run(s)
    f(a).run(s1)
  })
}

object State {
  def unit[S, A](a: A): State[S, A] =
    State(s => (a, s))

  def sequenceViaFoldRight[S,A](sas: List[State[S, A]]): State[S, List[A]] =
    sas.foldRight(unit[S, List[A]](List()))((f, acc) => f.map2(acc)(_ :: _))

//  def sequence[S, A](sas: List[State[S, A]]): State[S, List[A]] = {
//    def go(s: S, actions: List[State[S,A]], acc: List[A]): (List[A],S) =
//      actions match {
//        case Nil => (acc.reverse,s)
//        case h :: t => h.run(s) match { case (a,s2) => go(s2, t, a :: acc) }
//      }
//    State((s: S) => go(s,sas,List()))
//  }
//
//  def sequenceViaFoldLeft[S,A](l: List[State[S, A]]): State[S, List[A]] =
//    l.reverse.foldLeft(unit[S, List[A]](List()))((acc, f) => f.map2(acc)( _ :: _ ))

  def modify[S](f: S => S): State[S, Unit] = for {
    s <- get // Gets the current state and assigns it to `s`.
    _ <- set(f(s)) // Sets the new state to `f` applied to `s`.
  } yield ()

  def get[S]: State[S, S] = State(s => (s, s))

  def set[S](s: S): State[S, Unit] = State(_ => ((), s))
}