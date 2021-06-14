package apps

object genGlueSchema {
  def main(args: Array[String]): Unit = {
    val colList = List("id"
      ,"code"
      ,"name"
      ,"description"
      ,"timeStratum"
      ,"displayAs"
      ,"accountTypeCode"
      ,"decimalPrecision"
      ,"isAssumption"
      ,"suppressZeroes"
      ,"isDefaultRoot"
      ,"shortName"
      ,"balanceType"
      ,"isLinked"
      ,"owningSheetId"
      ,"isSystem"
      ,"isIntercompany"
      ,"dataEntryType"
      ,"planBy"
      ,"actualsBy"
      ,"timeRollup"
      ,"timeWeightAcctId"
      ,"levelDimRollup"
      ,"levelDimWeightAcctId"
      ,"rollupText"
      ,"startExpanded"
      ,"hasSalaryDetail"
      ,"dataPrivacy"
      ,"isBreakbackEligible"
      ,"subType"
      ,"enableActuals"
      ,"isGroup"
      ,"hasFormula"
      ,"parentId")
    
    
    for (i <- colList)
      print(s"(\"$i\", \"string\",\"$i\", \"string\"),")
  }
}
