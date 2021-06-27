package dcp.p0

// cons(a, b) constructs a pair, and car(pair) and cdr(pair) returns the first and last element of that pair.
// For example, car(cons(3, 4)) returns 3, and cdr(cons(3, 4)) returns 4.

object q005_pairFirstLast:
  def main(args: Array[String]): Unit =
    val pair = MyPair(3,4)
    assert(pair.car == 3)
    assert(pair.cdr == 4)

  case class MyPair(f: Int, l: Int):
    def car = f
    def cdr = l