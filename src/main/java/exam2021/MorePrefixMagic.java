package exam2021;

public class MorePrefixMagic {

    /**
     * You should implement this method.
     *
     * @param n the number of encodings
     * @param encodings the encodings to analyse. Note that you should use entries encodings[1] to
     *     encodings[n]!
     * @return the longest common prefix amongst all encodings.
     */
    public static String longestPrefix(int n, String[] encodings) {
        if(n == 0) return "";
        return helper(encodings , 1 , n);
    }

    private static String helper(String[] encodings , int left , int right){
        if(left == right) return encodings[right];
        if(right - left == 1){
            return longestPrefix(encodings[left] , encodings[right]);
        }
//        var middle = (left + right)/2;
        var middle = left + (right - left)/2;

        var leftSide = helper(encodings , left , middle);
        var rightSide = helper(encodings , middle+1 , right);
        return longestPrefix(leftSide , rightSide);
    }

    private static String longestPrefix(String a , String b){
        if(a.length() == 0 || b.length() == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length() && i < b.length(); i++) {
            if(a.charAt(i) == b.charAt(i)) sb.append(a.charAt(i));
            else break;
        }
        return sb.toString();
    }


}
