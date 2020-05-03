package com.tryout.DailyCodingProblems.p32

import scala.collection.mutable

// In chess, the Elo rating system is used to calculate player strengths based on game results.
//
//A simplified description of the Elo system is as follows. Every player begins at the same score. For each subsequent game, the loser transfers some points to the winner, where the amount of points transferred depends on how unlikely the win is. For example, a 1200-ranked player should gain much more points for beating a 2000-ranked player than for beating a 1300-ranked player.
//
//Implement this system.
object p328_elo_system {
  def main(args: Array[String]): Unit = {
    val e = Elo()
    e.addPlayer("first")
    e.addPlayer("second")
    println(e)

  }
}
case class Elo(ratings: mutable.Map[String,Int] = mutable.Map[String,Int](), var k: Int = 32, var n: Int = 400, var d: Int = 1000){
  def addPlayer(name: String): Unit ={
    ratings(name) = d
  }
  def expected(r1:Int,r2:Int): Unit ={
    1 / (1 + Math.pow(10, ((r2 - r1) / n)))
  }
  def update(p1:String, p2:String)={
    val e1 = expected(ratings(p1), ratings(p2))
    val e2 = expected(ratings(p2), ratings(p1))
  }
}

// class Elo:
//    def __init__(self, k=32, n=400, d=1000):
//        self.ratings = {}
//        self.k = k
//        self.n = n
//        self.default = d
//
//    def add_player(self, name):
//        self.ratings[name] = self.default
//
//    def expected(self, r1, r2):
//        return 1 / (1 + 10 ** ((r2 - r1) / self.n))
//
//    def update(self, p1, p2, outcome):
//        e1 = self.expected(self.ratings[p1], self.ratings[p2])
//        e2 = self.expected(self.ratings[p2], self.ratings[p1])
//
//        o1, o2 = 1 - outcome, outcome
//
//        self.ratings[p1] += self.k * (o1 - e1)
//        self.ratings[p2] += self.k * (o2 - e2)