package lib.models;

import java.util.ArrayList;

/**
 * Represent beat result
 */
public class MatchResult {

    String score;
    ArrayList<PlayerChoice> players;
    PlayerChoice winner;
    PlayerChoice loser;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public ArrayList<PlayerChoice> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<PlayerChoice> players) {
        this.players = players;
    }

    public PlayerChoice getWinner() {
        return winner;
    }

    public void setWinner(PlayerChoice winner) {
        this.winner = winner;
    }

    public PlayerChoice getLoser() {
        return loser;
    }

    public void setLoser(PlayerChoice loser) {
        this.loser = loser;
    }
}
