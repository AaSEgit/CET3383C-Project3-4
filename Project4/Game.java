import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class Game {
    Player player1;
    Player player2;
    Player computer;

    // Display game rules
    public void displayRules() {
        System.out.println("\nWinner of the round will be determined as follows:");
        System.out.println("   a. Rock breaks scissors and saw, therefore rock wins over scissors and saw. Rock loses against paper.");
        System.out.println("   b. Scissors cut paper, therefore scissors win over paper. Scissors lose against rock and saw.");
        System.out.println("   c. Paper covers rock, therefore paper wins over rock. Paper loses against scissors and saw.");
        System.out.println("   d. Saw cuts through scissors and paper, therefore saw wins over scissors and paper. Saw loses against rock.");
        System.out.println("   e. If the player and computer make the same selection, there is a tie.\n");
        System.out.println("Winner of the game against the computer is the one who won more rounds (must account for ties).\n");
        System.out.println("Overall human winner is based on the greater number of won games against the computer and the fewest games lost (must account for ties between human players).\n");
    }

    // Get player's selection
    public int getPlayerSelection(String playerName) {
        Scanner scanner = new Scanner(System.in);
        int selection = 0;

        while (true) {
            System.out.println(playerName + ", please choose:");
            System.out.println("1. Rock");
            System.out.println("2. Paper");
            System.out.println("3. Scissors");
            System.out.println("4. Saw");

            try {
                System.out.print("Enter the number of your choice: ");
                selection = scanner.nextInt();

                if (selection >= 1 && selection <= 4) {
                    return selection;
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Clear the invalid input from the scanner
            }
        }
    }

    // Map numeric selection to string
    public String selectionIntToString(int selection) {
        switch (selection) {
            case 1:
                return "rock";
            case 2:
                return "paper";
            case 3:
                return "scissors";
            case 4:
                return "saw";
            default:
                return "invalid";
        }
    }

    // Determine the winner of a round
    public String determineWinner(String playerName, int playerSelection, int computerSelection) {
        if (playerSelection == computerSelection) {
            return "It's a tie!";
        }

        if ((playerSelection == 1 && (computerSelection == 3 || computerSelection == 4)) ||
                (playerSelection == 2 && (computerSelection == 1)) ||
                (playerSelection == 3 && (computerSelection == 2)) ||
                (playerSelection == 4 && (computerSelection == 2 || computerSelection == 3))) {
            return playerName + " wins!";
        } else {
            return "Computer wins";
        }
    }

    // Update round statistics
    public void updateRoundStatistics(String result1, String result2, int[] winCounts) {
        if (result1.equals(player1.getPlayerName() + " wins!")) {
            winCounts[0]++;
            player1.getPlayerStatistics().updateStats(0);
            computer.getPlayerStatistics().updateStats(1);
        } else if (result1.equals("Computer wins")) {
            winCounts[2]++;
            computer.getPlayerStatistics().updateStats(0);
            player1.getPlayerStatistics().updateStats(1);
        } else {
            computer.getPlayerStatistics().updateStats(2);
            player1.getPlayerStatistics().updateStats(2);
        }

        if (result2.equals(player2.getPlayerName() + " wins!")) {
            winCounts[1]++;
            player2.getPlayerStatistics().updateStats(0);
            computer.getPlayerStatistics().updateStats(1);
        } else if (result2.equals("Computer wins")) {
            winCounts[3]++;
            computer.getPlayerStatistics().updateStats(0);
            player2.getPlayerStatistics().updateStats(1);
        } else {
            computer.getPlayerStatistics().updateStats(2);
            player2.getPlayerStatistics().updateStats(2);
        }
    }

    // Update game statistics
    public void updateGameStatistics(int[] winCounts) {
        if (winCounts[0] > winCounts[2]) {
            System.out.println(player1.getPlayerName() + " wins the game!");
            player1.getPlayerStatistics().updateStats(3);
            computer.getPlayerStatistics().updateStats(4);
        } else if (winCounts[0] == winCounts[2]) {
            System.out.println(player1.getPlayerName() + " tied against the computer!");
            player1.getPlayerStatistics().updateStats(5);
            computer.getPlayerStatistics().updateStats(5);
        } else {
            System.out.println("The computer wins the game!");
            player1.getPlayerStatistics().updateStats(4);
            computer.getPlayerStatistics().updateStats(3);
        }

        System.out.println("-------------------");
        System.out.println(player2.getPlayerName() + " vs Computer:");

        if (winCounts[1] > winCounts[3]) {
            System.out.println(player2.getPlayerName() + " wins the game!");
            player2.getPlayerStatistics().updateStats(3);
            computer.getPlayerStatistics().updateStats(4);
        } else if (winCounts[1] == winCounts[3]) {
            System.out.println(player2.getPlayerName() + " tied against the computer!");
            player2.getPlayerStatistics().updateStats(5);
            computer.getPlayerStatistics().updateStats(5);
        } else {
            System.out.println("The computer wins the game!");
            player2.getPlayerStatistics().updateStats(4);
            computer.getPlayerStatistics().updateStats(3);
        }
    }

    // Main function to play the game
    public void playGame(Player[] players) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        this.player1 = players[0];
        this.player2 = players[1];
        this.computer = players[2];

        String player1_name = player1.getPlayerName();
        String player2_name = player2.getPlayerName();

        int[] winCounts = new int[4];

        /*
        winCounts[0] = player1_wincounts
        winCounts[1] = player2_wincounts
        winCounts[2] = computerp1_wincounts
        winCounts[3] = computerp2_wincounts
        */

        for (int round = 1; round <= 3; round++) {
            System.out.println("\nRound " + round + ":");

            int computer_selection = random.nextInt(4) + 1;

            int player1_selection = getPlayerSelection(player1_name);
            int player2_selection = getPlayerSelection(player2_name);

            System.out.println("\n" + player1_name + " chose " + selectionIntToString(player1_selection));
            System.out.println(player2_name + " chose " + selectionIntToString(player2_selection));
            System.out.println("Computer chose " + selectionIntToString(computer_selection) + "\n");

            String result1 = determineWinner(player1_name, player1_selection, computer_selection);
            String result2 = determineWinner(player2_name, player2_selection, computer_selection);

            System.out.println(player1_name + " vs. Computer: " + result1);
            System.out.println(player2_name + " vs. Computer: " + result2 + "\n");

            updateRoundStatistics(result1, result2, winCounts);
        }

        System.out.println("Winners of the game:");
        System.out.println("-------------------");
        System.out.println(player1_name + " vs Computer:");

        updateGameStatistics(winCounts);
    }
}
