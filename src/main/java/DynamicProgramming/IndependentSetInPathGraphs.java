package DynamicProgramming;

public class IndependentSetInPathGraphs {
    /*
     * Note that entry node[0] should be avoided, as nodes are labelled node[1] through node[n].
     */
    public static int weight(int n, int[] nodes) {
        // The memory array. mem[i] represents the optimal solution when considering the first i
        // nodes.
        int[] mem = new int[n + 1];
        // This represents the case where we have no nodes left.
        mem[0] = 0;
        // If we have only node left, might as well use it.
        mem[1] = nodes[1];
        // Now it is up to you, to fill the rest of the memory
        for (int i = 2; i <= n; i++) {
            mem[i] = Math.max(mem[i - 2] + nodes[i] , mem[i-1]);
        }
        // The optimal solution is when we consider all n nodes.
        return mem[n];
    }
}
