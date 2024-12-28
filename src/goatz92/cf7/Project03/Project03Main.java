package goatz92.cf7.Project03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Reads from a .txt file
 * Then saves each char read, in a 2d array
 * The rows of the array represent each char.
 * The columns of the array represent the number of times the char has occurred.
 * Ignores white spaces by use of Character.isWhitespace java class.
 * Prints how many times every char has occurred. (char frequency)
 */
public class Project03Main {

    static final int ARRAY_SIZE = 128; // The maximum size of the Array
    public static void main(String[] args) {

        String filePath = "C:/tmp/Test.txt"; //TODO Change file path to be given by user
        String[][] charCount = new String[ARRAY_SIZE][2];

        // Read the file and populate the character count array
        fileInputCharCount(filePath, charCount);

        // Sort and display the results
        printCharFrequency(charCount);
    }

    /**
     * Reads characters from the .txt file specified
     * Then counts how many times the char has occurred.
     *
     * @param filePath The path to the .txt file to read.
     * @param charCount Array that stores char count
     */
    private static void fileInputCharCount(String filePath, String[][] charCount) {

        for (int i = 0; i < ARRAY_SIZE; i++) {
            charCount[i][0] = null;
            charCount[i][1] = "0";
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            int ch;
            while ((ch = br.read()) != -1) {
                // Ignore whitespaces
                if (Character.isWhitespace(ch)) {
                    continue;
                }
                updateCharCount(ch, charCount);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the character count in the array.
     *
     * @param ch The character to count.
     * @param charCount The array to store character counts.
     */
    private static void updateCharCount(int ch, String[][] charCount) {

        for (int i = 0; i < ARRAY_SIZE; i++) {
            if (charCount[i][0] != null && charCount[i][0].charAt(0) == ch) {
                // Character found, increment the count
                int count = Integer.parseInt(charCount[i][1]);
                count++;
                charCount[i][1] = String.valueOf(count);
                break;
            } else if (charCount[i][0] == null) {
                // New character, add it to the array
                charCount[i][0] = String.valueOf((char) ch);
                charCount[i][1] = "1";
                break;
            }
        }
    }

    /**
     * Sorts the character count array and prints the frequencies.
     *
     * @param charCount The array containing character counts.
     */
    private static void printCharFrequency(String[][] charCount) {
        // Sort the array by character
        Arrays.sort(charCount, (a, b) -> {
            if (a[0] == null) return 1; // Put nulls at the end
            if (b[0] == null) return -1;
            return a[0].compareTo(b[0]); // Sort by character
        });

        // Prints frequency for each char
        System.out.println("Frequency of characters:");
        for (String[] entry : charCount) {
            if (entry[0] != null) {
                System.out.println("Character: " + entry[0] + ", Count: " + entry[1]);
            }
        }
    }
}
