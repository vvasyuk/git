package com.tryout.DailyCodingProblems.p35

// A typical American-style crossword puzzle grid is an N x N matrix with black and white squares, which obeys the following rules:
//
//Every white square must be part of an "across" word and a "down" word.
//No word can be fewer than three letters long.
//Every white square must be reachable from every other white square.
//The grid is rotationally symmetric (for example, the colors of the top left and bottom right squares must match).
//Write a program to determine whether a given matrix qualifies as a crossword grid.
object p352_American_style_crossword_puzzle {
// This problem boils down to knowing how to use various matrix operations. We can break this task apart by creating a separate function to satisfy each requirement above.
  //
  //First, let us determine whether the across and down words all have at least three letters. Note that this will also ensure that each white square is part of two words, since otherwise there would have to be a one-letter word.
  //
  //For each row in the grid, we will iterate over all words and increment a counter for consecutive white squares. (We assume here that white squares are given as zeroes and black squares are given as ones.) If at any point we encounter a word of length one or two, we return False.
  //
  //def has_valid_word_length(grid):
  //    for row in grid:
  //        word_length = 0
  //
  //        for square in row:
  //            if square == 0:
  //                word_length += 1
  //            else:
  //                if 0 < word_length < 3:
  //                    return False
  //                word_length = 0
  //
  //        if 0 < word_length < 3:
  //            return False
  //
  //    return True
  //Note this will work for both across and down words, since we can transpose the matrix and reapply on the new grid.
  //
  //This function will take O(N) time and O(1) space to complete, where N is the number of rows in the matrix.
  //
  //To check rotational symmetry, we need to ensure that the grid looks the same after rotating 180 degrees. While this can be achieved by iterating over the grid square by square, an alternative method is to use a combination of transposals and row reversals.
  //
  //The following steps will allow us to find the rotated grid:
  //
  //Transpose the matrix
  //Reverse the matrix
  //Transpose the matrix again
  //Reverse the matrix again
  //Here is how these operations would look on a sample input matrix:
  //
  //[[0, 1, 1],    [[0, 0, 0],    [[1, 1, 1],    [[1, 1, 0],    [[1, 0, 0],
  // [0, 0, 1], ->  [1, 0, 0], ->  [1, 0, 0], ->  [1, 0, 0], ->  [1, 0, 0],
  // [0, 0, 1]]     [1, 1, 1]]     [0, 0, 0]]     [1, 0, 0]]     [1, 1, 0]]
  //Note that in Python, we can transpose a matrix with the zip operation, by performing list(zip(*grid)), and reverse the rows of a matrix using slice notation with grid[::-1]. The zip operation will change each row into a tuple, so we must map these back to lists at the end.
  //
  //We can therefore write this operation as follows:
  //
  //def is_rotationally_symmetric(grid):
  //    transpose = list(zip(*grid))
  //    reverse = transpose[::-1]
  //    transpose = list(zip(*grid))
  //    reverse = transpose[::-1]
  //
  //    return grid == list(map(list, reverse))
  //This operation takes O(N) time and space, since we must iterate over each square and create a new grid.
  //
  //Finally, to check the connectedness of our matrix we can perform a breadth-first search from a random white square and see if we can reach all other white squares.
  //
  //def is_connected(grid):
  //    # Check how many white squares there are in the grid.
  //    count = sum([1 - square for row in grid for square in row])
  //
  //    # Find the first one to begin our search from.
  //    start = None
  //    for i, row in enumerate(grid):
  //        for j in row:
  //            if grid[i][j] == 0:
  //                start = (i, j)
  //                break
  //
  //    if not start:
  //        return False
  //
  //    # Perform BFS, adding each unvisited adjacent white square to a queue.
  //    queue = deque([start])
  //    visited = set()
  //    connected_count = 0
  //
  //    while queue:
  //        square = queue.popleft()
  //        if square not in visited:
  //            visited.add(square)
  //            connected_count += 1
  //
  //            i, j = square
  //            for neighbor in [(i - 1, j), (i + 1, j), (i, j - 1), (i, j + 1)]:
  //                row, col = neighbor
  //                if (0 <= row < len(grid) and 0 <= col < len(grid) and \
  //                    grid[row][col] == 0):
  //                    queue.append(neighbor)
  //
  //    # Check whether the visited count matches the overall count.
  //    return count == connected_count
  //This function too will have O(N) time and space complexity, as we may iterate over the entire grid and store many of the squares in our queue.
  //
  //Putting it all together, a valid grid must satisfy all four methods we have just defined.
  //
  //def is_valid(grid):
  //    return has_valid_word_length(grid) and \
  //           has_valid_word_length(zip(*grid)) and \
  //           is_rotationally_symmetric(grid) and \
  //           is_connected(grid)
  //The overall time and space complexity of our solution will be O(N), since this is the upper bound for each of our component functions.
}
