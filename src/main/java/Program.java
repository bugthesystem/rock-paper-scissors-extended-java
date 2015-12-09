import lib.*;
import lib.interfaci.*;
import lib.models.MatchResult;
import lib.models.PlayerChoice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Program {
    @CoverageIgnore
    public static void main(String[] arguments) throws Exception {

        IWeaponStorage weaponStorage = new WeaponStorage();
        Set<IGameLogicStrategy> strategies = new HashSet<IGameLogicStrategy>();
        strategies.add(new BasicGameLogicStrategy(weaponStorage));
        strategies.add(new ExtendedGameLogicStrategy(weaponStorage));

        IGameLogicStrategyResolver strategyResolver = new GameLogicStrategyResolver(strategies);

        IGameLogicStrategy currentStrategy = strategyResolver.resolve("Basic");

        UserWeaponChoiceProvider userWeaponChoiceProvider = new UserWeaponChoiceProvider(currentStrategy);
        IRandomProvider randomProvider = new RandomProvider();

        IPlayerFactory playerFactory = new PlayerFactory(currentStrategy, userWeaponChoiceProvider, randomProvider);

        Game game = new Game(playerFactory, currentStrategy);

        game.addPlayer(PlayerType.ComputerPlayer);
        game.addPlayer(PlayerType.UserPlayer);
        //game.addPlayer(PlayerType.ComputerPlayer);

        try {
            ArrayList<MatchResult> results = game.play();
            printResults(results);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printResults(ArrayList<MatchResult> results) {
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
