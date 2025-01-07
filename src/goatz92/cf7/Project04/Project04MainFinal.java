package goatz92.cf7.Project04;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.*;

/**
 * Demo Tic Tac Toe game app
 *     In previous versions I tried to implement a different way to describe the board
 *     Tried to create two different 2D arrays
 *     One for the player named String[][]board
 *     And one for the program named int[][]grid
 *     I found out this was redundant because the board doesn't need to be two-dimensional.
 *     Making the board "grid" one-dimensional simplifies it's use and coding methods.
 *     We can think of the grid as:
 *     For the array's positions 0 through 2 we have the first row and so on.
 *     For the diagonals simply use position 0, 4 and 8 and vice versa(2, 4, 6)
 *     And for columns use positions 0, 3, 6
 */

public class Project04MainFinal {

    static String[] board; //One dimensional board to be printed and handled
    static String currentPlayer; //Determines the current player, First player is always X
    static String currentPlayerName; //The current player's name as input by each user. Alternates between the two.
    static String playerOne;
    static String playerTwo;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        board = new String[9];
        currentPlayer = "X";
        String winner = null;

        //Populate the board with numbers from 1 to 9
        //This is essential because we need a way to match the board
        //To each player's input
        for (int a = 0; a < 9; a++) {
            board[a] = String.valueOf(a + 1);
        }

        System.out.println("Welcome to 3x3 Tic Tac Toe.");
        printBoard();
        System.out.println("Player One: Please enter a name");
        playerOne = in.nextLine();
        currentPlayerName = playerOne;
        System.out.println("Player Two: Please enter a name");
        playerTwo = in.nextLine();
        System.out.println(playerOne + " will play first. Choose wisely");

        while (winner == null) {

            playTurn(in);
            //Swap player names
            if (currentPlayerName.equals(playerOne)) {
                currentPlayerName = playerTwo;
            } else {
                currentPlayerName = playerOne;
            }
            System.out.println(currentPlayerName + "'s turn; enter a slot number to place " + currentPlayerName + " in:");
            winner = checkWinner();

        }
        //Checks for winner through static winner String
        //Prints draw if no win conditions were met
        if (winner.equalsIgnoreCase("draw")) {
            System.out.println("It's a draw! Thanks for playing.");
        }
        //Prints winner when the win condition is met
        else if (Objects.equals(currentPlayer, "O")) {
            System.out.println("Congratulations! " + playerOne + " You win!");
            System.out.println("Thanks for playing.");
        } else if (Objects.equals(currentPlayer, "X")) {
            System.out.println("Congratulations! " + playerTwo + " You win!");
            System.out.println("Thanks for playing.");
        }
        in.close(); //close the scanner
    }

    /**
     * Prints out the first and each subsequent version of the board.
     * Board layout:
     * |-----------|
     * | 1 | 2 | 3 |
     * | 4 | 5 | 6 |
     * | 7 | 8 | 9 |
     * |-----------|
     */
    static void printBoard () {

        System.out.println("|-----------|");
        System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
        System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
        System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
        System.out.println("|-----------|");
    }

    /**
     * Check each line and diagonal for win condition
     * Compare line variable string with each line of grid
     * Determine winner or tie
     * @return Value for String winner, ("X", "O", "draw")
     */
    public static String checkWinner() {

        for (int i = 0; i < 8; i++) {
            String line = switch (i) {
                case 0 -> board[0] + board[1] + board[2];
                case 1 -> board[3] + board[4] + board[5];
                case 2 -> board[6] + board[7] + board[8];
                case 3 -> board[0] + board[3] + board[6];
                case 4 -> board[1] + board[4] + board[7];
                case 5 -> board[2] + board[5] + board[8];
                case 6 -> board[0] + board[4] + board[8];
                case 7 -> board[2] + board[4] + board[6];
                default -> null;
                //Cases 0 to 3 check horizontal lines
                //Cases 3 to 5 check vertical lines
                //Cases 6 to 7 check diagonal lines
                //If a reaches a = 8 then no win condition was found so it's a draw
            };
            //X wins
            if (line.equals("XXX")) {
                return "X";
            }
            // O wins
            else if (line.equals("OOO")) {
                return "O";
            }
        }

        for (int i = 0; i < 9; i++) {
            //Temporarily converts board to a list and checks if the playerChoice is already on the board and breaks
            if (Arrays.asList(board).contains(String.valueOf(i + 1))) {
                break;
            }
            //No winning moves can be played
            else if (i == 8) {
                return "draw";
            }
        }

        return null;
    }

    /**
     * Asks the current player for a move (playerChoice)
     * Checks if the move is valid (needs to be an int from 1 to 9)
     * Then checks if the move is valid by checking if the slot in the array is already taken
     * Then if not, swaps players
     * @param in Scanner for move inputs
     */
    public static void playTurn (Scanner in){
        int playerChoice;

        // Exception handling for playerChoice input
        while (true) {
            try {
                playerChoice = in.nextInt();
                if (playerChoice > 0 && playerChoice <= 9) {
                    break; // Exit the loop if input is valid
                } else {
                    System.out.println("Invalid input; re-enter slot number:");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input; re-enter slot number:");
                in.nextLine(); // Consume invalid input
            }
        }

        // Check if the chosen slot is available
        if (board[playerChoice - 1].equals(String.valueOf(playerChoice))) {
            board[playerChoice - 1] = currentPlayer;

            // Alternate between players
            if (currentPlayer.equals("X")) {
                currentPlayer = "O";
            } else {
                currentPlayer = "X";
            }
            printBoard();
        } else {
            System.out.println("Slot already taken; re-enter slot number:");
            playTurn(in); // Recursively call playTurn if the slot is already taken
        }
    }
}
