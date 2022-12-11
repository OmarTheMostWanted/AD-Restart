package DivideAndConquer;

public class FastBinaryExponentiation {
    /**
     * Computes the matrix C, containing the values for a^b, for all values in a and b. Note that
     * you may not use math.pow but should implement your own fast binary exponentiation algorithm.
     *
     * @param a array containing the bases
     * @param b array containing the exponents
     * @return matrix C where entry (i,j) contains a_i^b_j
     */
    public static int[][] computeC(int[] a, int[] b) {
        var res = new int[a.length][b.length];
        for (int i = 0; i < res.length; i++) {
            for (int k = 0; k < res[i].length; k++) {
                res[i][k] = recursivePower(a[i], b[k]);
            }
        }
        return res;
    }

    private static int recursivePower(int a, int e){
        if(e == 0) return 1;
        if(a == 0) return 0;
        if(e == 1) return a;

        if(e % 2 == 0){
            var t = recursivePower(a , e/2);
            return t * t;
        } else {
            return a * recursivePower(a , e - 1);
        }
    }
}
