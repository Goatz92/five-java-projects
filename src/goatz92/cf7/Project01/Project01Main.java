package goatz92.cf7.Project01;

import java.io.*;
import java.util.Arrays;
import java.util.Random;

/**
 * Generates a random assortment of 6 to 49 numbers with values ranging between 1 and 49,
 * Saves them in an array and creates a .txt file with the data produced.
 * Finally outputs a .txt file named validCombinations
 * containing all the valid combinations of six numbers found in the array.
 * Criteria of a combination being valid are:
 * No more than 4 even numbers.
 * No more than 4 odd numbers.
 * No more than 2 consecutive numbers.
 * No more than 3 numbers ending with the same digit.
 * No more than 3 numbers from the same decade.
 * Appendix:
 * RNG = for Random number generator
 * Combo = Combination
 */

//TODO
// Ask the user on program start, if they want to input a .txt file of their own (if not continue set process)
// User must also then, provide an input and output Path for the file (change final String FILE_PATH & OUTPUT_FILE_PATH to Scanner input)
// The file must be checked for validity, (must contain only integers(add exceptions to handle possible errors))
// Then we need to create an array with the same number of elements as the file.

public class Project01Main {

    static final int ARRAY_SIZE = 49; //The standard maximum size of the array
    static final String FILE_PATH = "C:/tmp/randomNumbers.txt"; // File path for saving and reading the random numbers
    static final String OUTPUT_FILE_PATH = "C:/tmp/validCombinations.txt"; //File path for final txt file containing all combinations.

    public static void main(String[] args) {

        //Empty array to be populated and handled.
        int[] arr;

        // Output random numbers to a file
        rngFileOutput();

        // Input random numbers from the file
        arr = rngFileInput();

        // Sort the array of random numbers
        Arrays.sort(arr);

        validComboOutput(arr);
        System.out.println("Thanks for using the App! :)");
    }

    /**
     * Creates an Array and populates it with random numbers from 1 to 49.
     * Numbers may be duplicates of previous ones.
     * Then creates a file named randomNumbers.txt
     * That contains all the numbers in the array
     * This method does nothing for the overall App
     * And could be replaced by any other assortment of numbers
     * Input by a user or .txt file
     * But tries to simulate a scenario where we are not the ones producing the list of numbers
     * So we can have different results in each run, for more accurate testing.
     */
    public static void rngFileOutput() {
        int[] rngArray = new int[ARRAY_SIZE];

        Random rand = new Random();

        // Populate the array with random integers ranging from 1 to 49.
        //TODO Currently the array is filled with random integers
        // This produces duplicate numbers
        // If we want to produce unique numbers when ARRAY_SIZE = 49;
        // Then after shorting the array the numbers will be 1,2,3,...,49 in order
        // Change the ARRAY_SIZE to something smaller than 49
        // and change for loop to generate unique numbers each time
        for (int i = 0; i < ARRAY_SIZE; i++) {
            rngArray[i] = (rand.nextInt(49) + 1);     // rand.nextInt produces numbers between 0 and 48. +1 lets us meet the intended range.
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (int i = 0; i < ARRAY_SIZE; i++) {
                writer.write(rngArray[i] + "\n");
            }
            System.out.println("Array has been saved to randomNumbers.txt");
        } catch (IOException e) {
            System.err.println("Error occurred while writing file: " + e.getMessage());
        }
    }

    /**
     * Reads from a .txt file a dedicated number of integers.
     * Then populates an array with those integers
     * TODO Find a way to change the array size depending on how many integers are in the txt file.
     *
     * @return The fully populated array
     */
    public static int[] rngFileInput() {
        int[] arr = new int[ARRAY_SIZE];
        String line = "";
        int index = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            while ((line = reader.readLine()) != null && index < ARRAY_SIZE) {
                try {
                    //Reads the file values as string and converts them into integers
                    arr[index++] = Integer.parseInt(line);
                } catch (NumberFormatException e) {
                    //Handles cases where line is not a valid integer
                    System.err.println("Error! Invalid number format: " + line);
                }
            }
            //Checks if file has enough numbers to fill the array.
            if (index < ARRAY_SIZE) {
                System.err.println("File contains fewer numbers than expected: " + ARRAY_SIZE);
            }
        } catch (IOException e) {
            System.err.println("Error occurred while reading the file: " + e.getMessage());
        }
        return arr;
    }

    /**
     * The method traverses through the given array, temporarily creates combinations of six numbers
     * then calls isValidCombo method to check if current combo meets the criteria.
     * Goes through all the possible combinations of numbers given in the array.
     * Finally outputs a .txt file with all the valid combinations found in the original array
     * @param arr The array read from the input file.
     */
    public static void validComboOutput(int[] arr) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE_PATH))) {
            // Iterate through all combinations of 6 numbers
            for (int i = 0; i < arr.length - 5; i++) {
                for (int j = i + 1; j < arr.length - 4; j++) {
                    for (int k = j + 1; k < arr.length - 3; k++) {
                        for (int l = k + 1; l < arr.length - 2; l++) {
                            for (int m = l + 1; m < arr.length - 1; m++) {
                                for (int n = m + 1; n < arr.length; n++) {
                                    // Create the combination of 6 numbers
                                    int[] sixCombo = {arr[i], arr[j], arr[k], arr[l], arr[m], arr[n]};

                                    // Check if the combination meets the criteria
                                    if (isValidCombo(sixCombo)) {
                                        //System.out.println("FoundOne");
                                        // Write the valid combination to the file
                                        writer.write(Arrays.toString(sixCombo));
                                        writer.newLine();
                                    }
                                }
                            }
                        }
                    }
                }
            }
            System.out.println("Valid combinations have been saved to validCombinations.txt");
        } catch (IOException e) {
            System.err.println("Error occurred while writing to the file: " + e.getMessage());
        }
    }

    /**
     * Checks if all given criteria are true, by calling a different method for each one.
     * No more than 4 even numbers.
     * No more than 4 odd numbers.
     * No more than 2 consecutive numbers.
     * No more than 3 numbers ending with the same digit.
     * No more than 3 numbers from the same decade.
     * @param sixCombo The current combination of six numbers produced by the validComboOutput method.
     * @return True if all criteria are met
     */
    public static boolean isValidCombo(int[] sixCombo) {
        return isEven(sixCombo) &&  isOdd(sixCombo) && isConsecutive(sixCombo) && isSameEnding(sixCombo) && isSameDecade(sixCombo);
    }

    /**
     * Checks if the combo has:
     * No more than 4 even numbers.
     * @param sixCombo The current combination of six numbers produced by the validComboOutput method.
     * @return True if less than 4 even numbers are found
     */
    public static boolean isEven(int[] sixCombo) {
        int evenCount = 0;
        for (int num : sixCombo) {
            if (num % 2 == 0) {
                evenCount++;
            }
        }
        return evenCount <= 4;
    }

    /**
     * Checks if the combo has:
     * No more than 4 odd numbers.
     * @param sixCombo The current combination of six numbers produced by the validComboOutput method.
     * @return True if less than 4 odd numbers are found
     */
    public static boolean isOdd(int[] sixCombo) {
        int oddsCount = 0;

        for (int num : sixCombo) {
            if (num % 2 != 0) oddsCount++;
        }

        return oddsCount <= 4;
    }

    /**
     * Checks if the combo has:
     * No more than 2 consecutive numbers.
     * @param sixCombo The current combination of six numbers produced by the validComboOutput method.
     * @return True if less than 2 consecutive numbers are found
     */
    public static boolean isConsecutive(int[] sixCombo) {

        int consecutiveCount = 1;  // Counts the first number

        for (int i = 0; i < sixCombo.length - 1; i++) {
            // Checks if the next number is consecutive to the current number
            if (sixCombo[i] + 1 == sixCombo[i + 1]) {
                consecutiveCount++;  // Increment the count of consecutive numbers
                // If we have more than 3 consecutive numbers, return false
                if (consecutiveCount >= 3) {
                    return false;
                }
            } else {
                consecutiveCount = 1;  // Resets the count if the numbers are not consecutive
            }
        }
        return true;  // Returns true if there are at most 3 consecutive numbers
    }

    /**
     * Checks if the combo has:
     * No more than 3 numbers ending with the same digit.
     * @param sixCombo The current combination of six numbers produced by the validComboOutput method.
     * @return True if less than 3 numbers with the same ending digit are found
     */
    public static boolean isSameEnding (int[] sixCombo) {
        int[] digitCount = new int[10];

        for (int num : sixCombo) {
            int lastDigit = num % 10;
            digitCount[lastDigit]++;
        }
        for (int count : digitCount) {
            if (count >= 3) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the combo has:
     * No more than 3 numbers from the same decade.
     * @param sixCombo The current combination of six numbers produced by the validComboOutput method.
     * @return True if less than 3 numbers from the same decade are found
     */
    public static boolean isSameDecade(int[] sixCombo) {
        // Each element of this array represents a decade
        int[] ten = new int[5];

        // Loops through each number in the combination of 6 numbers
        for (int num : sixCombo) {
            // Determines which decade (group of 10) the number belongs to.
            ten[num / 10]++;
        }
        // Loops through the decade count array to check if any decade has more than 3 numbers
        for (int count : ten) {
            if (count >= 3) {
                return false;
            }
        }
        return true;
    }
}