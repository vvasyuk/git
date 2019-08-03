package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    //var in = "(if (zero? x) max (/ 1 x))".toList
    //var in = "I told him (that it’s not (yet) done). (But he wasn’t listening)".toList
    //var in = ":-)".toList
    //var in = "())(".toList
    //println(balance(in))
    //    for (row <- 0 to 10) {
    //      for (col <- 0 to row)
    //        print(col + row + " ")
    //      //print(pascal(col, row) + " ")
    //      println()
    //    }
  }

  /**
    * Exercise 1
    */
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || r == 0 || c == r) {
      1
    } else {
      pascal(c - 1, r - 1) + pascal(c, r - 1)
    }
  }

  /**
    * Exercise 2
    */
  def balance(chars: List[Char]): Boolean = {
    var openBracketCnt = 0;

    def recHelper(chars: List[Char]): Boolean = {
      chars match {
        case _::Nil => {
          val h = chars.head
          if (h.equals('(')) {
            false
          } else if (h.equals(')')){
            openBracketCnt -= 1
            if (openBracketCnt == 0) {
              true
            } else {
              false
            }
          } else {
            true
          }
        }
        case _=> {
          val h = chars.head
          val t = chars.tail

          if (h.equals('(')) {
            openBracketCnt += 1
            recHelper(t)
          } else if (h.equals(')')) {
            if (openBracketCnt > 0) {
              openBracketCnt -= 1
            } else {
              return false
            }
            recHelper(t)
          } else {
            recHelper(t)
          }
        }
      }
    }
    recHelper(chars)
  }
      /**
       * Exercise 3
       */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money < 0 || coins.isEmpty){
      0
    } else if (money == 0) {
      1
    } else {
      countChange(money - coins.head, coins) + countChange(money, coins.tail)
    }
  }
}
