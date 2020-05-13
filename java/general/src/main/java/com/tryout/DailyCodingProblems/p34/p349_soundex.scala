package com.tryout.DailyCodingProblems.p34

// Soundex is an algorithm used to categorize phonetically, such that two names that sound alike but are spelled differently have the same representation.
//
//Soundex maps every name to a string consisting of one letter and three numbers, like M460.
//
//One version of the algorithm is as follows:
//
//Remove consecutive consonants with the same sound (for example, change ck -> c).
//Keep the first letter. The remaining steps only apply to the rest of the string.
//Remove all vowels, including y, w, and h.
//Replace all consonants with the following digits:
//b, f, p, v → 1
//c, g, j, k, q, s, x, z → 2
//d, t → 3
//l → 4
//m, n → 5
//r → 6
//If you don't have three numbers yet, append zeros until you do. Keep the first three numbers.
//Using this scheme, Jackson and Jaxen both map to J250.
//
//Implement Soundex.
object p349_soundex {
//VOWELS = {'a', 'e', 'h', 'i', 'o', 'u', 'w', 'y'}
  //CONSONANTS = {
  //    'b': 1, 'f': 1, 'p': 1, 'v': 1,
  //    'c': 2, 'g': 2, 'j': 2, 'k': 2, 'q': 2, 's': 2, 'x': 2, 'z': 2,
  //    'd': 3, 't': 3,
  //    'l': 4,
  //    'm': 5, 'n': 5,
  //    'r': 6
  //}

  //With this in place, we can go through the bullet points described above.
  //We can combine steps 1 - 3 by looping through the word, character by character, and adding non-duplicate consonants to a new list. To qualify, a consonant must either have a different sound from the previous one, or it must be separated by a vowel.
  //Next, we transform all but the first letter to integers, following our consonant mapping.
  //Finally, we pad our result with zeroes if necessary, and return the first letter and up to three digits.

//def convert(word):
  //    word = list(word.lower())
  //    collapsed_word = [word[0]]
  //
  //    vowel_last = False
  //
  //    for char in word:
  //        if (CONSONANTS.get(char) != CONSONANTS.get(collapsed_word[-1])) or vowel_last:
  //            if char not in VOWELS:
  //                vowel_last = False
  //                collapsed_word += char
  //
  //        if char in VOWELS:
  //            vowel_last = True
  //
  //    result = [collapsed_word[0]]
  //
  //    for char in collapsed_word[1:]:
  //        result += str(CONSONANTS[char])
  //
  //    while len(result) < 4:
  //        result += '0'
  //
  //    return ''.join(result[:4])
}
