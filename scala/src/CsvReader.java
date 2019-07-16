public class CsvReader {
    Transaction_date,Product,Price,Payment_Type,Country
1/2/09 6:17,Product1,1200,Mastercard,United Kingdom
1/2/09 4:53,Product1,1200,Visa,United States
1/2/09 13:08,Product1,1200,Mastercard,United States
1/3/09 14:44,Product1,1200,Visa,Australia

package com.lucianomolinari.csvprocessor

    /**
     * Trait responsible for reading/loading [[Sale]].
     *
     * @author Luciano Molinari
     */
    trait SalesReader {

        /**
         * @return A [[Seq]] containing all the sales.
         */
        def readSales(): Seq[Sale]

    }

case class Sale(date: String, product: String, price: Int, paymentType: String, country: String)


            package com.lucianomolinari.csvprocessor

import scala.io.Source

    /**
     * Implementation of [[SalesReader]] responsible for reading sales from a CSV file.
     *
     * @param fileName The name of the CSV file to be read.
     * @author Luciano Molinari
     */
    class SalesCSVReader(val fileName: String) extends SalesReader {

        override def readSales(): Seq[Sale] = {
        for {
            line <- Source.fromFile(fileName).getLines().drop(1).toVector
            values = line.split(",").map(_.trim)
        } yield Sale(values(0), values(1), values(2).toInt, values(3), values(4))
  }

    }

package com.lucianomolinari.csvprocessor

import org.scalatest.FunSuite
import org.scalatest.Matchers._

    /**
     *
     * @author Luciano Molinari
     */
    class SalesCSVReaderTest extends FunSuite {

        test("Load CSV file") {
            val sales = new SalesCSVReader("src/main/resources/SalesJan2009.csv").readSales

            sales.size shouldBe 50

            sales(0) shouldBe Sale("1/2/09 6:17", "Product1", 1200, "Mastercard", "United Kingdom")
            sales(49) shouldBe Sale("1/10/09 14:43", "Product1", 1200, "Diners", "Ireland")
        }

    }
}
