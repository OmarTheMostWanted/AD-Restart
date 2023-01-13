package DynamicProgramming;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleRetrievalTest {
    @Test
    @Timeout(100)
    public void example() {
        int[] s = {0, 0, 1, 3};
        int[] f = {0, 3, 4, 8};
        int[] v = {0, 3, 5, 7};
        int[] p = {0, 0, 0, 1};
        int[] mem = {0, 3, 5, 10};
        LinkedList<Integer> expected = new LinkedList<>();
        expected.add(1);
        expected.add(3);
        Assertions.assertEquals(expected, ScheduleRetrieval.solve(3, s, f, v, p, mem));
    }
}