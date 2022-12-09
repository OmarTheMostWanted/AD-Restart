package DivideAndConquer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

class SortingAndCountingTest {
    @Test
    @Timeout(100)
    public void countInversionsExample() {
        int[] input = {2, 1, 0, 8};
        Assertions.assertEquals(3, SortingAndCounting.countInversions(input));
    }

    @Test
    @Timeout(100)
    public void countInversionsExample2() {
        int[] input = {8, 3, 1, 2};
        Assertions.assertEquals(5, SortingAndCounting.countInversions(input));
        // All pairs except (1, 2) are inversions.
    }
}