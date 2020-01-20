package com.tryout.DailyCodingProblems.p23;

// You are given a list of N points (x1, y1), (x2, y2), ..., (xN, yN) representing a polygon. You can assume these points are given in order;
// that is, you can construct the polygon by connecting point 1 to point 2, point 2 to point 3, and so on, finally looping around to connect point N to point 1.

// Determine if a new point p lies inside this polygon. (If p is on the boundary of the polygon, you should return False).

public class n236_point_inside_polygon {

    public static void main(String[] args) {
        Point[] polygon = {new Point(1,1), new Point(1,3),new Point(3,4),new Point(4,1)};

        Point a = new Point(2,2);

        isPointInsidePolygon(a, polygon);

    }

    private static void isPointInsidePolygon(Point a, Point[] polygon) {
        Side[] sides = getSides(polygon);
        for (int i = 0; i < sides.length; i++) {
            System.out.println(intersects(a,sides[i]));
        }
    }

    private static int intersects(Point p, Side side) {
        Point p1 = side.p1;
        Point p2 = side.p2;

        double dx = p2.x - p1.x;
        double dy = p2.y - p1.y;
        if (dx==0){ if(p.x<p1.x && Math.min(p1.y, p2.y) < p.y && Math.max(p1.y, p2.y) > p.y){ return 1; } else {return 0;} }  // x should be to the left of side and between its y`s
        if (dy==0){return 0;}

        double slope = dy / dx;
        double intercept = p1.y - slope * p1.x;
        Point intersection = new Point((p.y - intercept) / slope, p.y);

        if (p.x <= intersection.x && Math.min(p1.y, p2.y) < intersection.y && Math.max(p1.y, p2.y) > intersection.y){ return 1; } else {return 0;}
    }

    private static Side[] getSides(Point[] polygon) {
        Side[] sides = new Side[polygon.length];
        for (int i = 0; i < polygon.length; i++) {
            if (i==polygon.length-1){
                sides[i]=new Side(polygon[i], polygon[0]);
            }else{
                sides[i]=new Side(polygon[i], polygon[i+1]);
            }
        }
        return sides;
    }

    static class Side{
        Point p1, p2;
        public Side(Point p1, Point p2) { this.p1 = p1;this.p2 = p2; }
    }

    static class Point{
        double x, y;
        public Point(double x, double y) { this.x = x;this.y = y; }
    }
}
