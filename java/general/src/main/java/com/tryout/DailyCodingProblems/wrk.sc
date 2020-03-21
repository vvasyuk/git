import scala.collection.mutable

val ops = mutable.Stack[Char]()

ops.push('(')
ops.push('+')
ops.push(')')

println("xxx")
ops.popWhile(_!='(').foreach(println(_))