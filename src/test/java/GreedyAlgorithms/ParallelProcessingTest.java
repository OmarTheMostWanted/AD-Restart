package GreedyAlgorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

class ParallelProcessingTest {
    @Test
    @Timeout(500)
    public void example() {
        int n = 5;
        int m = 2;
        int[] deadlines = {0, 3, 1, 1, 1, 2};
        Assertions.assertEquals(1, ParallelProcessing.solve(n, m, deadlines));
    }
}