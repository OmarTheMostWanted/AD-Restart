package Tools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {
    @Test
    public void Random(){
        Random r = new Random();
        int[] arr = new int[1000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(100);
        }

        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i : arr) {
            arrayList.add(i);
        }
        var res = new MergeSort<Integer>().Sort(arrayList);

        int[] newList = new int[arr.length];
        for (int i = 0; i < newList.length; i++) {
            newList[i] = res.get(i);
        }
        arr = newList;

        int min = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            Assertions.assertTrue(min <= arr[i]);
            min = arr[i];
        }
    }
}