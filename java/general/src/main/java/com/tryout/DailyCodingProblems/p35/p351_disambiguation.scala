package com.tryout.DailyCodingProblems.p35

// Word sense disambiguation is the problem of determining which sense a word takes on in a particular setting, if that word has multiple meanings. For example, in the sentence "I went to get money from the bank", bank probably means the place where people deposit money, not the land beside a river or lake.
//
//Suppose you are given a list of meanings for several words, formatted like so:
//
//{
//    "word_1": ["meaning one", "meaning two", ...],
//    ...
//    "word_n": ["meaning one", "meaning two", ...]
//}
//Given a sentence, most of whose words are contained in the meaning list above, create an algorithm that determines the likely sense of each possibly ambiguous word.
object p351_disambiguation {
// Lesk algorithm

// The idea here is that for each ambiguous word, we can compare the set of words in each sense's dictionary definition to the words in the immediate context of the sentence. If there is a lot of overlap, it is likely that that sense is correct.
  //
  //Expanding on the example above, suppose we have two ambiguous words, 'bank' and 'fork', and their meanings are defined as follows:
  //
  //meanings = {
  //    'bank': ['land beside a river or lake', 'place to deposit money'],
  //    'fork': ['eating utensil', 'bend in the road']
  //}
  //Now let's say our sentence is 'I drove my car past the fork in the road to put some money in the bank.' When we compare each word's sense, we find the following overlaps:
  //
  //word  | sense | overlap
  //-----------------------
  //bank  |   1   |   -
  //bank  |   2   | money
  //fork  |   1   |   -
  //fork  |   2   | road
  //Here we have not included smaller overlapping stopwords, such as 'in' and 'to'. We can see that since only the second senses of bank and fork have similarities to our sentence, those are the meaning we should choose.
  //
  //Our algorithm, then, will find the sense with the most overlap for each ambiguous word, and store a mapping from word to best sense in a result dictionary.
  //
  //import re
  //
  //STOPWORDS = set(['a', 'in', 'or', 'the', 'to'])
  //
  //def normalize(sentence):
  //    sentence = re.sub('[^A-Za-z ]', '', sentence)
  //
  //    words = sentence.lower().split()
  //
  //    return [word for word in words if word not in STOPWORDS]
  //
  //def disambiguate(sentence, words, meanings):
  //    true_senses = {}
  //
  //    sentence = set(normalize(sentence))
  //
  //    for word in words:
  //        max_overlaps = 0; true_senses[word] = None
  //
  //        for sense in meanings[word]:
  //            definition = set(normalize(sense))
  //            overlaps = definition.intersection(sentence)
  //
  //            if len(overlaps) > max_overlaps:
  //                max_overlaps = len(overlaps)
  //                true_senses[word] = sense
  //
  //    return true_senses
}
