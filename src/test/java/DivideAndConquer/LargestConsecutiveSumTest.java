package DivideAndConquer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


class LargestConsecutiveSumTest {
    @Test
    public void testExampleA() {
        int[] input = new int[] {2, -3, 2, 1};
        Assertions.assertEquals(3, LargestConsecutiveSum.largestSum(input));
    }

    @Test
    public void testExampleB() {
        int[] input = new int[] {3, -3, -2, 42, -11, 2, 4, 4};
        Assertions.assertEquals(42, LargestConsecutiveSum.largestSum(input));
    }

    @Test
    public void totalSum(){
        int[] arr = new int[10];
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
            sum+=i;
        }
        Assertions.assertEquals(sum , LargestConsecutiveSum.largestSum(arr));
    }

    @Test
    public void reversed(){
        int[] arr = new int[10];
        int sum = 0;

        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = i;
            sum+=i;
        }
        Assertions.assertEquals(sum , LargestConsecutiveSum.largestSum(arr));
    }

    @Test
    public void oneLargeNumber(){
        int[] arr = new int[]{1 , 1 , 1 , 1 , 1, 1,-5 ,6 };
        Assertions.assertEquals(6 , LargestConsecutiveSum.largestSum(arr));
    }

    @Test
    public void test1(){
        int[] arr = new int[]{1 , 1 , 1 , 1 , 1, 1,-5 ,6  , 1};
        Assertions.assertEquals(7 , LargestConsecutiveSum.largestSum(arr));
    }

    @Test
    public void test2(){
        int[] arr = new int[]{1 , 1 , 1 , 1 , 1, 1,-5 ,1  , 6};
        Assertions.assertEquals(7 , LargestConsecutiveSum.largestSum(arr));
    }

    @Test
    public void test3(){
        int[] arr = new int[]{1 , 1 , 1 , 1 , 1, 1,-5 , 5};
        Assertions.assertEquals(6 , LargestConsecutiveSum.largestSum(arr));
    }


    @Test
    public void test4(){
        int[] arr = new int[]{10 , 10 , 10 , 10 , 10, 10,-100 , 500};
        Assertions.assertEquals(500 , LargestConsecutiveSum.largestSum(arr));
    }

}