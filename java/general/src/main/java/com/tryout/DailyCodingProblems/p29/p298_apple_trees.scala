package com.tryout.DailyCodingProblems.p29

// A girl is walking along an apple orchard with a bag in each hand. She likes to pick apples from each tree as she goes along, but is meticulous about not putting different kinds of apples in the same bag.
//
//Given an input describing the types of apples she will pass on her path, in order, determine the length of the longest portion of her path that consists of just two types of apple trees.
//
//For example, given the input [2, 1, 2, 3, 3, 1, 3, 5], the longest portion will involve types 1 and 3, with a length of four.
object p298_apple_trees {

  def execute(in: Array[Int]): Int = {
    var (a,b) = (in(0), in(1))
    var last_picked = b
    var last_picked_count = if(a==b) 1 else 0
    var max_length_path = 1
    var curr = 1

    for (tree <- in.tail) {
      if (List(a, b).contains(tree)) {
        curr += 1
        if (last_picked == tree) {
          last_picked_count += 1
        } else {
          last_picked = tree
          last_picked_count = 1
        }
      } else {
        a = last_picked
        b = tree
        last_picked = tree
        curr = last_picked_count + 1
      }
      max_length_path = Math.max(curr, max_length_path)
    }
    max_length_path
  }

  def main(args: Array[String]): Unit = {
    val in = Array(2, 1, 2, 3, 3, 1, 3, 5)
    println(execute(in))
  }
}
