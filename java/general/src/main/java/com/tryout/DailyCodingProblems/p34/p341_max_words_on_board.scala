package com.tryout.DailyCodingProblems.p34

// You are given an N by N matrix of random letters and a dictionary of words. Find the maximum number of words that can be packed on the board from the given dictionary.
//
//A word is considered to be able to be packed on the board if:
//
//It can be found in the dictionary
//It can be constructed from untaken letters by other words found so far on the board
//The letters are adjacent to each other (vertically and horizontally, not diagonally).
//Each tile can be visited only once by any word.
//
//For example, given the following dictionary:
//
//{ 'eat', 'rain', 'in', 'rat' }
//and matrix:
//
//[['e', 'a', 'n'],
// ['t', 't', 'i'],
// ['a', 'r', 'a']]
//Your function should return 3, since we can make the words 'eat', 'in', and 'rat' without them touching each other. We could have alternatively made 'eat' and 'rain', but that would be incorrect since that's only 2 words.
object p341_max_words_on_board {
  // We can solve this problem using backtracking. First, we must keep track of every tile that has been visited. Then we can do a depth-first search of the board and keep track of the largest number of words that have been found so far. We also keep track of the current word we are building up. If the tile we visit has already been visited, then return immediately. Otherwise, there are two cases: if the word we're at can be made into a word, we'll add it to our set of words and continue searching the rest of the board. Otherwise, at each step, we branch out up, right, down, or left to continue trying to build out our current word.
  // DIRECTIONS = [
  //    (1, 0),
  //    (-1, 0),
  //    (0, 1),
  //    (0, -1),
  //]
  //
  //
  //def max_words(board, n, m, words, visited, r, c, curr_word):
  //    if r < 0 or r >= n or c < 0 or c >= m or visited[r][c]:
  //        return []
  //
  //    curr_word += board[r][c]
  //    # if no words in |words| start with |curr_word|, then return early.
  //    if not any(word.startswith(curr_word) for word in words):
  //        return []
  //
  //    visited[r][c] = True
  //
  //    max_word_set = []
  //    if curr_word in words:
  //        # A valid words has been found: terminate current word search and start a new one
  //        for r, row in enumerate(board):
  //            for c, val in enumerate(row):
  //                curr_word_set = max_words(board, n, m, words, visited, r, c, '')
  //                if len(curr_word_set) > len(max_word_set):
  //                    max_word_set = curr_word_set
  //        max_word_set.append(curr_word)
  //    else:
  //        for dr, dc in DIRECTIONS:
  //            curr_word_set = max_words(board, n, m, words, visited, r + dr, c + dc, curr_word)
  //            if len(curr_word_set) > len(max_word_set):
  //                max_word_set = curr_word_set
  //
  //    visited[r][c] = False
  //    return max_word_set
  //
  //
  //def find_max_words(board, words):
  //    if not board:
  //        return 0
  //
  //    n, m = len(board), len(board[0])
  //    visited = [[False for _ in range(m)] for _ in range(n)]
  //    max_words_so_far = []
  //
  //    for r, row in enumerate(board):
  //        for c, val in enumerate(row):
  //            word_set = max_words(board, n, m, words, visited, r, c, '')
  //            if len(word_set) > len(max_words_so_far):
  //                max_words_so_far = word_set
  //
  //    print(max_words_so_far)
  //    return len(max_words_so_far)

}
