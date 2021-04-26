package general.abasics.bitOps

object xorTest {
  def main(args: Array[String]): Unit = {
    val a = 2
    val b = 2
    val c = 4
    val d = 4
    val e = 5

    val x = a^b^c^d^e   // order is not important
                        // xor trick -> duplicates are removed

    // x^0 = x
    // x^x = 0
    println(x)
  }
}
