package dcp

object q002_products {
  def main(args: Array[String]): Unit = {
    val input = Array(1, 2, 3, 4, 5)
    println(products(input).mkString(","))
  }

  def products(ints: Array[Int]): Array[Int] =
    val totalProduct = ints.foldLeft(1){(acc,v) => acc*v}
    ints.map(totalProduct/_)
}
