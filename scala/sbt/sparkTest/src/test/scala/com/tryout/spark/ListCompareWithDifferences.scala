package com.tryout.spark

import org.scalatest.{FlatSpec, Matchers}

class ListCompareWithDifferences extends FlatSpec with Matchers {
  "A list1" should "be equal to list2" in {
    (List(1,2)) should equal (List(1,2))
  }
}
