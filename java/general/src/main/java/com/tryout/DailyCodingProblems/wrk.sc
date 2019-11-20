def t(v: String): String ={
  if (v=="1") return "1"

  List("1").zipWithIndex.foreach{
    x=>return t(x._1)
  }

  return "0"
}

t("0")