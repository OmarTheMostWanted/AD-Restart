package DivideAndConquer;

public class LargestConsecutiveSum {

    public static int largestSum(int[] arr) {
        if (arr.length == 0) return 0;
        return sum(arr, 0, arr.length - 1);
    }

    public static int sum(int[] arr, int left, int right) {
        if (left == right) return arr[left];
        int midde = (right + left) / 2;
        int leftSum = 0;
        int counterLeft = 1;
        leftSum = arr[midde];
        int temp = leftSum;
        while (midde - counterLeft >= left) {
            temp = temp + arr[midde - counterLeft];
            if (leftSum <= temp) {
                leftSum = temp;
                counterLeft++;
            } else counterLeft++;
        }

        int rightSum = 0;
        int counterRight = 1;
        rightSum = arr[midde + 1];
        temp = rightSum;
        while (midde + 1 + counterRight <= right) {
            temp = temp + arr[midde + 1 + counterRight];
            if (rightSum <= temp) {
                rightSum = temp;
                counterRight++;
            } else counterRight++;
        }

        var leftSide = sum(arr, left, midde);
        var rightSide = sum(arr, midde + 1, right);
        var current = leftSum + rightSum;
        var max =  Integer.max(leftSide, Integer.max(rightSide, current));
        return max;
    }

    private static int largestSum(int[] arr, int from, int to) {
        if (to < from) return 0;
        if (to == from) return arr[from];

        int largest = Integer.MIN_VALUE;
        for (int i = from; i < to; i++) {
            int left = arr[i];
            int counter;
//            while (i - counter >= 0 && left + arr[i - counter] >= left) {
//                left = left + arr[i - counter++];
//            }
            int right = arr[i];
            counter = 1;
            while (i + counter < to && right + arr[i + counter] >= right) {
                right = right + arr[i + counter++];
            }
//            if (largest < left) largest = left;
            if (largest < right) {
                largest = right;
                i = counter;
            }
        }
        return largest;
    }

    public static int helper(int[] arr, int step) {
        int largest = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i += step) {
            int sum = arr[i];
            for (int k = i; k < i + step; k++) {
                if (arr[k] + sum >= sum) sum += arr[k];
                else break;
            }
            if (sum > largest) largest = sum;
        }
        return Integer.max(largest, helper(arr, step++));
    }

    public static int largestSumBruteForce(int[] arr) {
        if (arr.length == 0) return 0;
        if (arr.length == 1) return arr[0];

        int largest = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int left = arr[i];
            int counter;
//            while (i - counter >= 0 && left + arr[i - counter] >= left) {
//                left = left + arr[i - counter++];
//            }
            int right = arr[i];
            counter = 1;
            while (i + counter < arr.length && right + arr[i + counter] >= right) {
                right = right + arr[i + counter++];
            }
//            if (largest < left) largest = left;
            if (largest < right) {
                largest = right;
                i = counter;
            }
        }
        return largest;
    }
}
