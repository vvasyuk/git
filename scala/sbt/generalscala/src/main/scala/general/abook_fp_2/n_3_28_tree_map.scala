package general.abook_fp_2

object n_3_28_tree_map {
  def main(args: Array[String]): Unit = {
    val lev2l1 = Leaf(1)
    val lev2l2 = Leaf(2)
    val lev2l3 = Leaf(3)
    val lev2l4 = Leaf(4)

    val lev1b1 = Branch(lev2l1, lev2l2)
    val lev1b2 = Branch(lev2l3, lev2l4)

    val root = Branch(lev1b1, lev1b2)

    println(map(root)(x=>x+1))
  }
  def map[A,B](t: myTree[A])(f: A => B): myTree[B] = t match {
    case Leaf(n) => Leaf(f(n))
    case Branch(l,r) => Branch(map(l)(f), map(r)(f))
  }
}
