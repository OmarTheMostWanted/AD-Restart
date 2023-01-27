package DynamicProgramming;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SequenceAlignment {
    public static int solve(String firstString, String secondString) {
        if (firstString.length() == 0) return secondString.length();
        if (secondString.length() == 0) return firstString.length();
        int[][] mem = new int[firstString.length() + 1][secondString.length() + 1];
        for (int i = 0; i < mem.length; i++) {
            mem[i][0] = i;
        }

        for (int i = 0; i < mem[0].length; i++) {
            mem[0][i] = i;
        }

        for (int i = 1; i <= firstString.length(); i++) {
            for (int k = 1; k <= secondString.length(); k++) {

                var first = firstString.charAt(i - 1);
                var second = secondString.charAt(k - 1);

                var matching = (first == second ? 0 : 1) + mem[i - 1][k - 1];
                var skippingLeft = 1 + mem[i - 1][k];
                var skippingRight = 1 + mem[i][k - 1];
//                mem[i][k] =
//                        Integer.min(Integer.min(
//                        (firstString.charAt(i-1) == secondString.charAt(k-1) ? 0 : 1) + mem[i - 1][k - 1], 1 + mem[i - 1][k]), 1 + mem[i][k - 1])
                mem[i][k] = Integer.min(matching, Integer.min(skippingLeft, skippingRight));
            }
        }
        return mem[firstString.length()][secondString.length()];
    }

    public static int solveSpaceEfficient(String firstString, String secondString) {
        if (firstString.length() == 0) return secondString.length();
        if (secondString.length() == 0) return firstString.length();
        int[] mem = new int[secondString.length() + 1];

        for (int i = 0; i < mem.length; i++) {
            mem[i] = i;
        }

        int[] next = new int[secondString.length()+1];

        for (int i = 1; i <= firstString.length(); i++) {

            var left = i;

            for (int k = 1; k <= secondString.length(); k++) {

                var first = firstString.charAt(i - 1);
                var second = secondString.charAt(k - 1);

                var matching = (first == second ? 0 : 1) + mem[k - 1];
                var skippingLeft = 1 + mem[k];
                var skippingRight = 1 + left;
                left = Integer.min(matching, Integer.min(skippingLeft, skippingRight));
//                left = mem[k];
                next[k] = left;
            }
            mem = Arrays.copyOf(next  , mem.length);
        }
        return mem[secondString.length()];
    }

    public static int solveSpaceEfficient2(String firstString, String secondString) {
        if (firstString.length() == 0) return secondString.length();
        if (secondString.length() == 0) return firstString.length();
        int[] mem = new int[firstString.length() + 1];

        for (int i = 0; i < mem.length; i++) {
            mem[i] = i;
        }

        for (int j = 1; j <= secondString.length(); j++) {

            var left = j-1;

            for (int i = 1; i <= firstString.length(); i++) {
                var ib = left;
                left = mem[i];

                var matching = (secondString.charAt(j-1) == firstString.charAt(i-1) ? 0 : 1) + ib;
                var skippingLeft = 1 + mem[i-1];
                var skippingRight = 1 + left;
                mem[i]  = Integer.min(matching, Integer.min(skippingLeft, skippingRight));
            }
        }
        return mem[firstString.length()];
    }

    public static int[][] createMemory(String firstString, String secondString) {
        int[][] mem = new int[firstString.length() + 1][secondString.length() + 1];
        for (int i = 0; i < mem.length; i++) {
            mem[i][0] = i;
        }

        for (int i = 0; i < mem[0].length; i++) {
            mem[0][i] = i;
        }

        for (int i = 1; i <= firstString.length(); i++) {
            for (int k = 1; k <= secondString.length(); k++) {

                var first = firstString.charAt(i - 1);
                var second = secondString.charAt(k - 1);

                var matching = (first == second ? 0 : 1) + mem[i - 1][k - 1];
                var skippingLeft = 1 + mem[i - 1][k];
                var skippingRight = 1 + mem[i][k - 1];
//                mem[i][k] =
//                        Integer.min(Integer.min(
//                        (firstString.charAt(i-1) == secondString.charAt(k-1) ? 0 : 1) + mem[i - 1][k - 1], 1 + mem[i - 1][k]), 1 + mem[i][k - 1])
                mem[i][k] = Integer.min(matching, Integer.min(skippingLeft, skippingRight));
            }
        }
        return mem;
    }

    public static class Matching {
        int l, r;

        public Matching(int l, int r) {
            this.l = l;
            this.r = r;
        }

        @Override
        public String toString() {
            return "(" + l + "," + r + ')';
        }
    }

    public static class Matching2 {
        char l, r;

        public Matching2(char l, char r) {
            this.l = l;
            this.r = r;
        }

        @Override
        public String toString() {
            return "(" + l + "," + r + ')';
        }
    }

    public static List<Matching2> extractSolution(String firstString, String secondString) {
        return extractSolution(createMemory(firstString, secondString), firstString, secondString);
    }

    public static List<Matching2> extractSolution(int[][] mem, String firstString, String secondString) {

        var e = mem[firstString.length()][secondString.length()];
        var res = new LinkedList<Matching>();
        var res2 = new LinkedList<Matching2>();
        int l = firstString.length();
        int r = secondString.length();
        while (l > 0 && r > 0) {
            var match = (firstString.charAt(l - 1) == secondString.charAt(r - 1) ? 0 : 1);//+ mem[l - 1][r - 1];
            var skipL = 1;//+ mem[l - 1][r];
            var skipR = 1;//+ mem[l][r - 1];

            var matched = mem[l - 1][r - 1];
            var skippedL = mem[l - 1][r];
            var skippedR = mem[l][r - 1];

            if (mem[l][r] - match == mem[l - 1][r - 1]) {
                res.addFirst(new Matching(l, r));
                res2.addFirst(new Matching2(firstString.charAt(l - 1), secondString.charAt(r - 1)));

                l--;
                r--;
            } else if (mem[l][r] - skipL == mem[l - 1][r]) {
                res.addFirst(new Matching(l, -1));
                res2.addFirst(new Matching2(firstString.charAt(l - 1), '-'));
                l--;
            } else if (mem[l][r] - skipR == mem[l][r - 1]) {
                res.addFirst(new Matching(-1, r));

                res2.addFirst(new Matching2('-', secondString.charAt(r - 1)));
                r--;
            }

        }

        while (l > 0) {
            res.addFirst(new Matching(l, -1));
            res2.addFirst(new Matching2(firstString.charAt(l - 1), '-'));
            l--;
        }

        while (r > 0) {
            res.addFirst(new Matching(-1, r));
            res2.addFirst(new Matching2('-', secondString.charAt(r - 1)));
            r--;
        }

        return res2;

    }
}
