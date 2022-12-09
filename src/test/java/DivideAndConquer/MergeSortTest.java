package DivideAndConquer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {
    @Test
    @Timeout(100)
    public void example() {
        int[] input = {4, 2, 5, 1, 3};
        new MergeSort().sort(input);
        Assertions.assertArrayEquals(new int[] {1, 2, 3, 4, 5}, input);
    }

    @Test
    public void Random(){
        Random r = new Random();
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(100);
        }
        new MergeSort().sort(arr);

        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i : arr) {
            arrayList.add(i);
        }

        int[] newList = new int[arr.length];
        for (int i = 0; i < newList.length; i++) {
            newList[i] = arrayList.get(i);
        }

        int min = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            Assertions.assertTrue(min <= arr[i]);
            min = arr[i];
        }

    }
}