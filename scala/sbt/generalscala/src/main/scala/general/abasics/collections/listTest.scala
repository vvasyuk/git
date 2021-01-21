package general.abasics.collections

object listTest {
  def main(args: Array[String]): Unit = {
    // Builders
    val empty = List() // or Nil
    val l = List(1,2,3)
    val l1 = 1::2::3::Nil
    assert(l==l1)

    // Factory Methods
    // Transforms
    // Queries
    // Aggregations
  }
}
