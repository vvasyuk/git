package com.tryout.generalPuzzles.a7heaps;

import java.util.*;

// Consider a coordinate system for the Milky Way in which Earth is at (0,0,0). Model
//stars as points, and assume distances are in light years. The Milky Way consists of
//approximately 1012 stars, and their coordinates are stored in a file.
//How would you compute the k stars which are closest to Earth?
public class n11_4_compute_closest_stars {
    // Intuitively, we only care about stars close to Earth. Therefore, we can keep a set
    //of candidates, and iteratively update the candidate set. The candidates are the k
    //closest stars we have seen so far. When we examine a new star, we want to see if
    //it should be added to the candidates. This entails comparing the candidate that is
    //furthest from Earth with the new star. To find this candidate efficiently, we should
    //store the candidates in a container that supports efficiently extracting the maximum
    //and adding a new member.
    //A max-heap is perfect for this application. Conceptually, we start by adding the
    //first k stars to the max-heap. As we process the stars, each time we encounter a new
    //star that is closer to Earth than the star which is the furthest from Earth among the
    //starsin the max-heap,wedelete from the max-heap,and add thenew one. Otherwise,
    //we discard the new star and continue. We can simplify the code somewhat by simply
    //adding each star to the max-heap, and discarding the maximum element from the
    //max-heap once it contains k + 1elements.
    public static class Star implements Comparable<Star> {
        private double x, y, z;

        public Star(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public double distance() {
            return Math.sqrt(x * x + y * y + z * z);
        }

        @Override
        public int compareTo(Star rhs) {
            return Double.compare(this.distance(), rhs.distance());
        }
    }

    public static List<Star> findClosestKStars(int k, Iterator<Star> stars) {
        // maxHeap to store the closest k stars seen so far.
        PriorityQueue<Star> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());
        while (stars.hasNext()) {
            // Add each star to the max -heap. If the max-heap size exceeds k, remove
            // the maximum element from the max-heap.
            Star star = stars.next();
            maxHeap.add(star);
            if (maxHeap.size() == k + 1) {
                maxHeap.remove();
            }
        }
        List<Star> orderedStars = new ArrayList<Star>(maxHeap);
        // The only guarantee PriorityQueue makes about ordering is that the
        // maximum element comes first, so we sort orderedStars.
        Collections.sort(orderedStars);
        return orderedStars;
    }
}
