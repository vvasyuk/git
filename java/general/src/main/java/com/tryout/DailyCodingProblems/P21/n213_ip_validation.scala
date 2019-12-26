package com.tryout.DailyCodingProblems.P21

object n213_ip_validation {

  def execute(s: String, res: String, oct: Int): Unit = {
    if(oct==0){
      println(res + s)
    }else{
      Range(1,4).foreach(x=>{
        val tmp = s.slice(0,x)
        val tmpRes = res + tmp + "."
        if(
          oct*3 >= s.slice(x,s.size).size &&
          tmp.toInt <= 254

        ){
          execute(s.slice(x,s.size), tmpRes, oct-1)
        }


      })
    }
  }

  def main(args: Array[String]):Unit={
    execute("2542540123","",3)	//BA
  }
}
