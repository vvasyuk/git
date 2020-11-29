package general.abook_fp_2

object n_3_29_tree_fold {
  def main(args: Array[String]): Unit = {
    val lev2l1 = Leaf(1)
    val lev2l2 = Leaf(2)
    val lev2l3 = Leaf(3)
    val lev2l4 = Leaf(4)

    val lev1b1 = Branch(lev2l1, lev2l2)
    val lev1b2 = Branch(lev2l3, lev2l4)

    val root = Branch(lev1b1, lev1b2)

  }

  def fold[A,B](t: myTree[A])(f: A => B)(g: (B,B) => B): B = t match {
    case Leaf(a) => f(a)
    case Branch(l,r) => g(fold(l)(f)(g), fold(r)(f)(g))
  }

  def sizeViaFold[A](t: myTree[A]): Int =
    fold(t)(a => 1)(1 + _ + _)

  def maximumViaFold(t: myTree[Int]): Int =
    fold(t)(a => a)(_ max _)

  def depthViaFold[A](t: myTree[A]): Int =
    fold(t)(a => 0)((d1,d2) => 1 + (d1 max d2))
}
