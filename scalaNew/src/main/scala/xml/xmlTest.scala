package xml

import scala.xml.{Attribute, Elem, MetaData, Node, Null, PrefixedAttribute, Text, UnprefixedAttribute}

object xmlTest {
  def main(args: Array[String]): Unit = {
    val xml: Elem = <calendar attr1="1" attr2="2" attr3="3">
      <week cnt="5">
        <day num="1">Mon</day>
        <day num="2">Tue</day>
        <day num="3">Wed</day>
        <day num="4">Thu</day>
        <day num="5">Fri</day>
      </week>
      <year cnt2="3">
        <month>January</month>
        <month>February</month>
        <month>March</month>
      </year>
    </calendar>

    //println(xml)
    xml.scope

    //update attribute
//    val updatedXml = xml % Attribute(None, "attr1", Text("shiat"), Null)
//    updatedXml.attributes.foreach(println(_))

    //update attribute with copy
    val xmlChanged = xml.copy(label = "newLabel", attributes = modifyAttributes(xml))
    //println(xmlChanged.buildString(true))

    //println("##### OLD ###")
    //println(xml.buildString(true))


    // print all text
//    xml.child.foreach(child => println(child.text))
//
//    println(xml.label)
//    xml.child.foreach(node => println(node.label + " " + node.attribute("cnt")))

//    val nodes: Seq[Node] = xml.child
//    nodes.foreach(n => println(n.attributes))

  }

  def modifyAttributes(xml: Elem) = {
    val attrs: MetaData = xml.attributes
    attrs.foreach(a => {
      println(a.head)
      //val x = a.get("attr1").get.head
      //println(x)
    })
    val newAttrs = MetaData.update(xml.attributes, xml.scope, Attribute(None, "attr1", Text("11"), Null))
    newAttrs
  }
}