package xml

import scala.xml.transform.{RewriteRule, RuleTransformer}
import scala.xml.{Attribute, Elem, MetaData, Node, NodeSeq, Null, PrefixedAttribute, Text, UnprefixedAttribute}

object xmlTest {

  def main(args: Array[String]): Unit = {
    val xml: Elem = <calendar attr1="1" attr2="2" attr3="3">
      <bool>True</bool>
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
        <test>Test</test>
      </year>
    </calendar>


    // find elements - one level search and not deep inside our XML
    val b = xml \ "bool"
    println (b.map(x=>x.text).contains("True"))
    println(b.head.text)
    println("##############################")

    // find elements - deep
    val deep = xml \\ "month"
    println(deep.head.text)
    println("##############################")

    // dind attrinute
    val deepAttr = xml \\ "@num"
    println(deepAttr.head.text)
    println("##############################")

    // modify text
    val abbreviateDayRule = new RewriteRule {
      override def transform(n: Node): Seq[Node] = n match {
        case elem: Elem if elem.label == "day" =>
          elem.copy(child = elem.child collect {
            case Text(data) => Text(data.take(3))
          })
        case n => n
      }
    }
    val transform = new RuleTransformer(abbreviateDayRule)
    transform(xml)
    println((xml \\ "day").head.text)
    println("##############################")

    // modify text 2
    def nodesFunc(n: Seq[Node]): collection.Seq[Node] = {
      n.map(x=>{
        x match {
          case elem: Elem if (elem.label == "week" && (elem \ "day").contains("day")) => println("-"+elem.label)
          case _ =>
        }
      })

      n
    }
    val xml2 = xml.copy(child = nodesFunc(xml.child))
    println((xml2 \\ "day").head.text)
    println("##############################")



    //println(xml)
    //xml.scope

    //update attribute
//    val updatedXml = xml % Attribute(None, "attr1", Text("shiat"), Null)
//    updatedXml.attributes.foreach(println(_))

    //update attribute with copy
    //val xmlChanged = xml.copy(label = "newLabel", attributes = modifyAttributes(xml))
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