package DynamicProgramming;

public class AnOldFavourite {
    /**
     * Returns the n'th Fibonacci number
     */
    public static int fibonacci(int n) {
        // The array in which you must implement your memoization.
        int[] fibonacci = new int[n + 1];
        // TODO fibonacci[0] = ...; // Implement the base cases here
        if(n == 0) return 0;

        // TODO fibonacci[1] = ...;
        if(n == 1) return 1;

        // After that, iterate through all fibonacci numbers from index 2 up to n.
        for (int i = 2; i <= n; i++) {
            if(fibonacci[i - 1] == 0){
                fibonacci[i - 1] = fibonacci(i - 1);
            }
            if(fibonacci[i - 2] == 0){
                fibonacci[i - 2] = fibonacci(i - 2);
            }

            fibonacci[i] = fibonacci[i-1]+fibonacci[i-2];

        }
        // Returning the obtained fibonacci value at index n.
        return fibonacci[n];
    }
}
