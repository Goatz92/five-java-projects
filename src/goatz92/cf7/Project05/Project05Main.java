package goatz92.cf7.Project05;

/**
 * Books and Cancels seats within a Theater
 */
public class Project05Main {

    static boolean[][] isSeatAvailable = new boolean[30][12];

    public static void main(String[] args) {

        /*
        Ideally the class name would be something like "TheaterSeatManager"
        but for this example we keep the name for continuity.
        Also Instantiating the same Class could be a mistake,
        But it is done for demonstrating how a potential future project
        would function.
         */

        Project05Main theater = new Project05Main();

        Project05Main.book('A' , 1);    //Books the Seat
        Project05Main.book('A' , 1);    //Try to book the same seat

        Project05Main.cancel('A' , 1);  //Cancel the Seat
        Project05Main.cancel('A' , 1);  //Try to cancel the same seat
    }

    /**
     * Checks if the seat chosen is a valid seat within the theater
     *
     * @param column The column of the seat
     * @param row    The row of the seat
     * @return If seat is valid or not.
     */
    public static boolean isValidSeat(char column, int row) {
        return column >= 'A' && column <= 'L' && row >= 1 && row <= 30;
    }

    public static void book(char column, int row) {


        if (isValidSeat(column, row)) {
            int columnIndex = column - 'A'; //Column converted into an index (0-11)
            int rowIndex = row - 1;         //Row converted into an index (0-29)

            if (!isSeatAvailable[rowIndex][columnIndex]) {
                isSeatAvailable[rowIndex][columnIndex] = true;
                System.out.println("Seat: " + column + row + " was booked Successfully");
            } else {
                System.out.println("Seat: " + column + row + " is already booked");
            }
        }
        System.out.println("Invalid Seat. Please make a valid Choice");
    }

    public static void cancel(char column, int row) {
        if (isValidSeat(column, row)) {
            int columnIndex = column - 'A'; //Column converted into an index (0-11)
            int rowIndex = row - 1;         //Row converted into an index (0-29)

            if (!isSeatAvailable[rowIndex][columnIndex]) {
                isSeatAvailable[rowIndex][columnIndex] = false;
                System.out.println("Seat: " + column + row + " was booked Canceled");
            } else {
                System.out.println("Seat: " + column + row + " is not booked!");
            }
        }
        System.out.println("Invalid Seat. Please make a valid Choice");
    }
}

/*

Attempt to create this in a different way using a String array for columns
And an int array for rows

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

public static int[] rowInit (int[] row) {
    int rowNum = 1;
    for(int i = 0; i < row.length; i++){
        row[i] = rowNum;
        rowNum++;
    }
    return row;
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
                throw new Exception ("Character can't be a digit");
            }
        }
        catch (Exception e) {
            throw e;
        }
 */