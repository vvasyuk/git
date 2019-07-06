package wikipedia

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.rdd.RDD

case class WikipediaArticle(title: String, text: String) {
  /**
    * @return Whether the text of this article mentions `lang` or not
    * @param lang Language to look for (e.g. "Scala")
    */
  def mentionsLanguage(lang: String): Boolean = text.split(' ').contains(lang)
}

object WikipediaRanking {

  val langs = List(
    "JavaScript", "Java", "PHP", "Python", "C#", "C++", "Ruby", "CSS",
    "Objective-C", "Perl", "Scala", "Haskell", "MATLAB", "Clojure", "Groovy")

  val conf: SparkConf = new SparkConf().setAppName("test").setMaster("local")
  val sc: SparkContext = new SparkContext(conf)
  sc.setLogLevel("ERROR")
  // Hint: use a combination of `sc.textFile`, `WikipediaData.filePath` and `WikipediaData.parse`
  val myWikiRdd: RDD[String] = sc.textFile(WikipediaData.filePath)
  val wikiRdd: RDD[WikipediaArticle] = sc.textFile(WikipediaData.filePath).map(WikipediaData.parse(_)).persist()

  /** Returns the number of articles on which the language `lang` occurs.
   *  Hint1: consider using method `aggregate` on RDD[T].
   *  Hint2: consider using method `mentionsLanguage` on `WikipediaArticle`
   */
  //def occurrencesOfLang(lang: String, rdd: RDD[WikipediaArticle]): RDD[Tuple2[String, Int]] = {
    //rdd.filter(x=>x.mentionsLanguage(lang)).map(x => (lang,1))
  def occurrencesOfLang(lang: String, rdd: RDD[WikipediaArticle]): Int = {
    rdd.filter(x=>x.mentionsLanguage(lang)).aggregate(0)((acc,v)=>(acc+1),(acc1,acc2)=>(acc1+acc2))
      //.count().toInt
      //.map(x => (lang,1)).aggregate(0)((acc,v)=>(acc+v._2),(acc1,acc2)=>(acc1+acc2))
  }

  /* (1) Use `occurrencesOfLang` to compute the ranking of the languages
   *     (`val langs`) by determining the number of Wikipedia articles that
   *     mention each language at least once. Don't forget to sort the
   *     languages by their occurrence, in decreasing order!
   *
   *   Note: this operation is long-running. It can potentially run for
   *   several seconds.
   */
  def rankLangs(langs: List[String], rdd: RDD[WikipediaArticle]): List[(String, Int)] = {
    langs.map(l=> (l, occurrencesOfLang(l, rdd))).sortBy(l=>l._2)(Ordering[Int].reverse)
  }

  /* Compute an inverted index of the set of articles, mapping each language
   * to the Wikipedia pages in which it occurs.
   */
  def makeIndex(langs: List[String], rdd: RDD[WikipediaArticle]): RDD[(String, Iterable[WikipediaArticle])] = {
    rdd.flatMap(x => langs.filter(lang => x.text.split(" ").contains(lang))
      .map(lang => (lang, x)))
      .groupByKey()
  }

  /* (2) Compute the language ranking again, but now using the inverted index. Can you notice
   *     a performance improvement?
   *
   *   Note: this operation is long-running. It can potentially run for
   *   several seconds.
   */
  def rankLangsUsingIndex(index: RDD[(String, Iterable[WikipediaArticle])]): List[(String, Int)] = {
    index.map(x=> (x._1, x._2.toArray.length)).collect.toList.sortWith(_._2 > _._2)
  }

  /* (3) Use `reduceByKey` so that the computation of the index and the ranking are combined.
   *     Can you notice an improvement in performance compared to measuring *both* the computation of the index
   *     and the computation of the ranking? If so, can you think of a reason?
   *
   *   Note: this operation is long-running. It can potentially run for
   *   several seconds.
   */
  def rankLangsReduceByKey(langs: List[String], rdd: RDD[WikipediaArticle]): List[(String, Int)] = {
    rdd.flatMap(line=>langs
      .filter(lang=>line.text.contains(lang))
      .map(lang=>(lang, 1)))
      .reduceByKey(_+_)
      .collect.toList.sortWith(_._2 > _._2)
  }

  def main(args: Array[String]) {

//    makeIndex(langs, wikiRdd).foreach(println)
//    rankLangsUsingIndex(makeIndex(langs, wikiRdd)).foreach(println)
//    myWikiRdd.take(1).foreach(println)
//    wikiRdd.map(x=>x.title).take(1).foreach(println)
//    println(occurrencesOfLang("Scala", wikiRdd))
//    println(occurrencesOfLang("Java", wikiRdd))

    /* Languages ranked according to (1) */
    val langsRanked: List[(String, Int)] = timed("Part 1: naive ranking", rankLangs(langs, wikiRdd))
//    println(langsRanked)
//    /* An inverted index mapping languages to wikipedia pages on which they appear */
    def index: RDD[(String, Iterable[WikipediaArticle])] = makeIndex(langs, wikiRdd)
//
//    /* Languages ranked according to (2), using the inverted index */
    val langsRanked2: List[(String, Int)] = timed("Part 2: ranking using inverted index", rankLangsUsingIndex(index))

//    /* Languages ranked according to (3) */
    val langsRanked3: List[(String, Int)] = timed("Part 3: ranking using reduceByKey", rankLangsReduceByKey(langs, wikiRdd))
//
//    /* Output the speed of each ranking */
    println(timing)
    sc.stop()
  }

  val timing = new StringBuffer
  def timed[T](label: String, code: => T): T = {
    val start = System.currentTimeMillis()
    val result = code
    val stop = System.currentTimeMillis()
    timing.append(s"Processing $label took ${stop - start} ms.\n")
    result
  }
}
