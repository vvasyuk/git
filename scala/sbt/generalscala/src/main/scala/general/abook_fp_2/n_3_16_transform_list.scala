package general.abook_fp_2

object n_3_16_transform_list {
  def main(args: Array[String]): Unit = {
    val list = myList(1, 2, 3, 4)

    //val res = addOne(list, Nil)
    //val res = myList.foldLeft(list, myList[Int]())((acc,l)=>Cons(l,acc))
    val res = myList.foldRight(list, myList[Int]())((l,acc)=>Cons(l+1,acc))
    println(res)
  }

//  def addOne(l: myList[Int], res: myList[Int]): myList[Int] = {
//    l match{
//      case Nil => res
//      case Cons(a, _) => addOne(l.tail, Cons(a+1, res))
//    }
//  }

}
