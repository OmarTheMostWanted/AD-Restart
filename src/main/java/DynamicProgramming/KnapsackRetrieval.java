package DynamicProgramming;

import java.util.LinkedList;
import java.util.List;

public class KnapsackRetrieval {
    /**
     * Retrieves the knapsack items used in an optimal solution.
     *
     * @param n   the number of items.
     * @param W   the maximum weight.
     * @param w   the weight of the items, indexed w[1] to w[n].
     * @param v   the value of the items, indexed v[1] to v[n].
     * @param mem is a (n x W) integer array, where element mem[i][j] is the maximum value using
     *            only elements 1 to i and max weight of j.
     * @return list containing the id of the items used in the optimal solution, ordered
     * increasingly.
     */
    public static List<Integer> mathijsFavouriteProblem(int n, int W, int[] w, int[] v, int[][] mem) {
        int value = mem[n][W];
        LinkedList<Integer> list = new LinkedList<>();
//        for (int i = n; i > 0; i--) {
//            for (int j = W; j > 0; j--) {
//                if(j < w[i]) continue;
//                if(value == v[i] + mem[i-1][j-w[i]]){
//                    list.addFirst(i);
//                    j -= w[i];
//                    value-=v[i];
//                }
//            }
//        }

        int currentW = W;
        for (int i = n; i > 0; i--) {
            if(w[i] <= currentW && value == v[i] + mem[i-1][currentW-w[i]]){
                list.addFirst(i);
                value-= v[i];
                currentW -= w[i];
            }
        }

        return list;
    }
}
