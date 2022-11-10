package GreedyAlgorithms;

import java.util.ArrayList;
import java.util.Arrays;

public class PackingTrucks {

    /**
     * @param n the number of packages
     * @param weights the weights of all packages 1 through n. Note that weights[0] should be
     *     ignored!
     * @param maxWeight the maximum weight a truck can carry
     * @return the minimal number of trucks required to ship the packages _in the given order_.
     */
    public static int minAmountOfTrucks(int n, int[] weights, int maxWeight) {
        int trucks = 0;

        if(weights == null || n == 0 || weights.length <= 1)
            return trucks;

        ArrayList<Integer> weightsList = new ArrayList<>(weights.length);
        for (int i = 1; i < weights.length; i++) {
            weightsList.add(weights[i]);
        }
        int truck;
        while (!weightsList.isEmpty()){
            trucks++;
            truck = 0;
            for (int current =  weightsList.size() - 1; current >= 0; current--) {
                if (truck + weightsList.get(current) <= maxWeight) {
                    truck += weightsList.get(current);
                    weightsList.remove(current);
                } else {
                    break;
                }
            }
        }
        return trucks;
    }

    public static int readTheDamnQuestion(int n, int[] weights, int maxWeight) {
        int trucks = 0;

        if(weights == null || n == 0 || weights.length <= 1)
            return trucks;

        Arrays.sort(weights , 1 , n);
        ArrayList<Integer> weightsList = new ArrayList<>(weights.length);
        for (int i = 1; i < weights.length; i++) {
            weightsList.add(weights[i]);
        }
        int truck;
        while (!weightsList.isEmpty()){
            trucks++;
            truck = 0;
            for (int current =  weightsList.size() - 1; current >= 0; current--) {
                if (truck + weightsList.get(current) <= maxWeight) {
                    truck += weightsList.get(current);
                    weightsList.remove(current);
                }
            }
        }
        return trucks;
    }
}
