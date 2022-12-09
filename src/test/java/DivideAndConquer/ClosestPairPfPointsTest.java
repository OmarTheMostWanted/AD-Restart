package DivideAndConquer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import DivideAndConquer.ClosestPairPfPoints.Point;

import java.util.ArrayList;
import java.util.List;

class ClosestPairPfPointsTest {
    @Test()
    @Timeout(1000)
    public void testTwoPoints() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(1, 2));
        points.add(new Point(4, 6));
        Assertions.assertEquals(5, ClosestPairPfPoints.closestPair(points), 5e-6);
    }

    @Test()
    @Timeout(1000)
    public void testSmall() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(2, 3));
        points.add(new Point(12, 30));
        points.add(new Point(40, 50));
        points.add(new Point(5, 1));
        points.add(new Point(12, 10));
        points.add(new Point(3, 4));
        Assertions.assertEquals(1.4142135623730951, ClosestPairPfPoints.closestPair(points), 1e-6);
    }
}