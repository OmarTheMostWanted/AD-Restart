package DivideAndConquer;

import com.sun.source.tree.Tree;

import java.util.*;

public class PartialSums {
    /**
     * Computes all possible partial sums given an array of integers.
     *
     * @param arr - all values in the input set
     * @return set of sums
     */
    public static Set<Integer> partialSums(Integer[] arr) {
        if (arr.length == 0) {
            return new HashSet<>(Collections.singletonList(0));
        }
        var res =  partialSums(arr, 0, arr.length - 1);
        res.add(0);
        return res;
    }

    public static Set<Integer> partialSums(Integer[] arr, int low, int high) {
        if (low == high) {
            return new HashSet<>(Arrays.asList(arr[low]));
        }
        int mid = (low + high) / 2;
        Set<Integer> leftSums = partialSums(arr, low, mid);
        Set<Integer> rightSums = partialSums(arr, mid + 1, high);
        Set<Integer> combinedSums = new HashSet<>();
        combinedSums.addAll(leftSums);
        combinedSums.addAll(rightSums);
        for (Integer a : leftSums) {
            for (Integer b : rightSums) {
                combinedSums.add(a + b);
            }
        }
        return combinedSums;
    }




    public static Set<HashSet<Integer>> createPowerSet(Integer[] arr){

       var res = new HashSet<HashSet<Integer>>();
        for (int i = 0; i < Math.pow(2, arr.length); i++) {
            var binary = Integer.toBinaryString(i);
            var set = new HashSet<Integer>();
            int count = 0;
            for (int bit = binary.length() - 1; bit >= 0; bit--) {
                var selectedBit = binary.charAt(bit);
                if (selectedBit == '1') set.add(arr[count++]);
                else count++;
            }
            res.add(set);
        }
        return res;
    }

    public static Set<Integer> partialSumsBruteForce(Integer[] arr) {
        if (arr.length == 0) return Set.of(0);
        if (arr.length == 1) return Set.of(arr[0]);

        int totalSize = (int) Math.pow(2, arr.length);
        int counter = 0;

        var res = new ArrayList<HashSet<Integer>>();
        for (int i = 0; i < Math.pow(2, arr.length); i++) {
            var binary = Integer.toBinaryString(i);
            var set = new HashSet<Integer>();
            int count = 0;
            for (int bit = binary.length() - 1; bit >= 0; bit--) {
                var selectedBit = binary.charAt(bit);
                if (selectedBit == '1') set.add(arr[count++]);
                else count++;
            }
            res.add(set);
        }

        var sums = new HashSet<Integer>();

        for (HashSet<Integer> re : res) {
            int sum = 0;
            for (Integer integer : re) {
                sum += integer;
            }
            sums.add(sum);
        }
        return sums;

    }


}
