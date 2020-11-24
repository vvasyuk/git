package general.abook_fp_2

object n_3_11_sumProduct {
  def main(args: Array[String]): Unit = {
    val list = myList(1, 2, 3, 4)

    val sum = myList.foldLeft(list, 0)(_ + _)
    println(sum)

    val product = myList.foldLeft(list, 1)(_ * _)
    println(product)

    val size = myList.foldLeft(list, 0)((x,y)=>x + 1)
    println(size)
  }
}
