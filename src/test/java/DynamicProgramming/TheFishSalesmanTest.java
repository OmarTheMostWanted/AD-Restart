package DynamicProgramming;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TheFishSalesmanTest {
    @Test
    @Timeout(100)
    public void example() {
        int n = 5;
        int[] P = {0, 80, 30, 30, 70, 80};
        int[] Q = {0, 90, 60, 60, 50, 20};
        Assertions.assertEquals(300, TheFishSalesman.solve(n, P, Q));
        Assertions.assertEquals(List.of(new char[]{'Q', 'Q', '-', 'P', 'P'}), TheFishSalesman.GetSolution(n, P, Q));
    }

    @Test
    @Timeout(100)
    public void example1() {
        int n = 4;
        int[] P = {0, 80, 20, 100, 10};
        int[] Q = {0, 30, 50, 120, 70};
        Assertions.assertEquals(270, TheFishSalesman.solve(n, P, Q));
    }

}