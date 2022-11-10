package GreedyAlgorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

class FindingTheMedianTest {
    @Test
    @Timeout(100)
    public void example() {
        int n = 4;
        double[] list = {4, 2, 1, 3};
        Assertions.assertEquals(2.5, FindingTheMedian.solve(n, list), 1e-3);
    }
}