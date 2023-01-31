package NetWorkFlow;

import GreedyAlgorithms.SettingUpAWirelessNetworkInRemoteAreas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

class BaseballTest {
    @Test
    @Timeout(1)
    public void testExample() {
        int x = 4;
        int m = 4;
        int[] w = {0, 12, 11, 11, 10};
        int[][] toPlay = new int[m + 1][m + 1];
        toPlay[1][2] = toPlay[2][1] = 1;
        toPlay[1][3] = toPlay[3][1] = 1;
        toPlay[2][3] = toPlay[3][2] = 1;
        toPlay[2][4] = toPlay[4][2] = 1;
        toPlay[3][4] = toPlay[4][3] = 1;
        Assertions.assertFalse(Baseball.solve(x, m, w, toPlay));
    }

    /** Same test as example, but now with more wins for team 4. */
    @Test
    @Timeout(1)
    public void testExampleTrue() {
        int x = 4;
        int m = 4;
        int[] w = {0, 12, 11, 11, 13};
        int[][] toPlay = new int[m + 1][m + 1];
        toPlay[1][2] = toPlay[2][1] = 1;
        toPlay[1][3] = toPlay[3][1] = 1;
        toPlay[2][3] = toPlay[3][2] = 1;
        toPlay[2][4] = toPlay[4][2] = 1;
        toPlay[3][4] = toPlay[4][3] = 1;
        Assertions.assertTrue(Baseball.solve(x, m, w, toPlay));
    }
}