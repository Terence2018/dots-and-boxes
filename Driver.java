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

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";

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
                System.out.println("    [2] 2 Player");
                if (Board.getDebug()) {
                    System.out.println("    [3] Debug");
                    System.out.println("    [4] For Fun");
                }
                System.out.print("\nSelect a Gamemode: ");

                if (userInput.hasNextLine()) {
                    gamemode = userInput.nextInt();
                    if (Board.getDebug()) {
                        if ((gamemode > 4) || (gamemode < 1)) {
                            System.out.println("Please select one of the options\n");
                            continue;
                        }
                        else {
                            break;
                        }
                    }
                    else {
                        if ((gamemode > 2) || (gamemode < 1)) {
                            System.out.println("Please select one of the options\n");
                            continue;
                        }
                        else {
                            break;
                        }
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

        String p1 = "";
        String p2 = "";
        int order = 0;
        int difficulty = 0;

        // If 1 player
        // Choose whether to go first or second
        if (gamemode == 1) {
            while (true) { 
                userInput = new Scanner(System.in);
                System.out.println("    [1] First");
                System.out.println("    [2] Second\n");
                System.out.print("Go First or Second: ");
                try {
                    if (userInput.hasNextLine()) {
                        order = userInput.nextInt();

                        if ((order > 2) || (order < 1)) {
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

            // Chose a Difficulty
            while (true) {
                userInput = new Scanner(System.in);
                System.out.println("    [1] Easy");
                System.out.println("    [2] Normal");
                System.out.println("    [3] Hard\n");
                System.out.print("Choose a Difficulty: ");
                try {
                    if (userInput.hasNextLine()) {
                        difficulty = userInput.nextInt();

                        if ((difficulty > 3) || (difficulty < 1)) {
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

            // Then enter player name
            userInput = new Scanner(System.in);
            System.out.print("Enter Player Name: ");
            if (userInput.hasNextLine()) {
                if (order == 1) {
                    p1 = userInput.nextLine();
                    p2 = "Computer";
                }
                else {
                    p2 = userInput.nextLine();
                    p1 = "Computer";
                }
            }
            else {
                endgame();
            }

            System.out.println();

        }

        // If 2 players, enter their names
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

        if (gamemode != 4) {
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
        }

        // 30x30 gameboard for computers
        if (gamemode == 4) {
            columns = 50;
            rows = 50;
        }

        // creates the gameboard
        Board gameBoard = new Board(columns, rows);

        // Prints the empty gameboard
        if (gamemode != 4) {
            System.out.println("\n" + gameBoard.toString());
        }

        int twoPlayerCounter = 0;

        // DEBUG STUFF

        if (gamemode == 3) {











            System.out.println();
            System.exit(0);
        }

        // DEBUG STUFF

        // Two player turn by turn
        if (gamemode == 2) {
            // Loop to add walls to the board
            while (true) {

                if (twoPlayerCounter % 2 == 0) {
                    System.out.println(p1 + "'s turn");
                }
                else {
                    System.out.println(p2 + "'s turn");
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
                if (gameBoard.testPosition(position, false)) {

                    gameBoard.fillPosition(position);

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
        }

        // single player turn by turn
        if (gamemode == 1) {
            while (true) {

                // Print player's turn
                if (twoPlayerCounter % 2 == 0) {
                    System.out.println(p1 + "'s turn");
                }
                else {
                    System.out.println(p2 + "'s turn");
                }

                // Player's turn
                if (((order == 1) && (twoPlayerCounter % 2 == 0)) ||
                        ((order == 2) && (twoPlayerCounter % 2 != 0))) {
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
                    if (gameBoard.testPosition(position, false)) {
                        gameBoard.fillPosition(position);

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

                        System.out.println("\n" + gameBoard.toString());
                    }
                    else {
                        System.out.println("Not Available");
                    }


                        }
                // Computer's Turn
                else {
                    // Easiest computer, computer just picks randomly
                    if (difficulty == 1) {
                        gameBoard.fillPosition(Computer.randomCoord(gameBoard));

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

                        System.out.println("\n" + gameBoard.toString());
                    }
                    // Normal computer, prioritizes 3Boxes, tries not to place
                    // walls next to 2Boxes, else random
                    else if (difficulty == 2) {
                        gameBoard.fillPosition(Computer.priorityFill(gameBoard));

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

                        System.out.println("\n" + gameBoard.toString());
                    }
                    // Hard computer, computer tries to optimize choices
                    // TODO
                    else if (difficulty == 3) {
                        System.out.println("Difficulty Not Yet Implemented");
                        break;
                    }
                }

                // End the game when the board is full
                if (gameBoard.getOccupancy() == 0) {
                    break;
                }


            }
        }

        // for fun
        if (gamemode == 4) {
            System.out.println("AI BATTLE");
            p1 = "CPU1";
            p2 = "CPU2";

            while (true) {
                if (twoPlayerCounter % 2 == 0) {
                    // Priority
                    gameBoard.fillPosition(Computer.priorityFill(gameBoard));
                    // Random
                    //gameBoard.fillPosition(Computer.randomCoord(gameBoard));

                    if (!gameBoard.setBoxValue(true)) {
                        twoPlayerCounter++;
                    }
                }
                else {
                    // Priority
                    gameBoard.fillPosition(Computer.priorityFill(gameBoard));
                    // Random
                    //gameBoard.fillPosition(Computer.randomCoord(gameBoard));

                    if (!gameBoard.setBoxValue(false)) {
                        twoPlayerCounter++;
                    }
                }

                if (gameBoard.getOccupancy() == 0) {
                    break;
                }

            }

            gameBoard.printNoBorders();
            System.out.println();

        }

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

        // Game completed message
        System.out.println("Game Completed\n");


    }

}
