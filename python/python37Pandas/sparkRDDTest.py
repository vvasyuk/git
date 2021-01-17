from pyspark import SparkConf, SparkContext
from pyspark.sql import SparkSession, Row

conf = SparkConf().setMaster("local").setAppName("RatingsHistogram")
sc = SparkContext(conf = conf)

lines = sc.textFile("D:/work/tryout/coursera/aws/glue/moviedata.csv")
years = lines.map(lambda x: x.split(",")[2])
res = years.collect()
for e in res:
    print(e)

