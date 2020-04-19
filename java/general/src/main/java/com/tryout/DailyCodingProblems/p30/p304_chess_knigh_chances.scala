package com.tryout.DailyCodingProblems.p30

object p304_chess_knigh_chances {
  def getMoves(x: Int, y: Int) = {
    val moves = Array((1, 2), (2, 1), (2, -1), (1, -2), (-1, -2), (-2, -1), (-2, 1), (-1, 2))
    for(move<-moves)yield{
      val(a,b)=move
      (x+a, y+b)
    }
  }

  def onBoard(pos: (Int, Int)): Boolean = {
    val (x,y) = pos
    x>=0 && x<=7 && y>=0 && y<=7
  }

  def main(args: Array[String]): Unit = {

    val moves = getMoves(0,0)
    moves.foreach(pos=>println("pos: " + pos + " on board: " + onBoard(pos)))
  }
}
// bottom-up dynamic programming
// def get_probability(x, y, k, memo={}):
//    if (x, y, k) in memo:
//        return memo[(x, y, k)]
//
//    if k == 0:
//        return on_board(x, y)
//    if not on_board(x, y):
//        return 0
//
//    jumps = get_moves(x, y)
//    probs = [get_probability(x, y, k - 1, memo) for x, y in jumps]
//
//    memo[(x, y, k)] = 0.125 * sum(probs)
//
//    return memo[(x, y, k)]