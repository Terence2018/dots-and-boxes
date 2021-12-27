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
                returned += board[i][j];
            }
            returned += "\n";

        }
        return returned;
    }

}
