package ui;

import lib.models.MatchResult;
import lib.models.PlayerChoice;

import java.util.ArrayList;

public class ConsoleView implements IView {

    IPresenter presenter;

    public ConsoleView() {
        this.presenter = new Presenter(this);
    }

    public void printResults(ArrayList<MatchResult> results) {
        for (MatchResult beatResult : results) {
            if (beatResult.getScore().equals("win")) {
                PlayerChoice winner = beatResult.getWinner();
                PlayerChoice loser = beatResult.getLoser();
                String message = String.format("%s(%s) beats %s(%s).\\n",
                        winner.getPlayer(), winner.getChoice(), loser.getPlayer(), loser.getChoice());
                System.out.println(message);
            } else {
                ArrayList<PlayerChoice> players = beatResult.getPlayers();
                PlayerChoice player1 = players.get(0),
                        player2 = players.get(1);

                String message = String.format("%s(%s) tie %s(%s).\\n",
                        player1.getPlayer(), player1.getChoice(), player2.getPlayer(), player2.getChoice());
                System.out.println(message);
            }
        }
    }
}
