package DynamicProgramming;

public class BitcoinsAndEuros {
    /**
     * Implement this method
     *
     * @param t - the number of days you have
     * @param r - exchange rates of each day. Ignore index 0. I.e. the exchange rate of the first
     *          day can be found using r[1].
     * @return the maximum amount of euros after T days
     */
    public static double optimalTrade(int t, double[] r) {
        double[] b = new double[t + 1];
        double[] e = new double[t + 1];
        double exchangeRate = 0.95;
        double tax = 5;
        b[0] = 0.1;
        e[0] = 0;
        int i = 1;
        while (i <= t) {
            b[i] = Math.max(b[i - 1], (e[i - 1] - 5.0) / r[i]);
            e[i] = Math.max(e[i - 1], b[i - 1] * 0.95 * r[i]);
            i++;
        }
        return e[t];
    }
}
