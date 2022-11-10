package GreedyAlgorithms;

import java.util.*;

public class SortingInAFewMinutes {
    static class SpecialMap extends HashMap<Integer, String> implements SortedMap<Integer, String> {

        @Override
        public Comparator<? super Integer> comparator() {
            return Integer::compareTo;
        }

        @Override
        public SortedMap<Integer, String> subMap(Integer fromKey, Integer toKey) {
            return null;
        }

        @Override
        public SortedMap<Integer, String> headMap(Integer toKey) {
            return null;
        }

        @Override
        public SortedMap<Integer, String> tailMap(Integer fromKey) {
            return null;
        }

        @Override
        public Integer firstKey() {
            var f = Integer.MAX_VALUE;
            for (Integer integer : super.keySet()) {
                if (integer < f) {
                    f = integer;
                }
            }
            return f;
        }

        @Override
        public Integer lastKey() {
            var f = Integer.MIN_VALUE;
            for (Integer integer : super.keySet()) {
                if (integer > f) {
                    f = integer;
                }
            }
            return f;
        }
    }


    /**
     * @param n              the number of students
     * @param names          their names
     * @param studentNumbers their student numbers
     * @return the name of the first student when sorted ascendingly by student number Note: of
     * course this can be done in O(n) time rather than O(n log n). This is _not_ the goal of
     * this exercise. Your focus should be on sorting, the return value is only so we have
     * something to run tests against.
     */
    public static String sorting1(int n, String[] names, int[] studentNumbers) {
        var map = new SpecialMap();
        for (int i = 0; i < n; i++) {
            map.put(studentNumbers[i] , names[i]);
        }
        return map.get(map.firstKey());
    }

    /**
     * @param n              the number of students
     * @param names          their names
     * @param studentNumbers their student numbers
     * @return the name of the first student when sorted _descendingly_ by student number Note: of
     * course this can be done in O(n) time rather than O(n log n). This is _not_ the goal of
     * this exercise. Your focus should be on sorting, the return value is only so we have
     * something to run tests against.
     */
    public static String sorting2(int n, String[] names, int[] studentNumbers) {
        var map = new HashMap<Integer , String>();
        for (int i = 0; i < n; i++) {
            map.put(studentNumbers[i], names[i]);
        }
        Arrays.sort(studentNumbers);
        return map.get(studentNumbers[n-1]);
    }

    /**
     * @param n              the number of students
     * @param names          their names
     * @param studentNumbers their student numbers
     * @return the name of the first student when sorted ascendingly by name Note: of course this
     * can be done in O(n) time rather than O(n log n). This is _not_ the goal of this exercise.
     * Your focus should be on sorting, the return value is only so we have something to run
     * tests against.
     */
    public static String sorting3(int n, String[] names, int[] studentNumbers) {
        Arrays.sort(names);
        return names[0];
     }

    /**
     * @param n              the number of students
     * @param names          their names, in indices _1_ through _n_ including! You should ignore names[0].
     * @param studentNumbers their student numbers, in indices _1_ through _n_ including! You should
     *                       ignore studentNumbers[0].
     * @return the name of the first student when sorted ascendingly by student number. Note: of
     * course this can be done in O(n) time rather than O(n log n). This is _not_ the goal of
     * this exercise. Your focus should be on sorting, the return value is only so we have
     * something to run tests against. Hint: take a look at what other parameters Arrays.sort
     * can take.
     */
    public static String sorting4(int n, String[] names, int[] studentNumbers) {
        var map = new SpecialMap();
        for (int i = 0; i < n; i++) {
            map.put(studentNumbers[i+1] , names[i+1]);
        }
        return map.get(map.firstKey());
    }
}
