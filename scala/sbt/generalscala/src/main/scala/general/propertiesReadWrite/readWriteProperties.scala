package propertiesReadWrite

import java.io.{BufferedWriter, FileWriter}

import com.typesafe.config.{Config, ConfigFactory}

import scala.jdk.CollectionConverters._
//import resource._

import scala.xml.{Elem, XML}


object readWriteProperties {

  def writeToFile(bw: BufferedWriter, modified: Config) = {
    modified.entrySet().forEach(x => {
      bw.write(s"${x.getKey} = ${x.getValue.unwrapped()}")
        bw.newLine()
    })
    bw.flush()
  }

  def main(args: Array[String]): Unit = {
    // xml part
    val m = xmlToMap(XML.loadFile(new java.io.File("src/main/scala/propertiesReadWrite/in.xml") ))
    m.foreach(x => println(s"${x._1} = ${x._2} "))

    val in = ConfigFactory.parseFile(new java.io.File("src/main/scala/propertiesReadWrite/in.properties"))
    //val updatesMap = Map("p13" -> "m")
    val updates = ConfigFactory.parseMap(m.toMap.asJava)

    val modified = in.withFallback(updates)

    for {
      fw <- Some(new FileWriter(new java.io.File("src/main/scala/propertiesReadWrite/out.properties")))
      bw <- Some(new BufferedWriter(fw))
    } writeToFile(bw, modified)


  }

  def xmlToMap(xml: Elem): Seq[(String, String)] = {
    val nodes = xml \ "action" \ "workflow" \ "configuration" \ "property"
    for {
      node <- nodes
    } yield ((node \ "name").text -> (node \ "value").text)
  }
}
