package com.tryout.DailyCodingProblems.p35

// You are given a histogram consisting of rectangles of different heights. These heights are represented in an input list, such that [1, 3, 2, 5] corresponds to the following diagram:
//
//      x
//      x
//  x   x
//  x x x
//x x x x
//Determine the area of the largest rectangle that can be formed only from the bars of the histogram. For the diagram above, for example, this would be six, representing the 2 x 3 area at the bottom right.
object p353_largest_rectangle {
// If we were somehow able to compute the width boundaries for each column in a single pass of the array, we would reduce the time complexity considerably. Fortunately this is possible, through the use of a stack.
  //
  //We will iterate over our array, considering each index to be the right boundary. That is, for each item in our array, we will calculate the area of the best rectangle that can be made whose right boundary is the current index.
  //
  //Our stack will represent the potential left boundaries of rectangles being considered, and we will pop elements off that no longer help us make our decision.
  //
  //The clearest way to see what is happening is to look at the series of operations as we process the example array given above.
  //
  //stack   | last height | left | right | best area
  //------------------------------------------------
  //[0]           1          -1      0         0
  //[0, 1]        3          -1      1         0
  //[0]           1           0      2         3
  //[0, 2]        2           0      2         3
  //[0, 2, 3]     5          -1      3         3
  //[0, 2]        2           2      4         5
  //[0]           1           0      4         6
  //[]            -          -1      4         6
  //After looking at each element, we append it to our stack. As long as the last element of our stack represents a rectangle taller than the current one, we pop that element and use it as our height. With each pop, the left boundary will be shifted by one, incrementing the width of our potential rectangle. Finally, we update the running maximum, if necessary, to be the product of our new height and width.
  //
  //def largest_rectangle(array):
  //    stack = []
  //    max_area = 0
  //
  //    # Append a zero to handle cases of non-decreasing sequences.
  //    array.append(0)
  //
  //    for right_index, rect in enumerate(array):
  //        while stack and rect < array[stack[-1]]:
  //            height = array[stack.pop()]
  //
  //            left_index = stack[-1] if stack else -1
  //            width = right_index - (left_index + 1)
  //
  //            max_area = max(max_area, height * width)
  //
  //        stack.append(right_index)
  //
  //    return max_area
}
