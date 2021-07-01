package dcp.p1


// The area of a circle is defined as πr^2. Estimate π to 3 decimal places using a Monte Carlo method.
//
//Hint: The basic equation of a circle is x2 + y2 = r2.

object q014_estimatePi {
  def main(args: Array[String]): Unit = {
    val a = generate
    val b = generate

    println(s"$a = ${inCircle(a)}")
    println(s"$b = ${inCircle(b)}")

    println(estimate)
  }
}

def generate =
  val r = new scala.util.Random
  ((r.between(-1.0,1.0), r.between(-1.0,1.0)))

def inCircle(t: (Double, Double)): Boolean = (t._1 * t._1 + t._2 * t._2) < 1

def estimate =
  var pointInCircle = 0
  val iters = 100000
  for(
    i <- 1 to iters if inCircle(generate)
  ) pointInCircle += 1
  (pointInCircle.toDouble / iters.toDouble) * 4
