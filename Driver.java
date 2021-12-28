/*****************************************************************************
 *                                                          Terence Ting
 *                                                          December 26, 2021
 *                              Dots and Boxes
 * File Name:   Driver.java
 *
 *****************************************************************************/

import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

public class Driver {

    public static void main (String[] args) {

        // Title
        System.out.println("\n██████╗  ██████╗ ████████╗███████╗     █████╗ ███╗   ██╗██████╗     ██████╗  ██████╗ ██╗  ██╗███████╗███████╗");
        System.out.println("██╔══██╗██╔═══██╗╚══██╔══╝██╔════╝    ██╔══██╗████╗  ██║██╔══██╗    ██╔══██╗██╔═══██╗╚██╗██╔╝██╔════╝██╔════╝");
        System.out.println("██║  ██║██║   ██║   ██║   ███████╗    ███████║██╔██╗ ██║██║  ██║    ██████╔╝██║   ██║ ╚███╔╝ █████╗  ███████╗");
        System.out.println("██║  ██║██║   ██║   ██║   ╚════██║    ██╔══██║██║╚██╗██║██║  ██║    ██╔══██╗██║   ██║ ██╔██╗ ██╔══╝  ╚════██║");
        System.out.println("██████╔╝╚██████╔╝   ██║   ███████║    ██║  ██║██║ ╚████║██████╔╝    ██████╔╝╚██████╔╝██╔╝ ██╗███████╗███████║");
        System.out.println("╚═════╝  ╚═════╝    ╚═╝   ╚══════╝    ╚═╝  ╚═╝╚═╝  ╚═══╝╚═════╝     ╚═════╝  ╚═════╝ ╚═╝  ╚═╝╚══════╝╚══════╝\n");
        // Subtitles 
        System.out.println("By Terence Ting");
        System.out.println("Font courtesy of Patorjk.com");
        System.out.println("Ctrl+D at any point to manually end the game\n");

        Scanner userInput = new Scanner(System.in);
        int columns = 0;
        int rows = 0;

        while (true) {
            // Prompts user for number of columns
            System.out.print("Number of columns: ");
            try {
                if (userInput.hasNextLine()) {
                    columns = userInput.nextInt();
                }
                // EOF detected
                else {
                    System.out.println("EOF Detected");
                    System.out.println("\nGame Completed\n");
                    System.exit(0);
                }
                
                // Check if column number is smaller than 1
                if (columns < 1) {
                    System.out.println("Must Have at Least 1 Column");
                    userInput = new Scanner(System.in);
                    continue;
                }
                // Check if column number is greater than 10
                else if (columns >= 10) {
                    System.out.println("You might be here for a while with " + columns + " Columns (Maximum 9 Columns)");
                    userInput = new Scanner(System.in);
                    continue;
                }
                else {
                    break;
                }
            }
            // Catch any non integer input
            catch (Exception e) {
                System.out.println("ERROR: Not Integer Input");
                userInput = new Scanner(System.in);
            }

        }

        // Separator line
        System.out.println();
        userInput = new Scanner(System.in);

        while (true) {
            // Prompts user for number of rows
            System.out.print("Number of rows: ");
            try {
                if (userInput.hasNextLine()) {
                rows = userInput.nextInt();
                }
                // Checks for EOF
                else {
                    System.out.println("EOF Detected");
                    System.out.println("\nGame Completed\n");
                    System.exit(0);
                }
            
                // checks if row number is smaller than 1
                if (rows < 1) {
                    System.out.println("Must Have at Least 1 Row");
                    userInput = new Scanner(System.in);
                    continue;
                }
                // Checks if row number is greater than 10
                else if (rows >= 10) {
                    System.out.println("You might be here for a while with " + rows + " Rows (Maximum 9 Rows)");
                    userInput = new Scanner(System.in);
                    continue;
                }
                else {
                    break;
                }
            }
            // Catches non integer input
            catch (Exception e) {
                System.out.println("ERROR: Not Integer Input");
                userInput = new Scanner(System.in);
            }
        }

        // creates the gameboard
        Board gameBoard = new Board(columns, rows);

        // Prints the empty gameboard
        System.out.println("\n" + gameBoard.toString());

        // Loop to add walls to the board
        while (true) {
            System.out.print("Fill a position: ");

            userInput = new Scanner(System.in);
            String position;

            // Checks for EOF
            if (userInput.hasNextLine()) {
                position = userInput.nextLine();
            }
            else {
                System.out.println("EOF Detected");
                break;
            }

            // Fills position if available
            if (gameBoard.testPosition(position)) {
                gameBoard.fillPosition(position);
                // Only prints the board if changes have been made
                System.out.println(gameBoard.toString());
            }
            else {
                System.out.println("Not Avaiable");
            }

            // If game board no longer has any spaces left, end
            if (gameBoard.getOccupancy() == 0) {
                break;
            }
        }

        // Game completed message
        System.out.println("\nGame Completed\n");


    }

}
