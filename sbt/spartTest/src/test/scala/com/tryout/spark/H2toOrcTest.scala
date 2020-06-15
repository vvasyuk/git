package com.tryout.spark

import java.sql.{Connection, DriverManager, Statement}
import java.util.Properties

import org.apache.spark.sql.{SaveMode, SparkSession}
import org.scalatest.FlatSpec

class H2toOrcTest extends FlatSpec {
  "A dummyJob3Alt test" should "should be ok" in {

    var row1InsertionCheck = false
    //val con: Connection = DriverManager.getConnection("jdbc:h2:./data-dir/my-h2-db")
    //val con: Connection = DriverManager.getConnection("jdbc:h2:./data-dir/my-h2-db;;INIT=RUNSCRIPT FROM 'D:/work/tryout/sbt/spartTest/src/main/resources/createH2.sql'")
    //val con: Connection = DriverManager.getConnection("jdbc:h2:./data-dir/my-h2-db;;INIT=RUNSCRIPT FROM 'src/main/resources/createH2.sql'")
    val con: Connection = DriverManager.getConnection("jdbc:h2:mem:test_mem;;INIT=RUNSCRIPT FROM 'src/main/resources/createH2.sql'")

    val stm: Statement = con.createStatement
//    val sql: String =
//      """
//        |create table test_table1(ID INT PRIMARY KEY,NAME VARCHAR(500));
//        |insert into test_table1 values (1,'A');""".stripMargin

//    val sql: String = "insert into test_table1 values (1,'A');"
//    stm.execute(sql)

    val rs = stm.executeQuery("select * from schema_b.test_table1")

    rs.next
    println(rs.getInt("ID"))
    row1InsertionCheck = (1 == rs.getInt("ID")) && ("A" == rs.getString("NAME"))

    val rs2 = stm.executeQuery("select * from schema_b.test_table1")
    rs2.next
    println(rs2.getInt("ID"))

    assert(row1InsertionCheck, "Data not inserted")

    val spark = SparkSession
      .builder()
      .appName("SparkTestTest")
      .master("local")
      .getOrCreate()
    val props = new Properties()
    props.setProperty("driver", "org.h2.Driver")
    val df = spark.read.jdbc("jdbc:h2:mem:test_mem","(select * from schema_b.test_table1) query", props)
    df.show(false)

    //df.write.partitionBy("ID").mode(SaveMode.Overwrite).orc("/")
  }
}
// create external table hive_table(col1, string) partitioned by (partition_dt string) stored as orc location '/.../'