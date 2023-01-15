package DynamicProgramming;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecoveringSolutionsTest {
    @Test
    @Timeout(100)
    public void example() {
        int n = 7;
        int[] nodes = {0, 1, 18, 18, 1, 18, 18, 1};
        int[] mem = {0, 1, 18, 18, 2, 20, 20, 3};
        int optValue = 3;
        List<Integer> result = Arrays.asList(1, 4, 7);
        Assertions.assertEquals(result, RecoveringSolutions.solve(n, nodes, optValue, mem));
    }
}