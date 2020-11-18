package abook_fp_2

object n_3_14_append {
  def main(args: Array[String]): Unit = {
    val list = myList(1, 2, 3, 4)

    val res = myList.appendViaFoldRight(list, Cons(5,Nil))
    println(res)
  }
}
