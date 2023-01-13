package DynamicProgramming;

public class AGoodOldGameOfHopscotch {
    /**
     * Implement this method to be an iterative approach for the recursive formula given in the
     * slides
     *
     * @param n     The number of nodes
     * @param nodes the different weights. You should use nodes[1] to nodes[n]
     * @return the minimal weight
     */
    public static int solve(int n, int[] nodes) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return nodes[1];
        } else if (n == 2) {
            return nodes[2];
        }
        int[] mem = new int[n + 1];
        mem[0] = 0;
        mem[1] = nodes[1];
        mem[2] = nodes[2];
        for (int i = 3; i <= n; i++) {
            // Find node of max distance 3, that minimizes total weight.
            int result = nodes[i] + Integer.min(Integer.min(mem[i - 1], mem[i - 2]), mem[i - 3]);
            mem[i] = result;
        }
        return mem[n];
    }
}
