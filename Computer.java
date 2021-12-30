/*****************************************************************************
 *                                                          Terence Ting
 *                                                          December 29, 2021
 *                              Dots and Boxes
 * File Name:   Computer.java
 *
 *****************************************************************************/

import java.io.*;
import java.util.Random;

public class Computer {

    /**
     * Picks a random column index within the range 0 - col
     */
    public static int pickRandColIndex (int col) {
        Random rand = new Random();
        return rand.nextInt(col);
    }

    /**
     * Picks a random row index within the range 0 - row
     */
    public static int pickRandRowIndex (int row) {
        Random rand = new Random();
        return rand.nextInt(row);
    }

    /**
     * Picks a random position on the gameboard that isn't full
     */
    public static String randomCoord (Board gameBoard) {

        // Empty String
        String temp = "";

        // Continues to generate random positions on the gameboard
        while (true) {
            int col = pickRandColIndex(gameBoard.getCol());
            int row = pickRandRowIndex(gameBoard.getCol());

            temp = String.valueOf((char)('A' + col)) + (row + 1);

            // Tests whether or not that position is available
            if (gameBoard.testPosition(temp, true)) {
                break;
            }
        }

        // Returns random available space
        return temp;
    }

    /**
     * Searches the gameboard for a specific character
     */
    public static boolean searchForBox (Board gameBoard, char character) {

        // Loops through the gameBoard
        for (int i = 0; i < gameBoard.board.length; i++) {
            for (int j = 0; j < gameBoard.board[0].length; j++) {
                if ((i % 2 != 0) && (j % 2 != 0)) {

                    // If it finds the character, return true
                    if (gameBoard.board[i][j] == character) {
                        return true;
                    }
                }
            }
        }

        // No character was found
        return false;    
    }

    /**
     * Computer priority logic, the computer will chose to fill boxes with 3
     * walls and will try not to place walls next to boxes with 2 walls.
     */
    public static String priorityFill (Board gameBoard) {

        // If a 3Box is found, fill that one
        if (searchForBox(gameBoard, '3')) {
            // Loops through the gameBoard
            for (int i = 0; i < gameBoard.board.length; i++) {
                for (int j = 0; j < gameBoard.board[0].length; j++) {
                    if ((i % 2 != 0) && (j % 2 != 0)) {
                        // It finds a 3, find the empty wall
                        if (gameBoard.board[i][j] == '3') {
                            // Check North Wall
                            if (gameBoard.board[i - 1][j] == ' ') {
                                return String.valueOf((char)('A' + j)) + i;
                            }
                            // Check West Wall
                            else if (gameBoard.board[i][j - 1] == ' ') {
                                return String.valueOf((char)('A' + j - 1)) + (i + 1);
                            }
                            // Check East Wall
                            else if (gameBoard.board[i][j + 1] == ' ') {
                                return String.valueOf((char)('A' + j + 1)) + (i + 1);
                            }
                            // Check South Wall
                            else if (gameBoard.board[i + 1][j] == ' ') {
                                return String.valueOf((char)('A' + j)) + (i + 2);
                            }
                        }
                    }
                }
            }
        }

        // Chooses a random wall if 3 is not found
        for (int i = 0; i < gameBoard.getOccupancy(); i++) {

            // Creates a new empty coordinate string
            String nextCoord = randomCoord(gameBoard);
            // Converts this string to 2D array indexes
            int col = nextCoord.charAt(0) - (int)'A';
            int row = Integer.parseInt(nextCoord.substring(1)) - 1;

            // Test flags
            boolean up = true;
            boolean left = true;
            boolean right = true;
            boolean down = true;

            // Boolean flags to prevent index out of bounds error
            // Leftmost column
            if (col == 0) {
                left = false;
            }
            // Rightmost column
            if (col == gameBoard.board[0].length - 1) {
                right = false;
            }
            // Highest row
            if (row == 0) {
                up = false;
            }
            // Lowest row
            if (row == gameBoard.board.length - 1) {
                down = false;
            }

            // Check around the randomly chosen coord
            // If there is a row above, check up
            if (up) {
                if (gameBoard.board[row - 1][col] == '2') {
                    continue;
                }
            }
            // If there is a column to the left, check left
            if (left) {
                if (gameBoard.board[row][col - 1] == '2') {
                    continue;
                }
            }
            // If there is a column to the right, check right
            if (right) {
                if (gameBoard.board[row][col + 1] == '2') {
                    continue;
                }
            }
            // If there is a row below, check down
            if (down) {
                if (gameBoard.board[row + 1][col] == '2') {
                    continue;
                }
            }
            // Return this coordinate if a 2 is not detected around it
            return nextCoord;
        }

        // Final option, choose randomly
        return randomCoord(gameBoard);
    }


}
