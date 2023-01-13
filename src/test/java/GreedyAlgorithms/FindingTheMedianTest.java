package GreedyAlgorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class FindingTheMedianTest {

    @Test
    @Timeout(100)
    public void test(){
        int n = 4;
        double[] list = { 4, 2, 1, 3 };
        Assertions.assertEquals(2.5, FindingTheMedian.solve(n, list), 1e-3);
    }

}