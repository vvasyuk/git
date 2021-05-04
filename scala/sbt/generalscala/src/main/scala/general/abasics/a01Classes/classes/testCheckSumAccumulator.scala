package general.abasics.a01Classes.classes

object testCheckSumAccumulator {
  def main(args: Array[String]): Unit = {
    val acc = new CheckSumAccumulator
    println(acc.checksum())
    acc.add(2)
    println(acc.checksum())

    println(CheckSumAccumulator.calculate("test"))
    println(CheckSumAccumulator.calculate("test"))
  }
}
