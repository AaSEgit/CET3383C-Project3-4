import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class Game {

    Player player1;
    Player player2;
    Player computer;

    /*public Game() {
        this.player1 = new Player();
        this.player2 = new Player();
    }*/


    public void rules() {
        System.out.println("\nWinner of the round will be determined as follows:");
        System.out.println("   a. Rock breaks scissors and saw, therefore rock wins over scissors and saw. Rock loses against paper.");
        System.out.println("   b. Scissors cut paper, therefore scissors win over paper. Scissors lose against rock and saw.");
        System.out.println("   c. Paper covers rock, therefore paper wins over rock. Paper loses against scissors and saw.");
        System.out.println("   d. Saw cuts through scissors and paper, therefore saw wins over scissors and paper. Saw loses against rock.");
        System.out.println("   e. If the player and computer make the same selection, there is a tie.\n");
        System.out.println("Winner of the game against the computer is the one who won more rounds (must account for ties).\n");
        System.out.println("Overall human winner is based on the greater number of won games against the computer and the fewest games lost (must account for ties between human players).\n");
    }

    public int get_player_selection(String playerName) {
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

    public String determine_winner(String playerName, int playerSelection, int computerSelection) {
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

    public void updateRoundStatistics(String result1, String result2, int[] winCounts) {
        if (result1.equals(player1.getName() + " wins!")) {
            winCounts[0]++;
            player1.stats.incrementRoundsWon();
            computer.stats.incrementRoundsLost();
        } else if (result1.equals("Computer wins")) {
            winCounts[2]++;
            computer.stats.incrementRoundsWon();
            player1.stats.incrementRoundsLost();
        } else {
            computer.stats.incrementRoundsTied();
            player1.stats.incrementRoundsTied();
        }

        if (result2.equals(player2.getName() + " wins!")) {
            winCounts[1]++;
            player2.stats.incrementRoundsWon();
            computer.stats.incrementRoundsLost();
        } else if (result2.equals("Computer wins")) {
            winCounts[3]++;
            computer.stats.incrementRoundsWon();
            player2.stats.incrementRoundsLost();
        } else {
            computer.stats.incrementRoundsTied();
            player2.stats.incrementRoundsTied();
        }
    }

    public void updateGameStatistics(int[] winCounts) {
        if (winCounts[0] > winCounts[2]) {
            System.out.println(player1.getName()+ " wins the game!");
            player1.stats.incrementGamesWon();
            computer.stats.incrementGamesLost();
        } else if (winCounts[0] == winCounts[2]) {
            System.out.println(player1.getName() + " tied against the computer!");
            player1.stats.incrementGamesTied();
            computer.stats.incrementGamesTied();
        } else {
            System.out.println("The computer wins the game!");
            computer.stats.incrementGamesWon();
            player1.stats.incrementGamesLost();
        }

        System.out.println("-------------------");
        System.out.println(player2.getName() + " vs Computer:");

        if (winCounts[1] > winCounts[3]) {
            System.out.println(player2.getName() + " wins the game!");
            player2.stats.incrementGamesWon();
            computer.stats.incrementGamesLost();
        } else if (winCounts[1] == winCounts[3]) {
            System.out.println(player2.getName() + " tied against the computer!");
            player2.stats.incrementGamesTied();
            computer.stats.incrementGamesTied();
        } else {
            System.out.println("The computer wins the game!");
            computer.stats.incrementGamesWon();
            player2.stats.incrementGamesLost();
        }
    }
    public void playGame(Player player1, Player player2, Player computer) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        this.player1 = player1;
        this.player2 = player2;
        this.computer = computer;

        String player1_name = player1.getName();
        String player2_name = player2.getName();

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

            int player1_selection = get_player_selection(player1_name);
            int player2_selection = get_player_selection(player2_name);

            System.out.println("\n" + player1_name + " chose " + selectionIntToString(player1_selection));
            System.out.println(player2_name + " chose " + selectionIntToString(player2_selection));
            System.out.println("Computer chose " + selectionIntToString(computer_selection) + "\n");

            String result1 = determine_winner(player1_name, player1_selection, computer_selection);
            String result2 = determine_winner(player2_name, player2_selection, computer_selection);

            System.out.println(player1_name + " vs. Computer: " + result1);
            System.out.println(player2_name + " vs. Computer: " + result2 + "\n");

            updateRoundStatistics(result1, result2, winCounts);
        }

        System.out.println("Winners of the game:");
        System.out.println("-------------------");
        System.out.println(player1_name + " vs Computer:");

        updateGameStatistics(winCounts);


        //validateReturnToMenu();
    }


    /*private void announceRoundWinners() {
        System.out.println("\nRound Results:");
        System.out.println(player1.getName() + " wins: " + player1Wins + " rounds");
        System.out.println(player2.getName() + " wins: " + player2Wins + " rounds");
        System.out.println("Ties: " + ties + " rounds");
    }

    private void announceGameWinner() {
        System.out.println("\nGame Results:");

        if (player1Wins == player2Wins) {
            System.out.println("It's a tie! No overall winner.");
        } else if (player1Wins > player2Wins) {
            System.out.println(player1.getName() + " is the overall winner!");
        } else {
            System.out.println(player2.getName() + " is the overall winner!");
        }
    }

    private void displayStatistics() {
        System.out.println("\nStatistics:");
        System.out.println(player1.getName() + " wins: " + player1Wins + " rounds");
        System.out.println(player2.getName() + " wins: " + player2Wins + " rounds");
        System.out.println("Ties: " + ties + " rounds");
    }*/

}
