package rest

import spark.Spark._
import scala.util.parsing.json._

object Api {
  def main(args: Array[String]): Unit = {
    println("Start")
    initRoutes
  }

  def initRoutes(): Unit ={
    get("/hello", (req, res) => {
      res.header("Access-Control-Allow-Origin", "*")
      Thread.sleep(5000)
      "Hello"
    })

    get("/pdf", (req, res) => {
      res.header("Access-Control-Allow-Origin", "*")
      //Thread.sleep(5000)
      //      {"content":[
      //        {"text":"Bold value","bold":true},
      //        {"table":{"headerRows":1,"widths":["*","auto",100,"*"],
      //          "body":[[{"text":"this.getGreeting()"},"Second","Third","Последняя"],["Value 1","Value 2","Value 3","Value 4"],[{"text":"Bold value","bold":true},"Val 2","Val 3","Чё"]]}
      //          ,
      //          "layout": {"hLineWidth": "this.getF1()"}
      //        }
      //        ,{"table":{"headerRows":1,"widths":["*","auto",100,"*"],
      //        "body":[[{"text":"this.getGreeting()"},"Second","Third","Последняя"],["Value 1","Value 2","Value 3","Value 4"],[{"text":"Bold value","bold":true},"Val 2","Val 3","Чё"]]}
      //        ,
      //        "layout": {"hLineWidth": "this.getF1()"}
      //      }]}
      "{\"content\":[\n{\"text\":\"this.getGreeting()\",\"bold\":true},\n{\"table\":{\"headerRows\":1,\"widths\":[\"*\",\"auto\",100,\"*\"],\n\"body\":[[{\"text\":\"this.getGreeting()\"},\"Second\",\"Third\",\"Последняя\"],[\"Value 1\",\"Value 2\",\"Value 3\",\"Value 4\"],[{\"text\":\"Bold value\",\"bold\":true},\"Val 2\",\"Val 3\",\"Чё\"]]}\n,\n\"layout\": {\"hLineWidth\": \"this.getF1()\"}\n}\n,{\"table\":{\"headerRows\":1,\"widths\":[\"*\",\"auto\",100,\"*\"],\n\"body\":[[{\"text\":\"this.getGreeting()\"},\"Second\",\"Third\",\"Последняя\"],[\"Value 1\",\"Value 2\",\"Value 3\",\"Value 4\"],[{\"text\":\"Bold value\",\"bold\":true},\"Val 2\",\"Val 3\",\"Чё\"]]}\n,\n\"layout\": {\"hLineWidth\": \"this.getF1()\"}\n}]}"
    })
  }
}


//const docDefinition = {
//  content: [{
//  table: {
//  headerRows: 1,
//  widths: ['*', 'auto', 100, '*'],
//  body: [
//  ['First', 'Second', 'Third', 'Последняя'],
//  ['Value 1', 'Value 2', 'Value 3', 'Value 4'],
//  [{ text: 'Bold value', bold: true }, 'Val 2', 'Val 3', 'Чё']
//  ]
//}
//}]
//}