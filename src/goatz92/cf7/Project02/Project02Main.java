package goatz92.cf7.Project02;

/**
<<<<<<< HEAD
 * 1) Solution one is more of a brute force approach to the solution.
 * Traverses the array linearly and checks every
 * finds all the possible contiguous sub arrays and
 * calculates their sum.
 * Complexity is O(n^2), because we need two for loops to traverse the array.
 * 2) Solution two uses Kadane's algorithm
 * Traverses the array and checks every contiguous number
 * compares the localMaxSum at every index with the
 * globalMaxSum and update the globalMaxSum accordingly.
 * Complexity is O(n) because the algorithm requires only one
 * for loop and one pass through the array.
 */
=======
 * Searches for the maximum sum of integers found in an array
 * CF7 Algorithm explanation:
 * Traverse the array
 * The first for loop iterates from 0 to n - 1 (where n = arr.length)
 * The second for loop iterates from j to n - 1 (where j = i)
 * For each iteration we reset the subArraySum to 0
 * Then we add the subArraySum to the current integer of the main array
 *
 */

>>>>>>> 0e46057c2814105badfd50d27ccd3f6659a75da3
public class Project02Main {

    public static void main(String[] args) {

        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        System.out.println("Brute Force approach: ");
        int maxSumBrute = maxSumResult(arr);
        System.out.println("The maximum sum of the array is: " + maxSumBrute);

        System.out.println("Kadane's Algorithm solution: ");
        int maxSumKadane = maxSumKadane(arr);
        System.out.println("The maximum sum of the array is: " + maxSumKadane);
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
        int globalMaxSum = Integer.MIN_VALUE; // The max sum found in each iteration
        int localMaxSum = 0; // Temporary sum to be compared to globalMaxSum

        for (int i = 0; i < arr.length; i++) {
            localMaxSum = 0; // Reset the temp array sum
            for (int j = i; j < arr.length; j++) {
                localMaxSum += arr[j];
                if (localMaxSum > globalMaxSum) {
                    globalMaxSum = localMaxSum;
                }
            }
        }
        return globalMaxSum;
    }

    public static int maxSumKadane (int[] arr) {

        // In this case could be set as currentSum = -100
        // Integer.MIN_VALUE counts for all possibilities
        int globalMaxSum = Integer.MIN_VALUE; // The max sum found in each iteration
        int localMaxSum = 0; // Temporary sum to be compared to globalMaxSum
        int indexStart = 0; // The start index of the maxSum sub array
        int indexEnd = 0; // The end index of maxSum sub array
        int tempStart = 0; // Temporary start index

        for (int i = 0; i < arr.length; i++) {
            //Compares the localMaxSum with globalMaxSum at every index
            localMaxSum = Math.max(arr[i], arr[i] + localMaxSum);
            if (localMaxSum == arr[i]) {
                tempStart = i;
            }
            //Updates globalMaxSum and saves the current start-end indexes
            if (localMaxSum > globalMaxSum) {
                globalMaxSum = localMaxSum;
                indexEnd = i;
                indexStart = tempStart; // Update start index when needed
            }
        }
        System.out.print("Maximum sum sub array is: { ");
        for (int i = 0; i < indexEnd; i++) {
            System.out.print(arr[indexStart + i] + " ");
        }
        System.out.print("}");
        System.out.println();
        return globalMaxSum;
    }
}
