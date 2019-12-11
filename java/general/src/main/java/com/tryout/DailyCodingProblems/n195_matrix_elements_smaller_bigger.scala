package com.tryout.DailyCodingProblems

object n195_matrix_elements_smaller_bigger {

  def execute(arr: Array[Array[Int]], p1: (1, 1), p2: (3, 3)): Unit = {
    val row = arr.size
    val col = arr(0).size

    var cnt = 0

    val a = arr(p1._1)(p1._2)

    var i = 0
    var j = row

    Range(0,j).reverse.foreach(x=>{
      println(_)
    })


  }

  def main(args: Array[String]):Unit={
    val ar1 = Array(1, 3, 7, 10, 15, 20)
    val ar2 = Array(2, 6, 9, 14, 22, 25)
    val ar3 = Array(3, 8, 10, 15, 25, 30)
    val ar4 = Array(10, 11, 12, 23, 30, 35)
    val ar5 = Array(20, 25, 30, 35, 40, 45)

    val arr = Array(ar1,ar2,ar3,ar4,ar5)
    execute(arr, (1,1), (3,3))
  }
}


//i, j = 0, m - 1
//for j in reversed(range(n)):
//  while i < m and matrix[i][j] < a:
//    i += 1
//    count += i

matrix = [[1, 3, 7, 10, 15, 20],
[2, 6, 9, 14, 22, 25],
[3, 8, 10, 15, 25, 30],
[10, 11, 12, 23, 30, 35],
[20, 25, 30, 35, 40, 45]]
m, n = len(matrix), len(matrix[0])

count = 0

print(matrix[1][1])

# Count numbers smaller than m[i1][j1]
a = matrix[1][1]
i, j = 0, m - 1
for j in reversed(range(n)):
while i < m and matrix[i][j] < a:
print(matrix[i][j])
i+=1