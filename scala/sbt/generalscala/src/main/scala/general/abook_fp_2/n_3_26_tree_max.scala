package general.abook_fp_2

object n_3_26_tree_max {
  def main(args: Array[String]): Unit = {
    val lev2l1 = Leaf(1)
    val lev2l2 = Leaf(2)
    val lev2l3 = Leaf(3)
    val lev2l4 = Leaf(4)

    val lev1b1 = Branch(lev2l1, lev2l2)
    val lev1b2 = Branch(lev2l3, lev2l4)

    val root = Branch(lev1b1, lev1b2)

    println(maxi(root))
  }
  def maxi(t: myTree[Int]): Int = t match {
    case Leaf(n) => n
    case Branch(l,r) => maxi(l) max maxi(r)
  }
}
