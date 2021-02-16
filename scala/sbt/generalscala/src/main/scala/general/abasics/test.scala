package general.abasics

object test {
  def score(word: String): Int =
    word.replaceAll("a", "").length

  def bonus(word: String): Int =
    if (word.contains("c")) 5 else 0

  rankedWords(w => score(w) + bonus(w), words);
}

