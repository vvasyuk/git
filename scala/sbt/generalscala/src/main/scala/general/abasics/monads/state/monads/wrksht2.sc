trait RNG {
  def nextInt: (Int, RNG)     // return the random number and the new state
}
case class SimpleRNG(seed: Long) extends RNG {
  def nextInt: (Int, RNG) = {
    val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL
    val nextRNG = SimpleRNG(newSeed)
    val n = (newSeed >>> 16).toInt
    (n, nextRNG)
  }
}
val rng = SimpleRNG(5)
val (n1, rng2) = rng.nextInt

def run(rng: RNG): (Int, RNG) = {
  val (i, r) = rng.nextInt
  (i, r)
}

type Rand[+A] = RNG => (A, RNG)
def map[A,B](s: Rand[A])(f: A => B): Rand[B] =
  rng => {
    val (a, rng2) = s(rng)
    (f(a), rng2)
  }

def plusOne: Rand[Int] =
  map(run)(i => i + 1)
def minusOne: Rand[Int] =
  map(run)(i => i - 1)

println(plusOne(rng))
println(minusOne(rng))
def nonNegativeEven: Rand[Int] =
  map(nonNegativeInt)(i => i - i % 2)
// Nesting state actions
//nonNegativeInt
def nonNegativeInt(rng: RNG): (Int, RNG) = {                      // RNG => (A, RNG)
  val (i, r) = rng.nextInt
  (if (i < 0) -(i + 1) else i, r)
}
def unit[A](a: A): Rand[A] =
  rng => (a, rng)
def flatMap[A,B](f: Rand[A])(g: A => Rand[B]): Rand[B] =
  rng => {                                                          // receives nonNegativeInt, executes it
    val (a, r1) = f(rng)
    g(a)(r1)                                                        // g(a) its a rng => (a, rng) and you pass r1
  }
// to chain
def nonNegativeLessThan(n: Int): Rand[Int] = {
  flatMap(nonNegativeInt)                                           // 1. nonNegativeInt its state RNG => (A, RNG)
  { i =>                                                            // 2. g: A => Rand[B]
    val mod = i % n
    if (i + (n-1) - mod >= 0) unit(mod) else nonNegativeLessThan(n) // unit its rng => (a, rng)
  }
}

def rollDie: Rand[Int] = nonNegativeLessThan(6) // RNG => (A, RNG)
val zero = rollDie(SimpleRNG(5))