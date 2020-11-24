package general.abook_fp_2

object n_3_18_map {
  def main(args: Array[String]): Unit = {
  val list = myList(1, 2, 3, 4)

  val res = map(list)(x=>x+2)
  println(res)
}
  def map[A,B](as: myList[A])(f: A => B): myList[B] = {
  myList.foldRight(as, myList[B]())((l,acc)=>Cons(f(l),acc))
  }

  def map_2[A,B](l: myList[A])(f: A => B): List[B] = {
    val buf = new collection.mutable.ListBuffer[B]
    def go(l: myList[A]): Unit = l match {
      case Nil => ()
      case Cons(h,t) => buf += f(h); go(t)
    }
    go(l)
    List(buf.toList: _*) // converting from the standard Scala list to the list we've defined here
  }
}
