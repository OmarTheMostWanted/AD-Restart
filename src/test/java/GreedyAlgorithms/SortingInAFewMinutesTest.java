package GreedyAlgorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SortingInAFewMinutesTest {
    @Test
    @Timeout(1)
    public void example1() {
        int n = 2;
        String[] names = {"Phoenix", "Maya"};
        int[] sids = {42, 24};
        Assertions.assertEquals("Maya", SortingInAFewMinutes.sorting1(n, names, sids));
    }

    @Test
    @Timeout(1)
    public void example2() {
        int n = 2;
        String[] names = {"Phoenix", "Maya"};
        int[] sids = {24, 42};
        Assertions.assertEquals("Maya", SortingInAFewMinutes.sorting2(n, names, sids));
    }

    @Test
    @Timeout(1)
    public void example3() {
        int n = 2;
        String[] names = {"Phoenix", "Maya"};
        int[] sids = {24, 42};
        Assertions.assertEquals("Maya", SortingInAFewMinutes.sorting3(n, names, sids));
    }

    @Test
    @Timeout(1)
    public void example4() {
        int n = 2;
        String[] names = {null, "Phoenix", "Maya"};
        int[] sids = {0, 42, 24};
        Assertions.assertEquals("Maya", SortingInAFewMinutes.sorting4(n, names, sids));
    }

    @Test
    @Timeout(2)
    public void largeExample1() {
        int n = 1000;
        Random rng = new Random(1234);
        String[] names = new String[n];
        int[] sids = new int[n];
        for (int i = 0; i < n; i++) {
            names[i] = generateString();
            sids[i] = rng.nextInt(15000);
        }
        String min = names[0];
        int minSid = sids[0];
        for (int i = 0; i < n; i++) {
            if (sids[i] < minSid) {
                min = names[i];
                minSid = sids[i];
            }
        }
        // Note that the above is the cheating O(n) way. You should implement sorting instead!
        Assertions.assertEquals(min, SortingInAFewMinutes.sorting1(n, names, sids));
    }

    @Test
    @Timeout(2)
    public void largeExample2() {
        int n = 1000;
        Random rng = new Random(1234);
        String[] names = new String[n];
        int[] sids = new int[n];
        for (int i = 0; i < n; i++) {
            names[i] = generateString();
            sids[i] = rng.nextInt(15000);
        }
        String min = names[0];
        int minSid = sids[0];
        for (int i = 0; i < n; i++) {
            if (sids[i] > minSid) {
                min = names[i];
                minSid = sids[i];
            }
        }
        // Note that the above is the cheating O(n) way. You should implement sorting instead!
        Assertions.assertEquals(min, SortingInAFewMinutes.sorting2(n, names, sids));
    }

    @Test
    @Timeout(2)
    public void largeExample3() {
        int n = 1000;
        Random rng = new Random(1234);
        String[] names = new String[n];
        int[] sids = new int[n];
        for (int i = 0; i < n; i++) {
            names[i] = generateString();
            sids[i] = rng.nextInt(15000);
        }
        String min = names[0];
        int minSid = sids[0];
        for (int i = 0; i < n; i++) {
            if (names[i].compareTo(min) < 0) {
                min = names[i];
                minSid = sids[i];
            }
        }
        // Note that the above is the cheating O(n) way. You should implement sorting instead!
        Assertions.assertEquals(min, SortingInAFewMinutes.sorting3(n, names, sids));
    }

    @Test
    @Timeout(2)
    public void largeExample4() {
        int n = 1000;
        Random rng = new Random(1234);
        String[] names = new String[n + 1];
        int[] sids = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            names[i] = generateString();
            sids[i] = rng.nextInt(15000);
        }
        String min = names[1];
        int minSid = sids[1];
        for (int i = 1; i <= n; i++) {
            if (sids[i] < minSid) {
                min = names[i];
                minSid = sids[i];
            }
        }
        // Note that the above is the cheating O(n) way. You should implement sorting instead!
        Assertions.assertEquals(min, SortingInAFewMinutes.sorting4(n, names, sids));
    }

    public static String generateString() {
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        return uuid;
    }
}