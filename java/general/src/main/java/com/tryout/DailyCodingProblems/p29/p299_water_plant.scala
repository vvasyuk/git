package com.tryout.DailyCodingProblems.p29

import scala.collection.mutable

// A group of houses is connected to the main water plant by means of a set of pipes. A house can either be connected by a set of pipes extending directly to the plant, or indirectly by a pipe to a nearby house which is otherwise connected.
//
//For example, here is a possible configuration, where A, B, and C are houses, and arrows represent pipes:
//
//A <--> B <--> C <--> plant
//Each pipe has an associated cost, which the utility company would like to minimize. Given an undirected graph of pipe connections, return the lowest cost configuration of pipes such that each house has access to water.
//
//In the following setup, for example, we can remove all but the pipes from plant to A, plant to B, and B to C, for a total cost of 16.
//
//pipes = {
//    'plant': {'A': 1, 'B': 5, 'C': 20},
//    'A': {'C': 15},
//    'B': {'C': 10},
//    'C': {}
//}
object p299_water_plant {
  //           20
  //  +---------------------------+
  //  |                           v
  //+-------+  1    +---+  15   +---+
  //| plant | ----> | A | ----> | C |
  //+-------+       +---+       +---+
  //  |                           ^
  //  | 5                         |
  //  v                           |
  //+-------+  10                 |
  //|   B   | --------------------+
  //+-------+

  def rec(in: Map[String, Map[_ <: String, Int]], pq: mutable.PriorityQueue[(Int, (String,String))], mst: mutable.Map[(String, String), Int], visited: Set[String]): mutable.Map[(String, String), Int] = {
    if(pq.isEmpty || mst.size==in.size){
      mst
    }else{
      val (dist, (from, to)) = pq.dequeue()

      in(to).foreach(m=>{
        if(!visited.contains(m._1)) pq.enqueue((m._2, (to, m._1)))
      })

      mst.put((from,to),dist)

      rec(in, pq, mst, visited+to)
    }
  }

  def main(args: Array[String]): Unit = {
    val in = Map(
      "plant" -> Map("A" -> 1, "B"-> 5, "C"-> 20),
    "A" -> Map("C"-> 15),
    "B" -> Map("C"-> 10),
    "C" -> Map()
    )
    val pq = collection.mutable.PriorityQueue((0, ("plant","plant")))(Ordering[((Int,(String,String)))].on(x => (-x._1, ("",""))))
    val mst = mutable.Map[(String,String),Int]()
    val visited = Set[String]()
//    pq.enqueue((3,"B"))
//    pq.enqueue((1,"A"))
//    println(pq.clone.dequeueAll)

    val res = rec(in, pq, mst, visited).foldLeft(0)((a,b)=>a+b._2)
    println(res)
  }
}

//- Prim's Algorithm
//    ・Start with vertex 0 and greedily grow tree T.
//    ・Add to T the min weight edge with exactly one endpoint in T.
//    ・Repeat until V - 1 edges.                                                                                      min-PQ        MST
//    ###############################################################################     add edges of 0 to min PQ    0-7           0-7
//    #                                          0.29                               #     add edges of 7 to min PQ    1-7           0-7 1-7
//    #                                  ┌·················┐                        #     add edges of 1 to min PQ    0-2           0-7 1-7 0-2
//    #                                  :                 :                        #     add edges of 2 to min PQ    2-3           0-7 1-7 0-2 2-3
//    #              ╔═══════╗0.32     ╔═══════╗         ╔═══════╗                  #     add edges of 3 to min PQ    5-7           0-7 1-7 0-2 2-3 5-7
//    #           ┌─ ║   5   ║ ······· ║   1   ║ ·┐      ║   3   ║ ···············┐ #     add edges of 5 to min PQ    5-4           0-7 1-7 0-2 2-3 5-7
//    #           │  ╚═══════╝         ╚═══════╝  :      ╚═══════╝                : #     add edges of 4 to min PQ    6-2           0-7 1-7 0-2 2-3 5-7
//    #           │    │                 │        :        │                      : #
//    #           │    │0.28(5)          │0.19(2) :0.36    │ 0.17(4)              : #     #############################################################
//    #           │    │                 │        :        │                      : #     # public class LazyPrimMST {                                #
//    #           │    │      ╔═══════╗  │        :      ╔═══════╗                : #     #     private boolean[] marked;     // MST vertices         #
//    #           │    └───── ║       ║ ─┘        └····· ║       ║                : #     #     private Queue<Edge> mst;         // MST edges         #
//    #           │           ║       ║                  ║       ║                : #     #     private MinPQ<Edge> pq;         // PQ of edges        #
//    #           │0.35(6)    ║       ║      0.34        ║       ║                : #     #     public LazyPrimMST(WeightedGraph G) {                 #
//    #           │           ║   7   ║ ················ ║   2   ║                : #     #         pq = new MinPQ<Edge>();                           #
//    #           │           ║       ║                  ║       ║                : #     #         mst = new Queue<Edge>();                          #
//    #           │           ║       ║       0.26(3)    ║       ║                : #     #         marked = new boolean[G.V()];                      #
//    #  ┌········┼·········· ║       ║  ┌────────────── ║       ║ ──────┐        : #     #         visit(G, 0);                                      #
//    #  :        │           ╚═══════╝  │               ╚═══════╝       │        : #     #         while (<=pq.isEmpty() && mst.size() < G.V() - 1) {#
//    #  :        │             │      ╔═══════╗  0.58                   │        : #     #             Edge e = pq.delMin();                         #
//    #  :0.37    │             └───── ║   0   ║ ···················┐    │0.40(7  : #     #             int v = e.either(),                           #
//    #  :        │            0.16(1) ╚═══════╝                    :    │        : #     #             w = e.other(v);                               #
//    #  :      ╔═══╗  0.93              :                        ╔═══╗  │        : #     #             if (marked[v] && marked[w]) continue;         #
//    #  └····· ║ 4 ║ ···················┼······················· ║ 6 ║ ─┘        : #     #                                                           #
//    #         ╚═══╝                    :                        ╚═══╝           : #     #             mst.enqueue(e);                               #
//    #           :   0.38               :                          :   0.52      : #     #             if (<=marked[v]) visit(G, v);                 #
//    #           └······················┘                          └·············┘ #     #             if (<=marked[w]) visit(G, w);                 #
//    #                                                                             #     #         }                                                 #
//    ###############################################################################     #     }                                                     #
//                                                                                        #     private void visit(WeightedGraph G, int v) {          #
//                                                                                        #         marked[v] = true;                                 #
//                                                                                        #         for (Edge e : G.adj(v))                           #
//                                                                                        #             if (<=marked[e.other(v)])                     #
//                                                                                        #                 pq.insert(e);                             #
//                                                                                        #     }                                                     #
//                                                                                        #     public Iterable<Edge> mst() {return mst;}             #
//                                                                                        # }                                                         #
//                                                                                        #############################################################