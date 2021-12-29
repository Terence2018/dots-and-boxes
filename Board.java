/*****************************************************************************
 *                                                          Terence Ting
 *                                                          December 26, 2021
 *                              Dots and Boxes
 * File Name:   Board.java
 *
 *****************************************************************************/

import java.io.*;

public class Board {

    private int col;
    private int row;
    private char[][] board;
    private int occupancy;

    private static boolean debug;

    public Board (int colInput, int rowInput) {

        // Number of rows and columns
        this.col = colInput * 2 + 1;
        this.row = rowInput * 2 + 1;

        // 2D Character Array
        this.board = new char[row][col];

        // Adding Dots and filling Spaces
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if ((i%2==0) && (j%2==0)) {
                    board[i][j] = '•';
                }
                else {
                    board[i][j] = ' ';
                }
            }
        }

        // Occupancy of the gameboard is how many walls can be drawn
        this.occupancy = (colInput * (rowInput + 1)) + 
            (rowInput * (colInput + 1));
    }

    public void clearBoard () {

        // Adding Dots and filling Spaces
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if ((i%2==0) && (j%2==0)) {
                    board[i][j] = '•';
                }
                else {
                    board[i][j] = ' ';
                }
            }
        }
    }

    public int getOccupancy () {
        return occupancy;
    }

    public static void debugOff () {
        debug = false;
    }

    public static void debugOn () {
        debug = true;
    }

    public String toString () {

        String returned = "    ";

        // Starting character
        char colLabel = 'A';
        int rowLabel = 1;

        // Print Column Labels
        for (int i = 0; i < col; i++) {
            returned += colLabel++ + " ";
        }

        returned+= "\n";

        // Print dots and spaces
        for (int i = 0; i < row; i++) {

            // Print Row Labels
            if (rowLabel < 10) {
                returned += " " + rowLabel++ + " ";
            }
            else {
                returned += " " + rowLabel++;
            }

            for (int j = 0; j < col; j++) {
                returned += " ";

                if (debug) {
                    // Print all characters
                    if (board[i][j] != '0') {
                        returned += board[i][j];
                    }
                    // Don't print 0s
                    else {
                        returned += ' ';
                    }
                }
                else {
                    // Don't print wallCounts
                    if ((board[i][j] >= '0') && (board[i][j] < '4')) {
                        returned += ' ';
                    }
                    // Print dots and walls
                    else {
                        returned += board[i][j];
                    }
                }
            }
            returned += "\n";

        }
        return returned;
    }

    public boolean testPosition (String input) {

        // Gets the Character from the string
        char inputChar = input.charAt(0);
        // Gets the int from the string
        int inputInt;
        try {
            inputInt = Integer.parseInt(input.substring(1));
        }
        catch (Exception e) {
            System.out.print("Invalid Format: ");
            return false;
        }

        // Converts the input to array indexes
        int colNum = inputChar - (int)'A';
        int rowNum = inputInt - 1;

        // Check if the indexes are within the table confines
        if ((colNum >= col) || (colNum < 0)) {
            System.out.print("Column " + inputChar + ": ");
            return false;
        }
        if ((rowNum >= row) || (rowNum < 0)) {
            System.out.print("Row " + inputInt + ": ");
            return false;
        }

        // If on an even column number
        if (colNum % 2 == 0) {
            // If row is even, position not possible
            if (rowNum % 2 == 0) {
                System.out.print("Invalid Position: ");
                return false;
            }
            // If row is odd
            else {
                // return true if empty
                if (board[rowNum][colNum] == ' ') {
                    return true;
                }
                // position is full
                else {
                    System.out.print("Position Filled: ");
                    return false;
                }
            }
        }
        // If on an odd column number
        else {
            // If row is even
            if (rowNum % 2 == 0) {
                // return true if empty
                if (board[rowNum][colNum] == ' ') {
                    return true;
                }
                // position is full
                else {
                    System.out.print("Position Filled: ");
                    return false;
                }
            }
            // If row is odd, position not possible
            else {
                System.out.print("Invalid Position: ");
                return false;
            }
        }
    }

    public void fillPosition (String input) {

        // Check if position is available
        if (testPosition(input) && (occupancy > 0)) {
            // Gets input from String
            char inputChar = input.charAt(0);
            int inputInt = Integer.parseInt(input.substring(1));
            // Converts the input to array indexes
            int colIndex = inputChar - (int)'A';
            int rowIndex = inputInt - 1;

            // If colIndex is even, then fill with vertical wall
            if (colIndex % 2 == 0) {
                board[rowIndex][colIndex] = (char)9553;
                occupancy--;
            }
            // If colIndex is odd, then fill with horizontal wall
            else {
                board[rowIndex][colIndex] = (char)9552;
                occupancy--;
            }

        }
        // Position is not available
        else {
            System.out.println("Not Available");
        }
    }

    public boolean setBoxValue (boolean player1) {

        boolean checker = false;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if ((i % 2 != 0) && (j % 2 != 0)) {

                    int wallCount = 0;

                    if (board[i - 1][j] != ' ') {
                        wallCount++;
                    }
                    if (board[i + 1][j] != ' ') {
                        wallCount++;
                    }
                    if (board[i][j - 1] != ' ') {
                        wallCount++;
                    }
                    if (board[i][j + 1] != ' ') {
                        wallCount++;
                    }

                    if (wallCount == 4) {
                        if ((board[i][j] != 'X') && (board[i][j] != 'O')) {
                            if (player1) {
                                board[i][j] = 'X';
                            }
                            else {
                                board[i][j] = 'O';
                            }
                            checker = true;
                        }

                    }
                    else {
                        board[i][j] = (char)('0' + wallCount);
                    }
                }
            }
        }
        return checker;

    }

    public int countScore (boolean player1) {
   
        int counter = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if ((i % 2 != 0) && (j % 2 != 0)) {
                    
                    if (player1) {
                        if (board[i][j] == 'X') {
                            counter++;
                        }
                    }
                    else {
                        if (board[i][j] == 'O') {
                            counter++;
                        }
                    }
                }
            }
        }
        return counter;
    }
}
