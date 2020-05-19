package com.tryout.DailyCodingProblems.p36

import scala.collection.mutable.ArrayBuffer

// Mastermind is a two-player game in which the first player attempts to guess the secret code of the second.
// In this version, the code may be any six-digit number with all distinct digits.
//
// Each turn the first player guesses some number, and the second player responds by saying how many digits in this number
// correctly matched their location in the secret code.
// For example, if the secret code were 123456, then a guess of 175286 would score two, since 1 and 6 were correctly placed.
//
// Write an algorithm which, given a sequence of guesses and their scores, determines whether there exists some secret code that could have produced them.
//
// For example, for the following scores you should return True, since they correspond to the secret code 123456:
//
// {175286: 2, 293416: 3, 654321: 0}
// However, it is impossible for any key to result in the following scores, so in this case you should return False:
//
// {123456: 4, 345678: 4, 567890: 4}
object p361_mastermind_game {
  // First note that after each guess, we can narrow down the set of possible codes to those that match the corresponding score. This way, each subsequent guess will take less time to evaluate.
  //
  //Second, we can begin our pool of possible codes with only those that satisfy our most restrictive guess. Instead of iterating through every code to see whether it satisfies this guess, though, we can do this constructively.
  //
  //For example, suppose our guess is 123456 and its score is five. Then only one of the indices is incorrect, and it must be replaced by some other digit. These replacement digits, however, must not duplicate an existing digit. Therefore, we end up restricting our set of possible codes to the following:
  //
  //[[789]23456, 1[789]3456, 12[789]456, 123[789]56, 1234[789]6, 12345[789]]
  //
  //In other words, using the first guess, we have narrowed down the pool of possible solutions from roughly 105 to 36.


  def generate_codes(guess: Int, score: Int) = {
    val codes = ArrayBuffer[Int]()
    val wrong_indices = (0 until 6).combinations(6-score)
    //wrong_indices.foreach(println(_))
    for { indices <- wrong_indices
          digits = ArrayBuffer[Int]()
          index <- 0 until 6}{
      if (!indices.contains(index)){
        digits.append(guess.toString.charAt(index).toInt)
      } else{
        for{ x <- 0 until 10 if x != guess.toString.charAt(index).toInt } {
          digits.append(x)
        }
      }

    }

    println()
  }

  def isPossible(in: Map[Int, Int]): Unit = {

    val possible_codes = for { (guess, score) <- in } yield { generate_codes(guess, score) }
  }

  def main(args: Array[String]): Unit = {
    val in = Map(
      293416 -> 3, 175286 -> 2, 654321 -> 0
    )

    isPossible(in)
  }
}

// from itertools import combinations, permutations, product
//
//def corresponds(guess, code, score):
//    correct = 0
//
//    for guess_digit, real_digit in zip(guess, code):
//        if guess_digit == real_digit:
//            correct += 1
//
//    return correct == score
//
//def get_candidates(guess, score, possible_codes):
//    return [code for code in possible_codes if corresponds(guess, code, score)]
//
//def generate_codes(guess, score):
//    codes = []
//
//    # Find each possible set of incorrect indices.
//    wrong_indices = combinations(range(6), 6 - score)
//
//    # For each set, build a list of all possible digits at each index.
//    # For a "correct" index, the only possible digit is the existing one.
//    # For an "incorrect" index, we allow any digit from 0-9 except the current one.
//    # Finally, perform a Cartesian product to get the possible combined numbers.
//    for indices in wrong_indices:
//        digits = []
//
//        for index in range(6):
//            if index not in indices:
//                digits.append([int(str(guess)[index])])
//                continue
//            else:
//                digits.append([x for x in range(10) if x != str(guess)[index]])
//
//        codes.extend([''.join(map(str, num)) for num in product(*digits)])
//
//    # Ensure that all digits are distinct before returning.
//    return [code for code in codes if len(set(code)) == 6]
//
//def is_possible(scores):
//    # Sort the scores so the most correct guesses come first.
//    scores = sorted(scores.items(), key=lambda x: x[1], reverse=True)
//
//    # Start a pool of possible codes which satisfy the first guess.
//    possible_codes = generate_codes(*scores[0])
//
//    # Narrow down this pool of possible codes with each guess.
//    for guess, score in scores[1:]:
//        possible_codes = get_candidates(str(guess), score, possible_codes)
//
//    # Any remaining codes could have produced this game.
//    return True if possible_codes else False