package DynamicProgramming;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

class AGoodOldGameOfHopscotchTest {
    @Test
    @Timeout(100)
    public void example() {
        int n = 7;
        int[] nodes = {0, 1, 18, 18, 1, 18, 18, 1};
        Assertions.assertEquals(3, AGoodOldGameOfHopscotch.solve(n, nodes));
    }
}