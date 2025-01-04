package goatz92.cf7.Project04;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Project04Main {

    //Grid map
    static int[][] grid = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
    };
    //Printed Grid
    static String[][] board = {
            {"-", "-", "-",},
            {"-", "-", "-",},
            {"-", "-", "-",}
    };
    static String currentPlayer = "X";
    static int winCondition = 0;

    public static void main(String[] args) {

           while (true) {
               grid = gridUpdater(grid,board);
               boardPrinter(board);
           }
    }

    /**
     * Inputs the first version of the grid and the board
     * Asks for the player to make a "move" (first player is always "X")
     * Checks every position of the grid and updates it
     * Depending on player choice
     * Updates the board with X and O
     * Depending on the currentPlayer
     * Alternates between player X and player O
      * @param grid The grid that calculates the moves made
     * @param board The current version the printed board
     */
    public static int[][] gridUpdater (int[][] grid, String[][]board) {
        Scanner in = new Scanner(System.in);
        int playerChoice = 0; //Player must choose from 1 to 9
        try {
            playerChoice = in.nextInt();
            if (!(playerChoice > 0 && playerChoice <= 9)) {
                System.out.println(
                        "Invalid input; re-enter slot number:");
            }
        }
        catch (InputMismatchException e) {
            System.out.println(
                    "Invalid input; re-enter slot number:");
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (playerChoice == grid[i][j]) {
                    board[i][j] = currentPlayer; //updates the board (with X,O) with all moves played so far
                    if (currentPlayer.equals("X")) {
                        currentPlayer = "O";
                        grid[i][j] = 10; //arbitrary number that is not included in the array. indicates that a move has already been played
                    } else {
                        currentPlayer = "X";
                        grid[i][j] = 20; //arbitrary number that is not included in the array. indicates that a move has already been played
                    }
                }
                checkWinner(grid);
            }
            System.out.println();
        }
        return grid;
    }

    /**
     * Inputs the first and each updated version of the board
     * Prints it
     * @param board The first and every updated version of the board to be printed.
     */
    public static void boardPrinter (String[][]board) {
        for (int i = 0; i < 3; i++) {
            System.out.print(" | ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("---------------");
        }
    }
    public static String checkWinner (int[][]grid) {

        for(int i = 0; i < 8; i++){
            switch (i) {
                case 0:
                    winCondition = grid[0][0] + grid[0][1] + grid[0][2];
                case 1:
                    winCondition = grid[1][0] | grid[1][1] | grid[1][2];
                case 2:
                    winCondition = grid[2][0] | grid[2][1] | grid[2][2];
            }
            if (winCondition == 30) {
                System.out.println("Player X wins");
                break;
            } else if (winCondition == 60) {
                System.out.println("Player O wins");
                break;
            }
        }

        return null;
    }
}