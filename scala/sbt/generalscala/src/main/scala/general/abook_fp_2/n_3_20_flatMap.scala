package general.abook_fp_2

object n_3_20_flatMap {
  def main(args: Array[String]): Unit = {
    val list = myList(1, 2, 3, 4)

    val res = flatMap(list)(x=>myList(x, x))
    println(res)
  }

  def flatMap[A,B](as: myList[A])(f: A => myList[B]): myList[B] = {
    myList.concat(myList.map(as)(f))
  }
}
