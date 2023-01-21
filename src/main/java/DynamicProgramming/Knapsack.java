package DynamicProgramming;

import java.util.Arrays;

public class Knapsack {
    /**
     * @param n the number of items
     * @param W the maximum weight
     * @param w the weight of the items, indexed w[1] to w[n].
     * @param v the value of the items, indexed v[1] to v[n];
     * @return the maximum obtainable value.
     */
    public static int mathijsFavouriteProblem(int n, int W, int[] w, int[] v) {
        int[][] mem = new int[n+1][W+1];

        for (int i = 0; i < mem.length; i++) {
            mem[i][0] = 0;
        }

        Arrays.fill(mem[0], 0);

        for (int i = 1; i <= n; i++) {
            for (int k = 1; k <= W; k++) {
                if(w[i] > k){
                    mem[i][k] = mem[i-1][k];
                } else{
                    mem[i][k] = Math.max(mem[i-1][k] , v[i] + mem[i-1][k-w[i]]);
                }
            }
        }
        return mem[n][W];
    }
}
