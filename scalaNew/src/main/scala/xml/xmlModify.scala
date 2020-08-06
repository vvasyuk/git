package xml

import scala.xml.{Attribute, Elem, Node, Null, Text}

object xmlModify {
  def main(args: Array[String]): Unit = {
    val xml : Elem =
      <root>
        <subnode>
          <version>1</version>
        </subnode>
        <contents>
          <version>1</version>
        </contents>
      </root>

    //println(updateVersion(xml).buildString(true))

    val xml2 : Elem =
      <root type="norm">
        <subnode type="togeNorm">
          <version type="vasheOk">1</version>
        </subnode>
        <contents>
          <version>1</version>
        </contents>
      </root>
    println(updateVersionWithAttrs(xml2))
    println("end")

    val stocks =
      <stocks>
        <stock type="123" type2="123">AAPL</stock>
        <stock>AMZN</stock>
        <stock>GOOG</stock>
      </stocks>
    stocks match {
      case <stocks>{stocks @ _*}</stocks> =>
//        for (stock @ <stock>{_*}</stock> <- stocks)
//          println(s"stock: ${stock.attributes}")
    }

  }

  def updateVersion( node : Node ) : Node = {
    def updateElements( seq : Seq[Node]) : Seq[Node] =
      for( subNode <- seq ) yield updateVersion( subNode )

    node match {
      case <root>{ ch @ _* }</root> => <root>{ updateElements( ch ) }</root>
      case <subnode>{ ch @ _* }</subnode> => <subnode>{ updateElements( ch ) }</subnode>
      case <version>{ contents }</version> => <version>2</version>
      case other @ _ => other
    }
  }

  def updateVersion2( node : Node ) : Node = node match {
      case <root>{ ch @ _* }</root> => <root>{ ch.map(updateVersionWithAttrs) }</root>
      case <subnode>{ ch @ _* }</subnode> => <subnode>{ ch.map(updateVersionWithAttrs) }</subnode>
      case <version>{ contents }</version> => <version>13</version>
      case other @ _ => other
  }

  def updateVersionWithAttrs( node : Seq[Node] ) : Seq[Node] = node match {
    case Elem(prefix, "root", attributes, scope, child@_*) => {
      println("123")
      Elem(prefix, "root1", attributes, scope, true, child.flatMap(updateVersionWithAttrs): _*)  // child ++ newNode: _*  and  Attribute(None, "atr", Text("yes"), Null)
    }
    case Elem(prefix, "subnode", attributes, scope, child@_*) => {
      println("456")
      Elem(prefix, "subnode1", attributes, scope, true, child.flatMap(updateVersionWithAttrs): _*)
    }
    case Elem(prefix, "version", attributes, scope, child@_*) => {
      println("789")
      Elem(prefix, "version1", Attribute(None, "atr", Text("yes"), Null), scope, true, Text("riders one the storm"))
    }
    case other => {
      println("other")
      other
    }
  }


  def working = {
    val xml : Node =
      <root>
        <subnode>
          <version>1</version>
        </subnode>
        <contents>
          <version>1</version>
        </contents>
      </root>

    val upd = updateVersion(xml)
    println(upd.buildString(true))
}
  }
