package general.abook_fp_2

sealed trait myTree[A] {

}
case class Leaf[A](value: A) extends myTree[A]
case class Branch[A](left: myTree[A], right: myTree[A]) extends myTree[A]