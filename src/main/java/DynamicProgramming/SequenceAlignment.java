package DynamicProgramming;

public class SequenceAlignment {
    public static int solve(String firstString, String secondString) {
        if (firstString.length() == 0) return secondString.length();
        if (secondString.length() == 0) return firstString.length();
        int[][] mem = new int[firstString.length()+1][secondString.length()+1];
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
        return mem[firstString.length()][secondString.length() ];
    }
}
