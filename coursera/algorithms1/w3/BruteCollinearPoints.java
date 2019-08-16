import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {
        checkDuplicatedEntries(points);
        ArrayList<LineSegment> foundSegments = new ArrayList<>();

        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsCopy);

        if (pointsCopy[0].slopeTo(pointsCopy[1]) == pointsCopy[0].slopeTo(pointsCopy[2]) &&
                pointsCopy[0].slopeTo(pointsCopy[1]) == pointsCopy[0].slopeTo(pointsCopy[3])) {
            foundSegments.add(new LineSegment(pointsCopy[0], pointsCopy[3]));
        }

        segments = foundSegments.toArray(new LineSegment[foundSegments.size()]);
    }
    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return Arrays.copyOf(segments, numberOfSegments());
    }

    private void checkDuplicatedEntries(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("Duplicated entries in given points.");
                }
            }
        }
    }

//    public static void main(String[] args) {
//        Point[] points = new Point[4];
//        points[0] = new Point(1, 1);
//        points[1] = new Point(2, 2);
//        points[2] = new Point(3, 3);
//        points[3] = new Point(4, 4);
//        new BruteCollinearPoints(points);
//    }
}
