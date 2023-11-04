public class Player {
    private String name;
    private Statistics playerStats;

    // Constructor
    Player(String playerName) {
        this.name = playerName;
        playerStats = new Statistics();
    }

    // Returns Player's name
    public String getPlayerName() {
        return name;
    }

    // Returns the number of games the Player has won
    public int getGameWins() {
        return playerStats.getGameWins();
    }

    // Returns the number of games the Player has lost
    public int getGameLoses() {
        return playerStats.getGameLoses();
    }

    // Returns object with the Player's game stats
    public Statistics getPlayerStatistics() {
        return playerStats;
    }
}
