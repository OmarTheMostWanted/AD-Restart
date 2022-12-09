package DivideAndConquer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class ClosestPairPfPoints {
    /**
     * Class representing a 2D point.
     */
    static class Point {

        public double x;

        public double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * Useful methods for this assignment.
     */
    private static class Util {

        // Counts how often the distance function was called.
        // In an exam we may use such counters in places where you cannot edit them ;)
        // For our largest test case, this counter is checked to be less than 3 million. Our solution
        // uses about 1.2 million calls to the distance function.
        static int count_distances = 0;

        /**
         * Takes two points and computes the euclidean distance between the two points.
         *
         * @param point1 - first point.
         * @param point2 - second point.
         * @return euclidean distance between the two points.
         * @see <a
         * href="https://en.wikipedia.org/wiki/Euclidean_distance">https://en.wikipedia.org/wiki/Euclidean_distance</a>
         */
        public static double distance(Point point1, Point point2) {
            Util.count_distances++;
            return Math.sqrt(Math.pow(point1.x - point2.x, 2.0D) + Math.pow(point1.y - point2.y, 2.0D));
        }

        /**
         * Takes a list of points and sorts it on x (ascending).
         *
         * @param points - points that need to be sorted.
         */
        public static void sortByX(List<Point> points) {
            Collections.sort(points, Comparator.comparingDouble(point -> point.x));
        }

        /**
         * Takes a list of points and sorts it on y (ascending) .
         *
         * @param points - points that need to be sorted.
         */
        public static void sortByY(List<Point> points) {
            Collections.sort(points, Comparator.comparingDouble(point -> point.y));
        }

        /**
         * Takes a list of points and returns the distance between the closest pair. This is done by
         * brute forcing.
         *
         * @param points - list of points that need to be considered.
         * @return smallest pair-wise distance between points.
         */
        public static double bruteForce(List<Point> points) {
            int size = points.size();
            if (size <= 1) return Double.POSITIVE_INFINITY;
            double bestDist = Double.POSITIVE_INFINITY;
            for (int i = 0; i < size - 1; i++) {
                Point point1 = points.get(i);
                for (int j = i + 1; j < size; j++) {
                    Point point2 = points.get(j);
                    double distance = Util.distance(point1, point2);
                    if (distance < bestDist) bestDist = distance;
                }
            }
            return bestDist;
        }
    }

    /**
     * Takes a list of points and returns the distance between the closest pair. This is done with
     * divide and conquer.
     *
     * @param points - list of points that need to be considered.
     * @return smallest pair-wise distance between points.
     */
    public static double closestPair(List<Point> points) {
        if (points.size() <= 3) {
            double min = Double.POSITIVE_INFINITY;
            for (Point point : points) {
                for (Point point1 : points) {
                    if (point == point1) continue;
                    if (min > Util.distance(point, point1)) min = Util.distance(point, point1);
                }
            }
            return min;
        }
        Util.sortByX(points);
        var median = points.size() / 2;
        var left = new ArrayList<Point>();
        var right = new ArrayList<Point>();


        for (int i = 0; i <= median; i++) {
            left.add(points.get(i));
        }
        for (int i = median + 1; i < points.size(); i++) {
            right.add(points.get(i));
        }

        var delta = Math.min(closestPair(left), closestPair(right));
        var splitLine = points.get(median).x;

        List<Point> withinD = new ArrayList<>();
        for (Point point : left) {
            if (point.x > splitLine - delta) {
                withinD.add(point);
            }
        }
        for (Point point : right) {
            if (point.x > splitLine + delta) {
                withinD.add(point);
            }
        }

        Util.sortByY(withinD);
        var minDistance = Double.POSITIVE_INFINITY;
        for (int i = 0; i < withinD.size(); i++) {
            for (int k = i + 1; k <= i + 11 && k < withinD.size(); k++) {
                if (minDistance > Util.distance(withinD.get(i), withinD.get(k))) {
                    minDistance = Util.distance(withinD.get(i), withinD.get(k));
                }
            }
        }
        
        return Math.min(delta , minDistance);

    }
}
