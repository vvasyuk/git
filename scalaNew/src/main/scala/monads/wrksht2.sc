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
val rng = SimpleRNG(42)
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

// Nesting state actions
