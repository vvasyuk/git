package general.abasics.a03Functions

import scala.io.Source

object FunctionsClosuresTest {
  //Methods
  object LongLines {
    def processFile(filename: String, width: Int) = {
      val source = Source.fromFile(filename)
      for (line <- source.getLines())
        processLine(filename, width, line)
    }
    private def processLine(filename: String,width: Int, line: String) = {
      if (line.length > width)
        println(filename + ": " + line.trim)
    }
  }
  object FindLongLines {
    def main(args: Array[String]) = {
      val width = args(0).toInt
      for (arg <- args.drop(1))
        LongLines.processFile(arg, width)
    }
  }
  //Local functions
  object LongLines2 {
    def processFile(filename: String, width: Int) = {
      def processLine(line: String) = {
        if (line.length > width)                // local functions can access the parameters of their enclosing function
          println(filename + ": " + line.trim)
      }
      val source = Source.fromFile(filename)
      for (line <-source.getLines())
        processLine(line)
    }
  }

  //First-class functions
  // A function literal is compiled into a class that when instantiated at runtime is a function value
  // function literals exist in the source code
  // function values exist as objects at runtime
  // Every function value is an instance of some class that extends one of several FunctionN traits in package scala

  (x: Int) => x + 1                   // function literal
  var increase = (x: Int) => x + 1    // Function values are objects, so you can store them in variables
  increase(10)                        // you can call them

  //Short forms of function literals
  someNumbers.filter(x => x > 0)

  //Placeholder syntax
  someNumbers.filter(_ > 0)
  val f = (_: Int) + (_: Int)     // f: (Int, Int) => Int = <function2>
  f(5, 10)

  //Partially applied functions
  // Although the previous examples substitute underscores in place of individual parameters, you can also replace an entire parameter list with an underscore
  // when you invoke a function, passing in any needed arguments, you apply that function to the arguments
  // given
  def sum1(a: Int, b: Int, c: Int) = a + b + c
  // You could apply the function sum to the arguments 1, 2, and 3 like this
  sum1(1, 2, 3)

  // partially applied function is an expression in which you don’t supply all of the arguments needed by the function. Instead, you supply some, or none
  val a = sum1 _       // partially applied function expression involving sum, in which you supply none of the three required args
  a(1, 2, 3)          // Scala compiler translates the expression a(1, 2, 3) into an invocation of the function value’s apply method, passing in the three arguments 1, 2, and 3.

  val b = sum1(1, _: Int, 3)   // you can also express a partially applied function by supplying only some of the required arguments
  b(2)                        // res13: Int = 6

  //Closures
  (x: Int) => x + more        // A function object that captures free variables, and is said to be "closed" over the variables visible at the time it is created.
  var more = 1
  val addMore = (x: Int) => x + more  // addMore: Int => Int = <function1>
  addMore(10)                         // res16: Int = 11
  more = 9999                         // What happens if more changes after the closure is created? In Scala, the answer is that the closure sees the change
  addMore(10)                         // res17: Int = 10009

  val someNumbers = List(11,10,5,0, 5, 10)
  var sum = 0
  someNumbers.foreach(sum += _)       // sum = 11

  //Special function call forms

  //Repeated parameters
  def echo(args: String*) = for (arg <- args) println(arg)  // echo("hello", "world!")
  // echo(Array("What's", "up", "doc?"))                     // error: type mismatch found : Array[String] required: String. fix: echo(arr: _*)
  echo(Array("What's", "up", "doc?"): _*)
  // This notation tells the compiler to pass each element of arr as its own argument to echo, rather than all of it as a single argument.

    //Named arguments
  def speed(distance: Float, time: Float): Float = distance / time    // speed(distance = 100, time = 10)

  //Default parameter values
  def printTime2(out: java.io.PrintStream = Console.out, divisor: Int = 1) =
    out.println("time = " + System.currentTimeMillis()/divisor)

  //Tail recursion
  // Functions like approximate, which call themselves as their last action, are called tail recursive. The Scala compiler detects tail recursion and replaces it with a jump back to the beginning of the function, after updating the function parameters with the new values.
//  def approximate(guess: Double): Double =
//    if (isGoodEnough(guess)) guess
//    else approximate(improve(guess))
}
