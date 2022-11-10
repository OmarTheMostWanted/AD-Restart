package GreedyAlgorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

class PackingTrucksTest {
    @Test
    @Timeout(100)
    public void example() {
        int n = 4;
        int[] weights = {0, 41, 29, 12, 26};
        int maxWeight = 48;
        Assertions.assertEquals(3, PackingTrucks.minAmountOfTrucks(n, weights, maxWeight));
    }

    @Test
    @Timeout(100)
    public void Test() {
        int n = 4;
        int[] weights = {0, 4, 5, 6, 10};
        int maxWeight = 10;
        Assertions.assertEquals(3, PackingTrucks.minAmountOfTrucks(n, weights, maxWeight));
    }

    @Test
    @Timeout(100)
    public void Test2() {
        int n = 6;
        int[] weights = {0, 2, 4, 5, 6, 8, 10};
        int maxWeight = 20;
        Assertions.assertEquals(2, PackingTrucks.minAmountOfTrucks(n, weights, maxWeight));
    }

//    @Test
//    @Timeout(100)
//    public void Test3() {
//        int n = 6;
//        int[] weights = {0, 1, 2, 3, 7, 8, 9};
//        int maxWeight = 10;
//        Assertions.assertEquals(3, PackingTrucks.minAmountOfTrucks(n, weights, maxWeight));
//    }
//
//    @Test
//    @Timeout(100)
//    public void Test4() {
//        int n = 6;
//        int[] weights = {0, 1, 2, 3, 7, 8, 9};
//        int maxWeight = 11;
//        Assertions.assertEquals(3, PackingTrucks.minAmountOfTrucks(n, weights, maxWeight));
//    }

    @Test
    @Timeout(100)
    public void Test5() {
        int n = 6;
        int[] weights = {0, 1, 2, 3, 7, 8, 9};
        int maxWeight = 13;
        Assertions.assertEquals(3, PackingTrucks.minAmountOfTrucks(n, weights, maxWeight));
    }

    @Test
    @Timeout(100)
    public void Test6() {
        int n = 0;
        int[] weights = {0};
        int maxWeight = 13;
        Assertions.assertEquals(0, PackingTrucks.minAmountOfTrucks(n, weights, maxWeight));
    }

}