package general.abasics.collections.mutable.seq.indexedSeq

// An implementation of a double-ended queue that internally uses a resizable circular buffer.
// Append, prepend, removeFirst, removeLast and random-access (indexed-lookup and indexed-replacement) take amortized constant time.
// In general, removals and insertions at i-th index are O(min(i, n-i)) and thus insertions and removals from end/beginning are fast.
object arrayDequeueTest {
  def main(args: Array[String]): Unit = {

  }
}
