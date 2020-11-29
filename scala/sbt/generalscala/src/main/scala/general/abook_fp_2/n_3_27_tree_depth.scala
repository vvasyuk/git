package general.abook_fp_2

object n_3_27_tree_depth {
  def main(args: Array[String]): Unit = {
    val lev2l1 = Leaf(1)
    val lev2l2 = Leaf(2)
    val lev2l3 = Leaf(3)
    val lev2l4 = Leaf(4)

    val lev1b1 = Branch(lev2l1, lev2l2)
    val lev1b2 = Branch(lev2l3, lev2l4)

    val root = Branch(lev1b1, lev1b2)

    println(depth(root, 0))
  }

  def depth[A](t: myTree[A], currentDepth: Int): Int = t match {
    case Leaf(_) => currentDepth+1
    case Branch(l,r) => depth(l, currentDepth+1) max depth(r, currentDepth+1)
  }
}
