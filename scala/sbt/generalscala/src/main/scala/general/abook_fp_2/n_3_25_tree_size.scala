package general.abook_fp_2

object n_3_25_tree_size {
  def main(args: Array[String]): Unit = {
    val lev2l1 = Leaf("a")
    val lev2l2 = Leaf("b")
    val lev2l3 = Leaf("c")
    val lev2l4 = Leaf("d")

    val lev1b1 = Branch(lev2l1, lev2l2)
    val lev1b2 = Branch(lev2l3, lev2l4)

    val root = Branch(lev1b1, lev1b2)

    println(size(root))
  }

  def size[A](t: myTree[A]): Int = t match {
    case Leaf(_) => 1
    case Branch(l,r) => 1+size(l)+size(r)
  }
}
