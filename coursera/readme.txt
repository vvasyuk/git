D:\work\installed\sbt\bin\sbt.bat
submit vvasyuk@gmail.com 0xXzA3lNy9arnwtF

#	def map[U]((f: T=>U): RDD[U]
val wordsRdd = sc.parallelize(largeList)
val lengthRdd = wordsRdd.map(_.length)
val totalChars = lengthRdd.reduce(_+_))

#	def flatMap[U]((f: T=>TraversableOnce[U]): RDD[U]
val rdd = spark.textfile("hdfs://...")
val count = rdd.flatMap(line=>line.split(" "))
.map(word=>(word,1))
.reduceByKey(_+_)

#	def filter(f: T=>Boolean): RDD[T]
val result = encyclopedia.filter(page=>page.contains("EPFL")).count()

#	def reduce(f: (T,T)=>T): T

#	def fold(z: A)(f: (A,A)=>A):A

#	def aggregate[B](z: B)(seqop: (B,A)=>B, combop: (B,B)=>B): B


#####	Pair RDD`s	#####
RDD[(K,V)]	//treated specially in spark
-create RDD:
val rdd: RDD[WilipediaPage] = ...
val pairRdd = rdd.map(page=>(page.title, page.text))
pairRdd.collect.print()


###	transormations
#	def groupByKey(): RDD[(K,Iterable[V])]
scala:
val ages = List (2,30,52,82)
val grouped = ages.groupBy(age=> if (age > 80) "senior" else "adult")
spark:
val eventsRdd = sc.parallelize()...map(event=> (event.organizer, event.budget))
val groupedRdd= eventsRdd.groupByKey
//	(Prime, (42000))	
//	(Sportorg, (23000,12000,5000))	


#	def reduceByKey(func: (V,V)=>V): RDD[(K,V)]
spark:
val eventsRdd = sc.parallelize()...map(event=> (event.organizer, event.budget))
val budgetsRdd = eventsRdd.reduceByKey(_+_)
//	(Prime, 42000)	
//	(Sportorg, 37000)

#	def mapValues[U](f: V => U): RDD[K,U]
short for:
rdd.map { case (x,y) => (x, func(y)) }


#	def keys: RDD[K]
spark:
val visits: RDD[Visitor] = sc.textfile()...map(v=> (v.ip, v.duration))
val numUniqueVisitors = visits.keys.distinct().count()


#	def join[W](other: RDD[(K,W)]): RDD[(K, (V,W))]
val subscriptions = List((101, ("Ruelt", AG),102, ("Brelaz", DemiTaris)))	//used from mobile
val customers = List((101,"Bern"),(102, "Geneve"),(103, "Zurich")))			//individual purchases
inner join:
val mobileUsers = subscriptions.join(customers)								//result: RDD[(Int, ((String,Abonement), String))]


###	actions
#	def countByKey(): Map[K, Long]
spark:
// get (budget, #events)
val intermediate = eventsRdd.mapValues(b=>(b,1))						// (org, budget) => (org, (budget,1))
.intermediate.reduceByKey((v1,v2) => (v1._1 + v2._1, v1._2 + v2._2))	// (org , (budget,1) => org , (sum(budget),sum(1)))
val avg = intermediate.mapValues { case (budget, numberOfEvebts) => budget/numberOfEvebts}
//	(Prime, 42000)	
//	(Sportorg, 12.3)


#####	shuffle		#####
val putchaseRdd: RDD[CFFPurchase] = sc.textFile()
val purchasesPerMonth = putchaseRdd
.map(p=>p.customerId, p.price).groupByKey().map(p=>(p._2.size, p._2.sum)).collect()		//pair may move between nodes here
###better alternative:
val purchasesPerMonth = putchaseRdd
.map(p=>p.customerId, (1, p.price)).reduceByKey((v1,v2)=>(v1._1+v2._1, v1._2+v2._2)).collect() 


