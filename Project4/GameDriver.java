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

    public static String[] getPlayerName() {
        Scanner scanner = new Scanner(System.in);
        String[] names = new String[2];
        boolean valid = false;
        while (!valid) {
            System.out.print("What is the name of the first player?");
            String name = scanner.nextLine();
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
            System.out.print("What is the name of the second player?");
            String name = scanner.nextLine();
            if (name.length() < 5) {
                System.out.println("Error: Name must be longer than 5 characters. Enter the name again.");
            } else if (name.length() > 20) {
                System.out.println("Error: Name must be shorter than 20 characters. Enter the name again.");
            } else if(names[0] == name) {
                System.out.println("Error: Second player name must be different than the first player. Please enter name again.");
            } else {
                valid = true;
                names[1] = name;
            }
        }

        return names;
    }
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
                    System.out.println("Invalid input. Please enter a number between 1 and 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Clear the invalid input from the scanner
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("---------------------------");
        System.out.println("Rock, Paper, Scissors, Saw");
        System.out.println("---------------------------\n");

        String[] names = getPlayerName();
        Player p1 = new Player(names[0]);
        Player p2 = new Player(names[1]);
        Player computer = new Player("Computer");
        Game game = new Game();

        int selection = displayMenu();

        while(selection != 4) {
            if (selection == 1) {
                game.playGame(p1, p2, computer);
            }
            else if(selection == 2){
                game.rules();
            }
            else if(selection == 3) {
                //Statistics.displayStatistics();
            }
            selection = displayMenu();
        }

        System.out.println("Goodbye");
    }
}
