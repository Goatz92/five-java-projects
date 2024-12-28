package goatz92.cf7.Project04;

import java.util.Objects;
import java.util.Scanner;

public class Project04Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        //Grid map
        int[][] grid = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        //Printed Grid
        String[][] board = {
                {"-", "-", "-",},
                {"-", "-", "-",},
                {"-", "-", "-",}
        };


        String player = "X";
        while (true) {

            int playerChoice = 0; //Player must choose from 1 to 9
            playerChoice = in.nextInt();
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (playerChoice == grid[i][j]) {
                        board[i][j] = player;
                        if (player.equals("X")) {
                            player = "O";
                            grid[i][j] = 0;
                        } else {
                            player = "X";
                            grid[i][j] = 10;
                        }
                    }
                }
                System.out.println();
            }

            //Prints the current version of the board
            for (int i = 0; i < 3; i++) {
                System.out.print(" | ");
                for (int j = 0; j < 3; j++) {
                    System.out.print(board[i][j] + " | ");
                }
                System.out.println();
                System.out.println("---------------");
            }
            //Check win con
            if (grid[0][0] == 0 && grid[0][1] == 0 && grid[0][2] == 0) {
                System.out.println("Winner");
            }
            if (grid[0][0] == 0 && grid[0][1] == 0 && grid[0][2] == 0) {
                System.out.println("Winner");
            }
        }
    }
}