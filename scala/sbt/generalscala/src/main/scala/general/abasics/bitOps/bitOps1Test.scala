package general.abasics.bitOps

object bitOps1Test {
  def main(args: Array[String]): Unit = {
    assert((1&2) == 0)    // 1 (0001) and 2 (0010) = 0 (0000)
    assert((1|2) == 3)    // 1 (0001) and 2 (0010) = 3 (0011)
    assert((1^3) == 2)    // 1 (0001) and 3 (0011) = 2 (0010)
    assert((~1) == -2)    // inverts each bit in 1 (0001), yielding -2, which in binary looks like 11111111111111111111111111111110.
    assert((1 << 2) == 4)
  }
}
