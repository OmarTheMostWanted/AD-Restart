package DynamicProgramming;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

class KnapsackTest {
    @Test
    @Timeout(100)
    public void example() {
        int n = 3;
        int W = 10;
        int[] w = {0, 8, 3, 6};
        int[] v = {0, 8, 4, 9};
        // We can either take the first item on it's own for a weight of 8 and a value of 8.
        // Or we take the second and third item, for a weight of 9 and a value of 13.
        Assertions.assertEquals(13, Knapsack.mathijsFavouriteProblem(n, W, w, v));
    }

    @Test
    @Timeout(100)
    public void example1() {
        int n = 3;
        int W = 6;
        int[] w = {0, 8, 3, 6};
        int[] v = {0, 8, 4, 9};
        // We can either take the first item on it's own for a weight of 8 and a value of 8.
        // Or we take the second and third item, for a weight of 9 and a value of 13.
        Assertions.assertEquals(9, Knapsack.mathijsFavouriteProblem(n, W, w, v));
    }

    @Test
    @Timeout(100)
    public void example2() {
        int n = 3;
        int W = 9;
        int[] w = {0, 8, 3, 6};
        int[] v = {0, 8, 4, 9};
        // We can either take the first item on it's own for a weight of 8 and a value of 8.
        // Or we take the second and third item, for a weight of 9 and a value of 13.
        Assertions.assertEquals(13, Knapsack.mathijsFavouriteProblem(n, W, w, v));
    }
}