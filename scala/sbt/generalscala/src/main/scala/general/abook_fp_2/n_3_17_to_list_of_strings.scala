package general.abook_fp_2

object convertn_3_17_to_list_of_strings {
  def main(args: Array[String]): Unit = {
    val list = myList(1, 2, 3, 4)

    val res = myList.foldRight(list, myList[String]())((l,acc)=>Cons(l.toString,acc))
    println(res)
  }
}
