package GreedyAlgorithms;

import Tools.MergeSort;

import java.util.ArrayList;

public class FindingTheMedian {

    public static double solve(int n, double[] list) {

        MergeSort<Double> mergeSort = new MergeSort<>();

        var doubles = new ArrayList<Double>(n);

        for (double v : list) {
            doubles.add(v);
        }

        var res = mergeSort.Sort(doubles);


        if (n % 2 == 1) {
            return res.get(n / 2);
        } else {
            return (res.get(n / 2) + res.get((n / 2) - 1)) / 2;
        }
    }

}
