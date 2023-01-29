package DynamicProgramming;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TheFishSalesman {
    /**
     * @param n the number of days
     * @param P the profits that can be made on day 1 through n on location P are stored in P[1]
     *          through P[n].
     * @param Q the profits that can be made on day 1 through n on location Q are stored in Q[1]
     *          through Q[n].
     * @return the maximum obtainable profit.
     */
    public static int solve(int n, int[] P, int[] Q) {
        int[][] mem = new int[2][n + 1];
        mem[0][0] = 0;
        mem[1][0] = 0;
        mem[0][1] = P[1];
        mem[1][1] = Q[1];
        for (int i = 2; i <= n; i++) {
//            mem[0][i] = Integer.max(mem[0][i - 1] + P[i], mem[1][i - 2] + Q[i]);
//            mem[1][i] = Integer.max(mem[0][i - 2] + P[i], mem[1][i - 1] + Q[i]);
            mem[0][i] = Integer.max(mem[0][i - 1] + P[i], mem[1][i - 1]);
            mem[1][i] = Integer.max(mem[1][i - 1] + Q[i], mem[0][i - 1]);
        }
        return mem[0][n];
    }

    public static List<Character> GetSolution(int n, int[] P, int[] Q) {
        int[][] mem = new int[2][n + 1];
        mem[0][0] = 0;
        mem[1][0] = 0;
        mem[0][1] = P[1];
        mem[1][1] = Q[1];
        for (int i = 2; i <= n; i++) {
            mem[0][i] = Integer.max(mem[0][i - 1] + P[i], mem[1][i - 1]);
            mem[1][i] = Integer.max(mem[1][i - 1] + Q[i], mem[0][i - 1]);
        }
        return GetSolution(mem, n, P, Q);
    }

    private static List<Character> GetSolution(int[][] mem, int n, int[] P, int[] Q) {
        var list = new LinkedList<Character>();

        boolean pathInP =mem[0][n] > mem[1][n];


        ArrayList<Integer> switches = new ArrayList<>();

        while (n > 0)   {

        if(mem[pathInP ? 0 : 1][n] == mem[pathInP ? 0 : 1][n-1] + (pathInP ? P[n] : Q[n])){
            list.addFirst( pathInP ? 'P' : 'Q' );
        }

        else {
            pathInP = !pathInP;
            list.addFirst('-');
        }

        n--;

        }

        return list;
    }
}
