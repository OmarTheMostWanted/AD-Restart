package NetWorkFlow;

import org.junit.jupiter.api.*;
import NetWorkFlow.ImageSegmentation.*;
class ImageSegmentationTest {
    @Test
    @Timeout(1)
    public void example() {
        int n = 2;
        int m = 1;
        int[] f = {0, 9, 8};
        int[] b = {0, 1, 2};
        boolean[][] connected = new boolean[n + 1][n + 1];
        connected[1][2] = true;
        connected[2][1] = true;
        Assertions.assertEquals(17, ImageSegmentation.solve(n, m, f, b, connected));
    }
}