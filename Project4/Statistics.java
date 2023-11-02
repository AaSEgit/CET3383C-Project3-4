public class Statistics {
    private int STATS_SIZE = 6;
    private int[] stats = new int[STATS_SIZE];

    // Constructor
    Statistics() {
        for (int i = 0; i < STATS_SIZE; i++) {
            stats[i] = 0;
        }
    }

    // Increments a player stat by 1
    public void updateStats(int statsIndex) {
        stats[statsIndex] = stats[statsIndex] + 1;
    }

    public int getGameWins() {
        return stats[3];
    }

    public int getGameLoses() {
        return stats[4];
    }

    public void displayStats(String playerName) {
        System.out.println(playerName);
        System.out.println("Rounds won:  " + stats[0]);
        System.out.println("Rounds lost: " + stats[1]);
        System.out.println("Rounds tied: " + stats[2]);
        System.out.println("Games won:   " + stats[3]);
        System.out.println("Games lost:  " + stats[4]);
        System.out.println("Games tied:  " + stats[5]);
    }

    public static void determineOverallWinner(Player player1, Player player2) {
        String overallWinner = "";
        int player1Ratio = player1.getGameWins() - player1.getGameLoses();
        int player2Ratio = player2.getGameWins() - player2.getGameLoses();

        if (player1Ratio > player2Ratio)
            overallWinner = player1.getPlayerName();
        else if (player2Ratio > player1Ratio)
            overallWinner = player2.getPlayerName();
        else if (player2Ratio == player1Ratio)
            overallWinner = "It's a tie!";
        
        System.out.println("Overall winner: " + overallWinner);
    }
}
