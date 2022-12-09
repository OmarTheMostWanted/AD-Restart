package DivideAndConquer;

public class MergeSort {
    /**
     * Takes an array and sorts it in an ascending order. Note that the method is void, so it should
     * sort the input, rather than return a sorted copy.
     *
     * @param arr - the array that needs to be sorted.
     */
    public void sort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private void merge(int[] arr, int left, int mid, int right) {
        var l = left;
        var r = mid + 1;

        if (arr[mid] <= arr[right]) return;

        while (l <= mid && right >= r) {
            if (arr[l] <= arr[r]) {
                l++;
            } else {
                int value = arr[r];
                int i = r;
                while (i != l) {
                    arr[i] = arr[i - 1];
                    i--;
                }
                arr[l] = value;
                l++;
                mid++;
                r++;
            }
        }
    }

    private void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(arr , l , m);
            mergeSort(arr , m + 1 , r);
            merge(arr , l , m , r);
        }
    }
}
