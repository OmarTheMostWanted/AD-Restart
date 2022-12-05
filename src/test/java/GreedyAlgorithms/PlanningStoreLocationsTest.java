package GreedyAlgorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import GreedyAlgorithms.PlanningStoreLocations.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class PlanningStoreLocationsTest {
    @Test
    public void twoForOne() {
        int n = 2;
        int k = 1;
        List<House> houses = new ArrayList<>();
        houses.add(new House(0, 1, 1));
        houses.add(new House(1, 3, 3));
        Set<Store> expected = new HashSet<>();
        expected.add(new Store(2, 2));
        Assertions.assertEquals(expected, PlanningStoreLocations.donutTime(n, k, houses));
    }
}