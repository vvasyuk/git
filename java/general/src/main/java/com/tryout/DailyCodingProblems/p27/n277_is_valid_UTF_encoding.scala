package com.tryout.DailyCodingProblems.p27

object n277_is_valid_UTF_encoding {
  //   128 64 32 16 8 4 2 1

  //   1     | 0xxxxxxx
  //   2     | 110xxxxx 10xxxxxx
  //   3     | 1110xxxx 10xxxxxx 10xxxxxx
  //   4     | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
  def main(args: Array[String]): Unit = {
    val oneByte = Array("00011110")
    val twoByte = Array("00011011", "00011101")
    val badByte = Array("00011011", "00011100")
    println(validate(oneByte))
    println(validate(twoByte))
    println(validate(badByte))


  }
  def validate(in: Array[String]): Boolean ={
    cntOnes(Integer.parseInt(in(0),2)) match{
      case 0 => {println("one bytes"); in.size==1}
      case 2 => {println("two bytes"); in.size==2 && checkStartingBitSeq(in.tail)}
      case _=> false
    }
  }
  def checkStartingBitSeq(tail: Array[String]): Boolean = {
    var res = true
    tail.foreach(e=>{
      val i = Integer.parseInt(e,2)
      res = (i&1)==1 && ((i>>1)&1)==0
    })
    res
  }
  def cntOnes(x:Int):Int={
    var y = x
    var cnt = 0
    var zero = false
    for(
      i <- 0 until 5
      if !zero
    ){
      if ((y&1)==0){
        zero=true
      }else{
        y=y>>1
        cnt+=1
      }
    }
    cnt
  }
}
