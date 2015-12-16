package ui;

import lib.*;
import lib.interfaci.*;
import lib.models.MatchResult;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Presenter implements IPresenter {
    private IView view;
    IPlayerFactory playerFactory;
    UserWeaponChoiceProvider userWeaponChoiceProvider;
    IRandomProvider randomProvider;
    IGameLogicStrategyResolver strategyResolver;

    public Presenter(IView view) {

        this.view = view;
        IWeaponStorage weaponStorage = new WeaponStorage();

        Set<IGameLogicStrategy> strategies = new HashSet<IGameLogicStrategy>();
        strategies.add(new BasicGameLogicStrategy(weaponStorage));
        strategies.add(new ExtendedGameLogicStrategy(weaponStorage));

        strategyResolver = new GameLogicStrategyResolver(strategies);
        userWeaponChoiceProvider = new UserWeaponChoiceProvider(weaponStorage);
        randomProvider = new RandomProvider();
    }

    @Override
    public void init() {
        IGameLogicStrategy currentStrategy = null;
        try {
            currentStrategy = strategyResolver.resolve(StrategyType.Basic);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
