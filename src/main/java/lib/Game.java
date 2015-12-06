package lib;

import com.google.inject.Inject;
import lib.interfaci.IGameLogicStrategy;
import lib.interfaci.IPlayer;
import lib.interfaci.IPlayerFactory;
import lib.models.MatchResult;
import lib.models.PlayerChoice;

import java.util.ArrayList;
import java.util.List;

public class Game {

    List<IPlayer> players;
    private IPlayerFactory playerFactory;
    private IGameLogicStrategy gameLogicStrategy;
    @Inject
    public Game(IPlayerFactory playerFactory, IGameLogicStrategy gameLogicStrategy) {
        this.playerFactory = playerFactory;
        this.gameLogicStrategy = gameLogicStrategy;
        this.players = new ArrayList<IPlayer>();
    }

    public List<IPlayer> getPlayers() {
        return players;
    }

    public ArrayList<MatchResult> play() throws Exception {

        if (this.players.size() < 2) {
            throw new Exception("At least two players should be added to start the game.");
        }

        ArrayList<PlayerChoice> playerChoices = new ArrayList<PlayerChoice>();
        for (IPlayer player : this.players) {
            String choice = player.makeChoice();
            playerChoices.add(new PlayerChoice(player.getName(), choice));
        }

        System.out.println("Game has started.");
        return this.executeRules(playerChoices);
    }

    private ArrayList<MatchResult> executeRules(final ArrayList<PlayerChoice> playerChoices) throws Exception {

        ArrayList<MatchResult> results = new ArrayList<MatchResult>();

        for (int idx = 0; idx < playerChoices.size(); idx++) {
            for (int i = idx + 1; i < playerChoices.size(); i++) {
                final PlayerChoice playerChoice1 = playerChoices.get(idx);
                final PlayerChoice playerChoice2 = playerChoices.get(i);

                int currentResult = this.gameLogicStrategy.canBeat(playerChoice1.getChoice(), playerChoice2.getChoice());

                if (currentResult == -1) {
                    MatchResult matchResult = new MatchResult();
                    matchResult.setScore("tie");
                    ArrayList<PlayerChoice> players = new ArrayList<PlayerChoice>();
                    players.add(playerChoice1);
                    players.add(playerChoice2);
                    matchResult.setPlayers(players);
                    results.add(matchResult);
                } else {

                    PlayerChoice winner, loser;
                    if (currentResult == 1) {
                        winner = playerChoice1;
                        loser = playerChoice2;
                    } else { //currentResult=0
                        winner = playerChoice2;
                        loser = playerChoice1;
                    }

                    MatchResult matchResult = new MatchResult();
                    matchResult.setScore("win");
                    matchResult.setWinner(winner);
                    matchResult.setLoser(loser);
                    results.add(matchResult);
                }
            }
        }

        return results;
    }

    /**
     * Add player to game
     *
     * @param playerType
     */
    public void addPlayer(PlayerType playerType) {
        switch (playerType) {
            case UserPlayer:
                this.players.add(this.playerFactory.createUserPlayer("User"));
                break;
            case ComputerPlayer:
                this.players.add(this.playerFactory.createComputerPlayer("Computer #" + (this.players.size() + 1)));
                break;
        }
    }


    /**
     * Clear game state.
     */
    public void clear() {
        this.players.clear();
    }
}

