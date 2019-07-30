D:\work\installed\sbt\bin\sbt.bat
submit vvasyuk@gmail.com UZz2bLRqdssCKJwb

# def map[U]((f: T=>U): RDD[U]
 val wordsRdd = sc.parallelize(largeList)
 val lengthRdd = wordsRdd.map(_.length)
 val totalChars = lengthRdd.reduce(_+_))

# def flatMap[U]((f: T=>TraversableOnce[U]): RDD[U]
 val rdd = spark.textfile("hdfs://...")
 val count = rdd.flatMap(line=>line.split(" "))
			.map(word=>(word,1))
			.reduceByKey(_+_)

# def filter(f: T=>Boolean): RDD[T]
 val result = encyclopedia.filter(page=>page.contains("EPFL")).count()

# def reduce(f: (T,T)=>T): T

# def fold(z: A)(f: (A,A)=>A):A

# def aggregate[B](z: B)(seqop: (B,A)=>B, combop: (B,B)=>B): B

####################
# Pair RDD`s
####################
RDD[(K,V)]	//treated specially in spark
-create RDD:
val rdd: RDD[WilipediaPage] = ...
val pairRdd = rdd.map(page=>(page.title, page.text))
pairRdd.collect.print()

####################
# transormations
####################
# def groupByKey(): RDD[(K,Iterable[V])]
scala:
 val ages = List (2,30,52,82)
 val grouped = ages.groupBy(age=> if (age > 80) "senior" else "adult")
spark:
 val eventsRdd = sc.parallelize()...map(event=> (event.organizer, event.budget))
 val groupedRdd= eventsRdd.groupByKey
 //	(Prime, (42000))	
 //	(Sportorg, (23000,12000,5000))	

# def reduceByKey(func: (V,V)=>V): RDD[(K,V)]
 spark:
 val eventsRdd = sc.parallelize()...map(event=> (event.organizer, event.budget))
 val budgetsRdd = eventsRdd.reduceByKey(_+_)
 //	(Prime, 42000)	
 //	(Sportorg, 37000)

# def mapValues[U](f: V => U): RDD[K,U]
 short for:
 rdd.map { case (x,y) => (x, func(y)) }

# def keys: RDD[K]
 spark:
 val visits: RDD[Visitor] = sc.textfile()...map(v=> (v.ip, v.duration))
 val numUniqueVisitors = visits.keys.distinct().count()

# def join[W](other: RDD[(K,W)]): RDD[(K, (V,W))]
 val subscriptions = List((101, ("Ruelt", AG),102, ("Brelaz", DemiTaris)))	//used from mobile
 val customers = List((101,"Bern"),(102, "Geneve"),(103, "Zurich")))			//individual purchases
 inner join:
 val mobileUsers = subscriptions.join(customers)								//result: RDD[(Int, ((String,Abonement), String))]

# actions
# def countByKey(): Map[K, Long]
 spark:
 // get (budget, #events)
 val intermediate = eventsRdd.mapValues(b=>(b,1))						// (org, budget) => (org, (budget,1))
 .intermediate.reduceByKey((v1,v2) => (v1._1 + v2._1, v1._2 + v2._2))	// (org , (budget,1) => org , (sum(budget),sum(1)))
 val avg = intermediate.mapValues { case (budget, numberOfEvebts) => budget/numberOfEvebts}
 //	(Prime, 42000)	
 //	(Sportorg, 12.3)

####################
# shuffle
####################
 val putchaseRdd: RDD[CFFPurchase] = sc.textFile()
 val purchasesPerMonth = putchaseRdd
						.map(p=>p.customerId, p.price).groupByKey().map(p=>(p._2.size, p._2.sum)).collect()		//pair may move between nodes here
# better alternative:
 val purchasesPerMonth = putchaseRdd
						.map(p=>p.customerId, (1, p.price)).reduceByKey((v1,v2)=>(v1._1+v2._1, v1._2+v2._2)).collect() 

####################
# Partitioning
####################
# create partition:
 val pairs = rdd.map(p=>(p.custId, p.price))
 val tunedPartitioner = new RangePartitioner(8,pair)					# 8 - number of partitiions
 val partitioned = pairs.partitionBy(tunedPartitioner).persist()		# if not persisted = partitioning will be applied each time rdd used
# map, flaatMap make partitioning disappear (use mapValues instead which does not change the ykey and leaves partitioning)
# performance example:
 val userData = sc.sequenceFile[UserId, UserInfo]("hdfs://...")
				.partitionBy(new HashPartitioner(100)).persist()	# crate 100 partitions. since this rdd is aprtitioned spart will used it during join
 userData.join(events)												# here spark will shuffle only events
# operations that can cause a shuffle:
 join, leftOutterJoin, rightOutterJoin, groupByKey, reduceByKey. combineByKey, distinct, intersection, repartition, coalesce, cogroup, groupWith
# avoid shuffle by partitioning:
 reduceByKey	- calues are computed locally, only final reduced value is send to driver
 join	- called on two rdd`s that are pre-partitioned with same partitioner and cached on same machine will cause join to be computed locally
				
####################
# SQL
####################
# create dataframe:
 val tupleRDD = ... // Asssume RDD[Int, String,String,String]
 val tupleDF = tupleRDD.toDf("id", "name", "city", "country")
# create dataframe with case class:
 case class Person(id:Int, name:String, city: String)
 val peopleRDD = ... // Assume RDD[Person]
 val peopleDF = people.toDF
# create dataframe scheme explicitly:
 case class Person(id:Int, name:String, city: String)
 val peopleRDD = ... // Assume RDD[Person]
 val schemaString = "name age"
 val fields = schemaString.split(" ").map(fieldName=> StructField(fieldName, StringType,nullable=true))
 val schema = StructType(fields)
 val rowRDD = peopleRDD.map(_.split(",")).map(attributes=> Row(attributes(0), attributes(1)))
 val peopleDF = spark.createDataFrame(rowRDD,schema)

# register dataframe as a view:
 peopleDF.createOrReplaceTempView("people")
 val adultsDF = spark.sql("select * from people where age > 17")

####################
# DataFrames API
####################
# df method using $:
 df.filter($"age" > 18)
# refer to df:
 df.filter(df("age") > 18)
# using sql query string:
 df.filter(df("age > 18")
# example:
 case class Emplyee(id:Int, fname:String, lname: String, age: Int, city:String)
 val employeeRDD = sc.parallelize(...).toDF
 val sydneyEmployeeDF = employeeRDD.select("id", "lname").where("city == 'Sydney'").orderBy("id")
# grouping and aggregation on df
 df.groupBy($"attribute1").agg(sum($"attribute2"))
 df.groupBy($"attribute1").count($"attribute2")
# another example of most and least expensive homes
 case class Listings(street:String, zip: Int, price: Int)
 val listingsDF = ... // DataFrame of listings
 import org.apache.spark.sql.functions._
 val mostExpensiveDF = listingsDF.groupBy($"zip").max("price")
 val leastExpensiveDF = listingsDF.groupBy($"zip").min("price")
# another example of getting each post per subforum, rank authors with most posts per subforum
 case class Post(authorId: Int, subforum: String, likes: Int, date: String)
 val postsDF = ... // DataFrame of posts
 val rankedDF = postsDF.groupBy($"authorId", $"subforum")
				.agg(count($"authodrId"))	// new DF with columns authorId, subforum, count(authorId)
				.orderBy("subforum", $"count(authorId)".desc) 
# drop() 					- drops rows which have null in any column
  drop("all")				- drops rows which have null in all columns
  drop(Array("id", "name")	- drops rows which have null in specified columns
# fill(0)  					- replaces nulls in num columns with value
  fill(Map("minBalance" -> 0))
  fill(Array("id"),Map(1234 -> 8923))
# joins:
 df1.join(df2, $"df1.id" === $"df2.id", right_outer)
 adosDf.join(locationsDF, adosDF(id) === locationsDF("id"))
# joints another example
 demographicDF.join(financeDF, demographicDF("ID") === financeDF("ID"), "inner")
			.filter($"HasDept" && $"HasFinancialDependencies")
			.filter($"CountryLive" === "Switzerland")
			.count
# limitations of DataFrames:
 listingsDF.filter($"state" === "Ca")	# code will compile but produce a runtime exception

####################
# DataSets
####################
# example
 case class Listings(street:String, zip: Int, price: Int)
 val listingsDF = ... // DF of listings
 import org.apache.spark.sql.functions._ 
 val averagePriceDF = listingsDF.groupBy($"zip").avg("price")
						.collect()								// Array[org.apache.sql.Row
 val averagePriceAgain = averagePriceDF.map{row=>(row(0).asInstanceOf[String], row(1)asInstanceOf[Int])}	// does not work, with Double it will work
# to remove above ugly method DataSets were invented
# type DataFrame = DataSet[Row]
# DataSet unifies RDD API with DataFrame API
# avg home prices wirg datasets
 listingsDS.groupByKey(l=>l.zip)		// can have lambda as input like rdd
			.agg(avg($"price").as[Double])

# creating datasets
 myDF.toDS				//requires import spark.implicits._
 myRDD.toDS
 List("yay", "asdf", "asfd").toDS
# creating a typed column
 $"price".as[Double]	// TypedColumn
 
# typed transformations 
 val keyValuesDF = List((1,"Me").(2,"a").(3,"Msadfe").(4,"aMe")).toDF
 val res = keyValuesDF.map(row => row(0).asInstanceOf[Int]+1)
# typed transformations 
 map		map[U](f: T=>U): Dataset[U]
 flatMap	flatMap[U](f: T=>TraversableOnce[U]): Dataset[U]
 filter		filter(pred: T=>Boolean): Dataset[T]
 distinct	distinct(): : Dataset[T]
 groupByKey	groupByKey[K](f: T=>K):KeyValueGroupedDataset[K,T]		// not a dataset, needs additional step 
 coalesce	coalesce(numpartitions: Int): Dataset[T]
 repartition repartition(numpartitions: Int): Dataset[T]
 
# KeyValueGroupedDataset - need to call groupByKey first and tehn call one of below to return a dataset
 reduceGroups	reduceGroups(f: (V,V)=> V): Dataset[(K,V)]
 agg			agg[U](col: TypedColumn[V,U]): Dataset[(K,U)]
 mapGroups		mapGroups[U](f: (K, Iterator[V]) => U): Dataset[U]
 flatMapGroups	flatMapGroups[U](f: (K, Iterator[V]) => TraversableOnce[U]): Dataset[U]
 
# agg example
 someDS.agg(avg($"column"))		// need to use as[Double] to convert column to a typedcolumn (someDS.agg(avg($"column").as[Double]) )
 
# reduceByKey alternative in dataset
 val keyValues = List((1,"Me").(2,"a").(3,"Msadfe").(4,"aMe"))
 keyValuesRDD.reduceByKey(_=_)
# in dataset it will be
 val keyValuesDS = keyValues.toDS			// Dataset[(Int, String)]
 keyValuesDS.groupByKey(p => p._1)			// grouped by int and returns KeyValueGroupedDataset[K,T]
			.mapGroups((k, vs) => (k, vs.foldLeft("")((acc.p) => acc + p._2)))

# dont use mapGroups, use reduce or aggregator:
 keyValuesDS.groupByKey(p => p._1)
			.mapValues(p => p._2)
			.reduceGroups((acc, str) => acc+str)
 or
 val strConcat = new Aggregator[(Int,String, String,String)]{
	def zero: String = ""
	def reduce(b:String, a:(Int,String): String = b+a._2)
	def merge(b1: String, b2: String): String = b1+b2
	def finish(r:String):String =r
	override def bufferEncoder: Encoder[BUF] = Encoders.STRING
	override def outputEncoder: Encoder[OUT] = Encoders.STRING
 }.toColumn
 keyValuesDS.groupByKey(p => p._1)
			.agg(strConcat.as[String])
 
