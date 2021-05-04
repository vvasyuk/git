package general.abasics.a01Classes.classes

class Rational(n: Int, d: Int, val visible: Int = 0) {                  // primary constructor will be created with these two params
  require(d != 0)                                 // class body code will be added to primary constructor (precondition of the primary constructor that d must be non-zero)
  private val g = gcd(n.abs, d.abs)
  val numer = n / g                               // n, d not visible, cause theya re constructor params
  val denom = d / g                               // need to prefix them with val or add fields as here
  def this(n: Int) = this(n, 1)                   // auxiliary constructors invokes the primary constructor
  def + (that: Rational): Rational =              // simply that.d will not work because d is not accessible outside object where they invoked, so we added numer, denum fields
    new Rational(numer * that.denom +
      that.numer * denom,denom * that.denom)
  def + (i: Int): Rational =
    new Rational(numer + i * denom, denom)
  def - (that: Rational): Rational =
    new Rational(
      numer * that.denom - that.numer * denom,
      denom * that.denom
    )
  def - (i: Int): Rational =
    new Rational(numer - i * denom, denom)
  def * (that: Rational): Rational =
    new Rational(numer * that.numer, denom *
      that.denom)
  def * (i: Int): Rational =
    new Rational(numer * i, denom)
  def / (that: Rational): Rational =
    new Rational(numer * that.denom, denom *
      that.numer)
  def / (i: Int): Rational =
    new Rational(numer, denom * i)
  override def toString = numer + "/" + denom     // override default override
  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)
}

object Rational {
  def main(args: Array[String]): Unit = {
    val r1 = new Rational(1,2)
    val r2 = new Rational(1,2)

    //r1.n is not visible
    assert(r1.visible == 0)

    val r3 = r1 + r2
    println(r3)
  }
}
