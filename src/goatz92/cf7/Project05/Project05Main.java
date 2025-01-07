package goatz92.cf7.Project05;

import java.util.Scanner;

public class Project05Main {

    public static void main(String[] args) {

        boolean[][] seatMap = new boolean[30][12];
        char row;
        int columns;


    }




    public static char userChoiceRow (char row) throws Exception {

        try(Scanner in = new Scanner(System.in)) {
            row = in.next().charAt(0);
            if (!Character.isLetter(row)) {
                throw new Exception ("Character can't be a Symbol");
            }
            else if (Character.isWhitespace(row)) {
                throw new Exception ("Character cant be Whitespace");
            }
            else if (Character.isDigit(row)){
                throw new Exception ("Character can't be a digit")
            }
        }
        catch (Exception e) {
            throw e;
        }
        while (row < 'A' )
        return row;
    }

    public static void book(char row, int columns) {


    }

    public static void cancel(char row, int columns) {

    }
}

/*

public static String[] columnInit (String[] column, char c) {
    for (int i = 0; i < column.length; i++) {
        if (c <= 'Z') {
            column[i] = String.valueOf(c);
        } else {
            c = 'A';
        }
        if (i >= 26) {
            column[i] = "" + c + c;
        }
        c++;
    }
    return column;
}
 */

/*
public static int[] rowInit (int[] row) {
    int rowNum = 1;
    for(int i = 0; i < row.length; i++){
        row[i] = rowNum;
        rowNum++;
    }
    return row;
}
 */