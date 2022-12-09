package DivideAndConquer;

import java.util.Arrays;

public class SortingAndCounting {

    static class Res {
        int[] arr;
        int inversions;

        public Res(int[] arr, int inversions) {
            this.arr = arr;
            this.inversions = inversions;
        }

        @Override
        public String toString() {
            return "Res{" +
                    "arr=" + Arrays.toString(arr) +
                    ", inversions=" + inversions +
                    '}';
        }
    }

    /**
     * This method is used to count the number of inversions in the array and also sorts the array.
     *
     * @param array The array to be sorted.
     * @return The number of inversions in the array.
     */
    static int countInversions(int[] array) {
        return helper(array).inversions;
    }

    private static Res helper(int[] array) {
        if (array.length <= 1) return new Res(array, 0);
        if (array.length > 2) {
            int l = 0; //start of left
            int m = (int) Math.floor((double) array.length / 2.0);
            int r = array.length; // end of right;
            var left = Arrays.copyOfRange(array, l, m);
            var right = Arrays.copyOfRange(array, m, r);
            var resLeft = helper(left);
            var resRight = helper(right);
            var res = merge(resLeft.arr, resRight.arr);
            res.inversions += resLeft.inversions;
            res.inversions += resRight.inversions;
            return res;
        } else {
            var left = new int[]{array[0]};
            var right = new int[]{array[1]};
            var resLeft = helper(left);
            var resRight = helper(right);
            var res = merge(resLeft.arr, resRight.arr);
            res.inversions += resLeft.inversions;
            res.inversions += resRight.inversions;
            return res;
        }
    }

    private static Res merge(int[] left, int[] right) {
        int inversions = 0;
        int[] res = new int[left.length + right.length];
        int l = 0;
        int r = 0;
        int counter = 0;
        while (l < left.length && r < right.length) {
            if (left[l] <= right[r]) {
                res[counter] = left[l];
                l++;
                counter++;
            } else {
                res[counter] = right[r];
                r++;
                counter++;
                inversions = inversions + (left.length - l);
            }
        }


        while (l < left.length) {
            res[counter] = left[l];
            counter++;
            l++;
//            inversions++;
        }

        while (r < right.length) {
            res[counter] = right[r];
            counter++;
            r++;
        }
        return new Res(res, inversions);
    }

}
