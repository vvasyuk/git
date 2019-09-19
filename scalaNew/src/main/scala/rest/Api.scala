package rest

import scalaj.http.{Http, HttpOptions, HttpResponse}
import spark.Spark._

//import scala.util.parsing.json._

object Api {

  def main(args: Array[String]): Unit = {
    println("Start")
    initRoutes(RemoteService)
  }

  def initRoutes(rs: RemoteService): Unit ={

    get("/22", (req, res) => {
      res.header("Access-Control-Allow-Origin", "*")
      val response = rs.getFromRemote(rs.host)
      //val response: HttpResponse[String] = rs.getFromLocal()
      response
    })

    get("/23", (req, res) => {
      res.header("Access-Control-Allow-Origin", "*")
      "23"
    })

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

trait RemoteService{
  def getFromLocal: String
  def getFromRemote(urlPrefix: String): String
  def host: String
}

object RemoteService extends RemoteService{
  def getFromLocal() ={
    val res = Http("http://localhost:4567/23").option(HttpOptions.connTimeout(10000)).option(HttpOptions.readTimeout(50000)).asString
    res.body
  }

  def host = "https://mockbin.org"

  def getFromRemote(urlPrefix: String) ={
    Http(urlPrefix+"/bin/8023d488-a89a-45db-aba6-502414a9c523").option(HttpOptions.connTimeout(10000)).option(HttpOptions.readTimeout(50000)).asString.body
    //Http("https://mockbin.org/bin/8023d488-a89a-45db-aba6-502414a9c523").option(HttpOptions.connTimeout(10000)).option(HttpOptions.readTimeout(50000)).asString
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