package abook_fp.a06_state_monads

trait RNG {
  def nextInt: (Int, RNG)
}

case class SimpleRNG(seed: Long) extends RNG {
  def nextInt: (Int, RNG) = {
    val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL
    val nextRNG = SimpleRNG(newSeed)
    val n = (newSeed >>> 16).toInt
    (n, nextRNG)
  }
}

object testRNG {
  def main(args: Array[String]): Unit = {
    println("test")

    val r = SimpleRNG(10)
    println(r.nextInt)

    //better API
    println("better API")
    println(RNG.nonNegativeEven(SimpleRNG(10)))

    // int double


  }
}

object RNG {
  def nonNegativeInt(rng: RNG): (Int, RNG) = {
    val (n, nextRNG) = rng.nextInt

    if (n < 0) (-(n + 1), nextRNG)
    else (n, nextRNG)
  }

  def double(rng: RNG): (Double, RNG) = {
    val (n, nextRNG) = nonNegativeInt(rng)
    (n.toDouble / (Int.MaxValue.toDouble + 1), nextRNG)
  }

  def intDouble(rng: RNG): ((Int, Double), RNG) = {
    val (n, tempNextRNG) = rng.nextInt
    val (d, nextRNG) = double(tempNextRNG)
    ((n, d), nextRNG)
  }

  def doubleInt(rng: RNG): ((Double, Int), RNG) = {
    val (d, tempNextRNG) = double(rng)
    val (n, nextRNG) = tempNextRNG.nextInt
    ((d, n), nextRNG)
  }

  type Rand[+A] = RNG => (A, RNG)
  val int: Rand[Int] = _.nextInt
  def unit[A](a: A): Rand[A] = rng => (a, rng)          // passes the RNG state through without using it, always returning a constant value rather than a random value
  def map[A, B](s: Rand[A])(f: A => B): Rand[B] = {
    rng => {
      val (a, rng2) = s(rng)
      (f(a), rng2)
    }
  }
  def nonNegativeEven: Rand[Int] = {
    map(nonNegativeInt)(i => i - i % 2)
  }

  def map2[A, B, C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] = {
    rng => {
      val (raa, rng1) = ra(rng)
      val (rba, rng2) = rb(rng1)
      (f(raa, rba), rng2)
    }
  }

  def both[A,B](ra: Rand[A], rb: Rand[B]): Rand[(A,B)] =
    map2(ra, rb)((_, _))
  val randIntDouble: Rand[(Int, Double)] =
    both(int, double)

  def flatMap[A, B](f: Rand[A])(g: A => Rand[B]): Rand[B] = {
    rng => {
      val (a, rng1) = f(rng)
      g(a)(rng1)
    }
  }

  // try sequencing with map
//  def nonNegativeLessThan(n: Int): Rand[Int] =
//    map(nonNegativeInt) { i =>
//      val mod = i % n
//      if (i + (n-1) - mod >= 0) mod else nonNegativeLessThan(n)(???)    // required Int; found (Int, RNG)
//    }

  def nonNegativeLessThan(n: Int): Rand[Int] = {
    flatMap(nonNegativeInt) { a =>
      val mod = a % n
      if (a + (n - 1) - mod >= 0) unit(mod)
      else nonNegativeLessThan(n)
    }
  }

  def mapViaFlatMap[A, B](s: Rand[A])(f: A => B): Rand[B] = {
    flatMap(s)(a => unit(f(a)))
  }
  def map2ViaFlatMap[A, B, C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] = {
    flatMap(ra)(a => { rng => {
      val (b, rngc) = rb(rng)
      (f(a, b), rngc)
    }})
  }
}