/*
 * CET3383C-Project3-4
 * Software Engineering 1
 * 
 * Project 4: Rock, Paper, Scissors, Saw Game & Software Design Specification Document
 *            (Object Oriented Design)
 * 
 * Due Date: November 5, 2023
 * 
 * Programmers: Ava Adams, Kenny Garcia, Juan Leon Perez
 * 
 * Description:   This project simulates a game of Rock, Paper, Scissors, Saw using
 *                an object-oriented programming approach.
 *                The game output will be used to complete Sections 3-5 of the SDS
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class GameDriver {

    // Get names for two players, ensuring valid name lengths and that they are different
    public static String[] getPlayerName() {
        Scanner scanner = new Scanner(System.in);
        String[] names = new String[2];
        boolean valid = false;

        while (!valid) {
            System.out.print("What is the name of the first player? ");
            String name = scanner.next();
            if (name.length() < 5) {
                System.out.println("Error: Name must be longer than 5 characters. Enter the name again.");
            } else if (name.length() > 20) {
                System.out.println("Error: Name must be shorter than 20 characters. Enter the name again.");
            } else {
                valid = true;
                names[0] = name;
            }
        }

        valid = false;

        while (!valid) {
            System.out.print("What is the name of the second player? ");
            String name = scanner.next();
            if (name.length() < 5) {
                System.out.println("Error: Name must be longer than 5 characters. Enter the name again.");
            } else if (name.length() > 20) {
                System.out.println("Error: Name must be shorter than 20 characters. Enter the name again.");
            } else if (names[0].equals(name)) {
                System.out.println("Error: Second player name must be different than the first player. Please enter a name again.");
            } else {
                valid = true;
                names[1] = name;
            }
        }

        return names;
    }

    //Allow the user to return to the main menu
    public static int validateReturnToMenu() {
        Scanner scanner = new Scanner(System.in);


        while(true) {
            try {
                System.out.print("\nEnter 1 to return to the Menu: ");
                int userInput = scanner.nextInt();

                if (userInput == 1) {
                    return 1;
                } else {
                    System.out.println("Invalid input. Please enter 1.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter 1.");
                scanner.next();// Clear the invalid input from the scanner
            }
        }
    }

    // Display the menu and return the user's choice
    private static int displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("---------------------------");
            System.out.println("1. Play game");
            System.out.println("2. Show game rules");
            System.out.println("3. Show statistics");
            System.out.println("4. Exit");

            try {
                System.out.print("Type choice and press return: ");
                choice = scanner.nextInt();

                if (choice >= 1 && choice <= 4) {
                    return choice;
                } else {
                    System.out.println("Error: Invalid choice. Please enter a valid menu option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid choice. Please enter a valid menu option.");
                scanner.next(); // Clear the invalid input from the scanner
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("---------------------------");
        System.out.println("Rock, Paper, Scissors, Saw");
        System.out.println("---------------------------\n");

        // Get player names
        String[] names = getPlayerName();
        //Initialize players
        Player p1 = new Player(names[0]);
        Player p2 = new Player(names[1]);
        Player computer = new Player("Computer");
        //Create array of players and new Game
        Player[] players = { p1, p2, computer };
        Game game = new Game();

        int selection = displayMenu();

        while (selection != 4) {
            if (selection == 1) {
                game.playGame(players);
            } else if (selection == 2) {
                game.displayRules();
            } else if (selection == 3) {
                System.out.println("\nStatistics");
                System.out.print("---------------------------");
                p1.getPlayerStatistics().displayStats(p1.getPlayerName());
                p2.getPlayerStatistics().displayStats(p2.getPlayerName());
                Statistics.determineOverallWinner(p1, p2);
            }
            validateReturnToMenu();
            selection = displayMenu();
        }

        System.out.println("Goodbye");
    }
}
