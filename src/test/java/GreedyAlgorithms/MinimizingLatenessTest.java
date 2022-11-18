package GreedyAlgorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

class MinimizingLatenessTest {

    @Test
    @Timeout(500)
    public void example() {
        int n = 5;
        int[] t = {0, 1, 2, 1, 3, 1};
        int[] d = {0, 5, 1, 1, 1, 4};
        /* We order by deadline as the book tells us to.
        So we get the following order of jobs: 23451
        f(2) = 2, so late 1.
        f(3) = 3, so late 2.
        f(4) = 6, so late 5.
        f(5) = 7, so late 2.
        f(6) = 9, so late 4.

        Thus the answer is 5.
         */
        Assertions.assertEquals(5, MinimizingLateness.solve(n, t, d));
    }
}