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
        System.out.println("By Terence Ting");
        System.out.println("Font courtesy of Patorjk.com\n");

        Scanner userInput = new Scanner(System.in);
        int columns = 0;
        int rows = 0;

        while (true) {
            try {
                // Prompts user for number of columns
                System.out.print("Number of columns: ");
                columns = userInput.nextInt();

                break;
            }
            catch (Exception e) {
                System.out.println("ERROR: Not Integer Input");
                userInput = new Scanner(System.in);
            }

        }

        userInput = new Scanner(System.in);

        while (true) {
            try {
                // Prompts user for number of rows
                System.out.print("Number of rows: ");
                rows = userInput.nextInt();

                break;
            }
            catch (Exception e) {
                System.out.println("ERROR: Not Integer Input");
                userInput = new Scanner(System.in);
            }
        }

        Board gameBoard = new Board(columns, rows);

        System.out.println("\n" + gameBoard.toString());

        System.out.println("\n" + gameBoard.toString());

    }

}
