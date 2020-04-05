package com.tryout.DailyCodingProblems.p28

// The number 6174 is known as Kaprekar's contant, after the mathematician who discovered an associated property: for all four-digit numbers with at least two distinct digits, repeatedly applying a simple procedure eventually results in this value. The procedure is as follows:
//
//For a given input x, create two new numbers that consist of the digits in x in ascending and descending order.
//Subtract the smaller number from the larger number.
//For example, this algorithm terminates in three steps when starting from 1234:
//
//4321 - 1234 = 3087
//8730 - 0378 = 8352
//8532 - 2358 = 6174
//Write a function that returns how many steps this will take for a given input N.
object n288_Kaprekars_contant {
  def main(args: Array[String]): Unit = {

  }

  def getDigits(num: Int): Int = {
    1234
  }

  def kaprekarSteps(num:Int, steps:Int = 0): Int = num match{
    case 6174 => steps
    case x => {
      val digits = getDigits(x)
      val num = 1234
      kaprekarSteps(num, steps + 1)
    }
  }
}

// def kaprekar_steps(num, steps=0):
//    if num == 6174:
//        return steps
//
//    digits = get_digits(num)
//    num = int(''.join(sorted(digits, reverse=True))) - int(''.join(sorted(digits)))
//
//    return kaprekar_steps(num, steps + 1)

// def get_digits(num):
//    digits = str(num)
//
//    if len(digits) == 4:
//        return digits
//    else:
//        return '0' * (4 - len(digits)) + digits