package goatz92.cf7.Project02;

/**
 * Searches for the maximum sum of integers found in an array
 * CF7 Algorithm explanation:
 * Traverse the array
 * The first for loop iterates from 0 to n - 1 (where n = arr.length)
 * The second for loop iterates from j to n - 1 (where j = i)
 * For each iteration we reset the subArraySum to 0
 * Then we add the subArraySum to the current integer of the main array
 *
 */

public class Project02Main {

    public static void main(String[] args) {

        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int maxSum = maxSumResult(arr);
        System.out.println("The maximum sum of the array is: " + maxSum);
    }

    /**
     * Traverses the array
     * Looks at every possible sum of contiguous integers
     * Produces the maximum sum found
     * @param arr The array to be analyzed
     * @return The maximum sum of integers found in the array
     */
    public static int maxSumResult (int[] arr) {

        // In this case could be set as currentSum = -100
        // Integer.MIN_VALUE counts for all possibilities
        int currentMaxSum = Integer.MIN_VALUE; // The max sum found in each iteration
        int subArraySum; // Temporary sum to be compared to currentMaxSum

        for (int i = 0; i < arr.length; i++) {
            subArraySum = 0; // Reset the temp array sum
            for (int j = i; j < arr.length; j++) {
                subArraySum += arr[j];
                if (subArraySum > currentMaxSum) {
                    currentMaxSum = subArraySum;
                }
            }
        }
        return currentMaxSum;
    }
}
