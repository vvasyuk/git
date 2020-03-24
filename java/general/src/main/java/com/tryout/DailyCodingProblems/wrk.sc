def filter[A](criteria: A => Boolean)(col: Traversable[A])=
  col.filter(criteria)

def even: Int => Boolean = _ % 2 == 0

def evenFilter = filter(even) _
def double: Int => Int = _ * 2

//def doubleAllEven = evenFilter.andThen(map(double))