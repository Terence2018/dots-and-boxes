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

    public static void endgame () {
        System.out.println("EOF Detected");
        System.out.println("\nGame Completed\n");
        System.exit(0);
    }

    public static void main (String[] args) {

        Board.debugOff();

        for (int index = 0; index < args.length; ++index) {
            if (args[index].equals("-x")) {
                Board.debugOn();
                System.out.println("[DEBUG ON]");
            }
        }

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
        int gamemode = 0;

        // Gamemode prompts
        while (true) {
            userInput = new Scanner(System.in);
            try {
                System.out.println("    [1] SinglePlayer");
                System.out.println("    [2] 2 Player\n");
                System.out.print("Select a Gamemode: ");

                if (userInput.hasNextLine()) {
                    gamemode = userInput.nextInt();

                    if ((gamemode > 2) || (gamemode < 1)) {
                        System.out.println("Please select one of the options\n");
                        continue;
                    }
                    else {
                        break;
                    }
                }

                else {
                    endgame();
                }
            }
            catch (Exception e) {
                System.out.println("ERROR: Not Integer Input\n");
                continue;
            }
        }

        System.out.println();

        // If 2 players, enter their names
        String p1 = "";
        String p2 = "";
        if (gamemode == 2) {
            userInput = new Scanner(System.in);
            System.out.print("Enter Player 1: ");
            if (userInput.hasNextLine()) {
                p1 = userInput.nextLine();
            }
            else {
                endgame();
            }
            System.out.print("Enter Player 2: ");
            if (userInput.hasNextLine()) {
                p2 = userInput.nextLine();
            }
            else {
                endgame();
            }
            System.out.println();
        }
        
        int columns = 0;
        int rows = 0;

        while (true) {
            userInput = new Scanner(System.in);
            // Prompts user for number of columns
            System.out.print("Number of columns: ");
            try {
                if (userInput.hasNextLine()) {
                    columns = userInput.nextInt();
                }
                // EOF detected
                else {
                    endgame();
                }
                
                // Check if column number is smaller than 1
                if (columns < 1) {
                    System.out.println("Must Have at Least 1 Column");
                    continue;
                }
                // Check if column number is greater than 10
                else if (columns >= 10) {
                    System.out.println("You might be here for a while with " + columns + " Columns (Maximum 9 Columns)");
                    continue;
                }
                else {
                    break;
                }
            }
            // Catch any non integer input
            catch (Exception e) {
                System.out.println("ERROR: Not Integer Input");
            }

        }

        while (true) {
            userInput = new Scanner(System.in);
            // Prompts user for number of rows
            System.out.print("Number of rows: ");
            try {
                if (userInput.hasNextLine()) {
                rows = userInput.nextInt();
                }
                // Checks for EOF
                else {
                    endgame(); 
                }
            
                // checks if row number is smaller than 1
                if (rows < 1) {
                    System.out.println("Must Have at Least 1 Row");
                    continue;
                }
                // Checks if row number is greater than 10
                else if (rows >= 10) {
                    System.out.println("You might be here for a while with " + rows + " Rows (Maximum 9 Rows)");
                    continue;
                }
                else {
                    break;
                }
            }
            // Catches non integer input
            catch (Exception e) {
                System.out.println("ERROR: Not Integer Input");
            }
        }

        // creates the gameboard
        Board gameBoard = new Board(columns, rows);

        // Prints the empty gameboard
        System.out.println("\n" + gameBoard.toString());

        int twoPlayerCounter = 0;

        // Loop to add walls to the board
        while (true) {

            if (gamemode == 2) {
                if (twoPlayerCounter % 2 == 0) {
                    System.out.println(p1 +"'s turn");
                }
                else {
                    System.out.println(p2 +"'s turn");
                }
            }

            System.out.print("Enter a position: ");

            userInput = new Scanner(System.in);
            String position = "";

            // Checks for EOF
            if (userInput.hasNextLine()) {
                position = userInput.nextLine();
            }
            else {
                endgame();
            }

            // Fills position if available
            if (gameBoard.testPosition(position)) {

                gameBoard.fillPosition(position);

                if (gamemode == 2) {
                    if (twoPlayerCounter % 2 == 0) {
                        if (!gameBoard.setBoxValue(true)) {
                            twoPlayerCounter++;
                        }
                    }
                    else {
                        if (!gameBoard.setBoxValue(false)) {
                            twoPlayerCounter++;
                        }
                    }
                }
                else {
                    gameBoard.setBoxValue(true);
                }

                // Only prints the board if changes have been made
                System.out.println("\n" + gameBoard.toString());
            }
            else {
                System.out.println("Not Avaiable");
            }

            // If game board no longer has any spaces left, end
            if (gameBoard.getOccupancy() == 0) {
                break;
            }
        }

        if (gamemode == 2) {
            int score1 = gameBoard.countScore(true);
            int score2 = gameBoard.countScore(false);
            System.out.println(p1 + "'s final score is: " + score1);
            System.out.println(p2 + "'s final score is: " + score2);
            if (score1 > score2) {
                System.out.println(p1 + " wins!");
            }
            else if (score2 > score1) {
                System.out.println(p2 + " wins!");
            }
            else {
                System.out.println("A tie!");
            }
        }

        // Game completed message
        System.out.println("Game Completed\n");


    }

}
