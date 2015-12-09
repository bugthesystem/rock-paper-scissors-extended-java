package app;

import lib.*;
import lib.interfaci.IGameLogicStrategy;
import lib.interfaci.IGameLogicStrategyResolver;
import lib.interfaci.IPlayerFactory;
import lib.interfaci.IRandomProvider;
import lib.models.MatchResult;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Presenter {
    private IView view;

    public Presenter(IView view) {

        this.view = view;

        Set<IGameLogicStrategy> strategies = new HashSet<IGameLogicStrategy>();
        strategies.add(new BasicGameLogicStrategy());
        strategies.add(new ExtendedGameLogicStrategy());

        IGameLogicStrategyResolver strategyResolver = new GameLogicStrategyResolver(strategies);

        IGameLogicStrategy currentStrategy = null;
        try {
            currentStrategy = strategyResolver.resolve("Basic");
        } catch (Exception e) {
            e.printStackTrace();
        }

        UserWeaponChoiceProvider userWeaponChoiceProvider = new UserWeaponChoiceProvider(currentStrategy);
        IRandomProvider randomProvider = new RandomProvider();

        IPlayerFactory playerFactory = new PlayerFactory(currentStrategy, userWeaponChoiceProvider, randomProvider);

        Game game = new Game(playerFactory, currentStrategy);

        game.addPlayer(PlayerType.ComputerPlayer);
        game.addPlayer(PlayerType.UserPlayer);
        //game.addPlayer(PlayerType.ComputerPlayer);

        try {
            ArrayList<MatchResult> results = game.play();
            this.view.printResults(results);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
