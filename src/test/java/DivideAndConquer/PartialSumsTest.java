package DivideAndConquer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static DivideAndConquer.PartialSums.createPowerSet;
import static DivideAndConquer.PartialSums.partialSumsBruteForce;

class PartialSumsTest {

    @Test
    public void powerSets(){
        Integer[] test = new Integer[10];
        Integer[] part1 , part2;
        for (int i = 0; i < test.length; i++) {
            test[i] = i;
        }

        part1 = Arrays.copyOfRange(test , 0 , 5);
        part2 = Arrays.copyOfRange(test, 5 , 10);

        System.out.println(Arrays.toString(test));
        System.out.println(Arrays.toString(part1));
        System.out.println(Arrays.toString(part2));

        System.out.println(createPowerSet(test));
        System.out.println(createPowerSet(part1));
        System.out.println(createPowerSet(part2));

        System.out.println(partialSumsBruteForce(test));
        System.out.println(partialSumsBruteForce(part1));
        System.out.println(partialSumsBruteForce(part2));
    }


    @Test
    public void testExampleA() {
        Integer[] arr = new Integer[] { 1, 2 };
        Set<Integer> res = PartialSums.partialSums(arr);
        Set<Integer> expected = new HashSet<>(Arrays.asList(0, 1, 2, 3));
        Assertions.assertEquals(expected, res);
    }

    @Test
    public void testExampleB() {
        Integer[] arr = new Integer[] { 1, 2, 3, 4 };
        Set<Integer> res = PartialSums.partialSums(arr);
        Set<Integer> expected = new HashSet<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        Assertions.assertEquals(expected, res);
    }

    @Test
    public void testEmpty() {
        Integer[] arr = new Integer[] {};
        Set<Integer> res = PartialSums.partialSums(arr);
        Set<Integer> expected = new HashSet<>(Collections.singletonList(0));
        Assertions.assertEquals(expected, res);
    }
}