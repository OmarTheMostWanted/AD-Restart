package DynamicProgramming;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class ChoosingBetweenWorkTest {
    @Test
    @Timeout(100)
    public void example() {
        int[] s = {0, 0, 1, 3};
        int[] f = {0, 3, 4, 8};
        int[] v = {0, 3, 5, 7};
        int[] p = {0, 0, 0, 1};
        Assertions.assertEquals(10, ChoosingBetweenWork.solve(3, s, f, v, p));
    }
}