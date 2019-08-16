import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        double res = 0;
        if ( x == that.x && y == that.y) {
            res = Double.NEGATIVE_INFINITY;
        } else if ( y == that.y ) {
            res = 0;
        } else if ( x == that.x ) {
            res = Double.POSITIVE_INFINITY;
        } else {
            res = ( that.y - y ) / ( that.x - x );
        }
        return res;
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
        if ( y == that.y && x == that.x) return 0;
        if ( y < that.y || ( y == that.y && x < that.x)) return -1;
        return 1;
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        return new MyComparator();
    }

    private class MyComparator implements Comparator<Point> {

        @Override
        public int compare(Point a, Point b) {
            return Double.compare(slopeTo(a), slopeTo(b));
        }
    }

    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        Point p1 = new Point(1,1);
        Point p2 = new Point(100,100);
        assert(p1.compareTo(p2) == -1);
        assert(p1.slopeTo(p2) == 1);
        assert(new Point(1,1).slopeTo(new Point(1,1)) == Double.NEGATIVE_INFINITY);
        assert(new Point(1,1).slopeTo(new Point(2,1)) == 0);
        assert(new Point(1,1).slopeTo(new Point(1,3)) == Double.POSITIVE_INFINITY);

        assert(new Point(1,1).slopeOrder().compare(new Point(2,2), new Point(3,3)) == 0);

//        p1.draw();
//        p2.draw();
//        p1.drawTo(p2);

    }
}
