package general.abook_fp_2

object n_3_21_filter_flatMap {
  def main(args: Array[String]): Unit = {
    val list = myList(1, 2, 3, 4)

    val res = filter(list)(x=>x<3)
    println(res)
  }

  def filter[A](as: myList[A])(f: A => Boolean): myList[A] = {
    myList.flatMap(as)(x=>if (f(x)) myList(x) else Nil)
  }
}
