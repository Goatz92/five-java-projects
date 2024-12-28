package goatz92.cf7.Project04;

import java.util.InputMismatchException;
import java.util.Scanner;

public class test {

    static char[][] board = {
            {'-', '-', '-'},
            {'-', '-', '-'},
            {'-', '-', '-'}
    };

    static int[][] grid = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
    };

    static char currentPlayer = 'X';

    public static void main(String[] args) {

        System.out.println("Welcome to Tic Tac Toe");
        printBoardMap();
        System.out.println();
        printCurrentBoard();
        playerMove();
    }

    /**
     * Prints out the board map to help players
     */
    public static void printBoardMap() {

        System.out.println("\nSubmit your next move by pressing a number " +
                "\nfrom the board map and then hit 'Enter'");
        System.out.println("Board map: \n");


        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print("[" + grid[i][j] + "] ");
            }
            System.out.println();
        }
    }

    private static void printCurrentBoard() {
        System.out.println("Current board:");
        for (int i = 0; i < 3; i++) {
            System.out.print(" | ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("---------------");
        }
    }

    private static void playerMove() {
        Scanner in = new Scanner(System.in);
        int playerChoice = 0;
        try {
            playerChoice = in.nextInt();
            if (!(playerChoice > 0 && playerChoice <= 9)) {
                System.out.println("Please enter a valid choice");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input; Enter a valid choice");
        }
        if (grid[playerChoice - 1].equals(String.valueOf(playerChoice))) {
        }
    }
}
