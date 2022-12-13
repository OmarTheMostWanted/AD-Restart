package DivideAndConquer;

public class LongestPrefix {
    /**
     * You should implement this method.
     *
     * @param n         the number of encodings
     * @param encodings the encodings to analyse. Note that you should use entries encodings[1] to
     *                  encodings[n]!
     * @return the longest common prefix amongst all encodings.
     */
    public static String longestPrefix(int n, String[] encodings) {
        return helper(encodings, 1, n);
    }

    private static String helper(String[] encodings, int l, int r) {
        if (l == r) return encodings[l];
        if (r - l == 1) {
            return findCommonPrefix(encodings[l] , encodings[r]);
        }
        int middle = (r + l) / 2;
        var leftPrefix = helper(encodings, l, middle);
        var rightPrefix = helper(encodings, middle + 1, r);
        return findCommonPrefix(leftPrefix  , rightPrefix);
    }

    private static String findCommonPrefix(String a , String b){
        if(a.length() == 0 | b.length() == 0) return "";
        int counter = 0;
        while (counter < a.length() && counter < b.length()) {
            if (a.charAt(counter) == b.charAt(counter)) {
                counter++;
            } else break;
        }
        return a.substring(0, counter);
    }
}
