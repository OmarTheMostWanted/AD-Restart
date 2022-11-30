package GreedyAlgorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

class AssigningWorkstationsTest {


    @Test
    @Timeout(100)
    public void example() {
        int n = 5;
        int m = 10;
        int[] start = {0, 2, 1, 17, 3, 15};
        int[] duration = {0, 6, 2, 7, 9, 6};
        Assertions.assertEquals(3, AssigningWorkstations.solve(n, m, start, duration));
    }
}