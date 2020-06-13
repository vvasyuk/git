package com.tryout.spark

import java.sql.{Connection, DriverManager, Statement}

import org.scalatest.FlatSpec

class H2Test extends FlatSpec {
  "A dummyJob3Alt test" should "should be ok" in {

    var row1InsertionCheck = false
    val con: Connection = DriverManager.getConnection("jdbc:h2:./data-dir/my-h2-db")
    val stm: Statement = con.createStatement
    val sql: String =
      """
        |create table test_table1(ID INT PRIMARY KEY,NAME VARCHAR(500));
        |insert into test_table1 values (1,'A');""".stripMargin

    stm.execute(sql)
    val rs = stm.executeQuery("select * from test_table1")

    rs.next
    row1InsertionCheck = (1 == rs.getInt("ID")) && ("A" == rs.getString("NAME"))

    assert(row1InsertionCheck, "Data not inserted")
  }
}
