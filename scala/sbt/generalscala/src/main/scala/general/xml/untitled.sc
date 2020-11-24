import scala.xml._

val someXML =
  <sammich>
    <bread>wheat</bread>
    <meat>salami</meat>
    <condiments>
      <condiment expired="true">mayo</condiment>
      <condiment expired="false">mustard</condiment>
    </condiments>
  </sammich>

someXML \\ "condiment"