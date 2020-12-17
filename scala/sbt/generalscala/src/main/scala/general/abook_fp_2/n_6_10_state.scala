package general.abook_fp_2

import general.abook_fp_2.State.get

object n_6_10_state {
  def main(args: Array[String]): Unit = {
    val rng = SimpleRNG(13)
    //println(rng.nextInt)

    type Rand[+A] = RNG => (A, RNG)
    val int: Rand[Int] = _.nextInt
    val int2: RNG => (Int, RNG) = (i:RNG) => i.nextInt
    println(int(rng))
    println(int2(rng))

    val s = State(int2)
    val sMap = s.map(x=>x-5001734)
    println(sMap.run(rng))

    val res = for(
      a <- s;
      b <- s
    ) yield (a,b)
    println(res.run(rng))
    //val res = s.flatMap(a => s.map(b => (a, b)))
  }
}

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
