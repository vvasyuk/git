package com.tryout.DailyCodingProblems.p33

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

// PageRank is an algorithm used by Google to rank the importance of different websites.
// While there have been changes over the years, the central idea is to assign each site a score based on the
// importance of other pages that link to that page.
//
// More mathematically, suppose there are N sites, and each site i has a certain count Ci of outgoing links.
// Then the score for a particular site Sj is defined as :
// score(Sj) = (1 - d) / N + d * (score(Sx) / Cx+ score(Sy) / Cy+ ... + score(Sz) / Cz))
//
//Here, Sx, Sy, ..., Sz denote the scores of all the other sites that have outgoing links to Sj, and d is a damping factor, usually set to around 0.85, used to model the probability that a user will stop searching.
//
//Given a directed graph of links between various websites, write a program that calculates each site's page rank.
object p335_page_rank {

  def get_inlinks(outlinks: Map[Int, Array[Int]]): mutable.Map[Int, ArrayBuffer[Int]] = {
    val inlinks = mutable.Map[Int, ArrayBuffer[Int]]()
    outlinks.keys.foreach(inlinks(_)=ArrayBuffer[Int]())

    for((k,v)<-outlinks;
        neighbor<-v){
      inlinks(neighbor).append(k)
    }
    inlinks
  }

  def main(args: Array[String]): Unit = {
    val outlinks = Map(
      0 -> Array(1, 2, 3),
      1 -> Array(0),
      2 -> Array(0),
      3 -> Array(0),
      4 -> Array(3)
    )
    val num_sites = outlinks.keys.size
    val num_rounds = 12
    val d = 0.85

    val inlinks = get_inlinks(outlinks)
    val scores = mutable.Map((for(o<-outlinks.keys)yield {(o->1.0/num_sites)}).toSeq: _*)
    val res = update_scores(num_rounds, num_sites, inlinks, outlinks, scores, d)
    println(res)
  }
  def update_scores(num_rounds: Int, num_sites: Int, inlinks: mutable.Map[Int, ArrayBuffer[Int]], outlinks: Map[Int, Array[Int]], scores: mutable.Map[Int, Double], d: Double): mutable.Map[Int, Double] = {
    for(_ <- 0 until num_rounds;
      new_scores = mutable.Map[Int, Double]()) {
        for((site, neighbors) <- inlinks;
            score = neighbors.map(n=>scores(n)/outlinks(n).size).sum){
          new_scores(site) = (1.0 - d) / num_sites + d * score
          new_scores.foreach(e=>scores(e._1)=e._2)
          //println(s"$site: $score $new_scores")
        }
    }
    scores
  }
}