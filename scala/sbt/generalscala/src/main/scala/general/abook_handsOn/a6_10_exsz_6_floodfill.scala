package general.abook_handsOn

object a6_10_exsz_6_floodfill {

  def main(args: Array[String]): Unit = {
    floodFill(
      src = "Raw.jpg", dest = "Filled.jpg", startX = 180, startY = 90,
      compareColors = { (a: java.awt.Color, b: java.awt.Color) =>
        def sqrDiff(f: java.awt.Color => Int) = math.pow(f(a) - f(b), 2)
        math.sqrt(sqrDiff(_.getBlue) + sqrDiff(_.getGreen) + sqrDiff(_.getRed)) < 25
      },
      fillColor = java.awt.Color.BLACK
    )
  }

  def floodFill(src: String, dest: String, startX: Int, startY: Int, compareColors: (java.awt.Color, java.awt.Color) => Boolean, fillColor: java.awt.Color) = {
    val raw = javax.imageio.ImageIO.read(new java.io.File(src))
    val width = raw.getWidth
    val height = raw.getHeight
    val queue = collection.mutable.ArrayDeque((startX, startY))
    val max = width * height
    var n = 0
    val seen = collection.mutable.Set.empty[(Int, Int)]
    while (queue.nonEmpty) {
      val (currX, currY) = queue.removeHead()

      n += 1
      if (n % 1000 == 0) println(s"Filled $n/$max pixels")
      val currColor = new java.awt.Color(raw.getRGB(currX, currY))
      raw.setRGB(currX, currY, fillColor.getRGB)

      def propagate(dx: Int, dy: Int) = {
        val (nextX, nextY) = (currX + dx, currY + dy)

        if (nextX >= 0 && nextY >= 0 && nextX < width && nextY < height) {
          if (!seen.contains((nextX, nextY))) {
            val nextColor = new java.awt.Color(raw.getRGB(nextX, nextY))
            seen.add((nextX, nextY))
            if (compareColors(currColor, nextColor)) queue.append((nextX, nextY))
          }
        }
      }

      propagate(-1, 0)
      propagate(1, 0)
      propagate(0, -1)
      propagate(0, 1)
    }

    javax.imageio.ImageIO.write(raw, "jpg", new java.io.File(dest))
  }
}
