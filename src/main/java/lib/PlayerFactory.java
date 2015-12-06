package lib;

import com.google.inject.Inject;
import lib.interfaci.*;

import java.security.InvalidParameterException;

public class PlayerFactory implements IPlayerFactory {

    private IGameLogicStrategy gameLogicStrategy;
    private IUserWeaponChoiceProvider userWeaponChoiceProvider;
    private IRandomProvider randomProvider;

    @Inject
    public PlayerFactory(IGameLogicStrategy gameLogicStrategy,
                         IUserWeaponChoiceProvider userWeaponChoiceProvider,
                         IRandomProvider randomProvider) {

        this.gameLogicStrategy = gameLogicStrategy;
        this.userWeaponChoiceProvider = userWeaponChoiceProvider;
        this.randomProvider = randomProvider;
    }

    public IPlayer createComputerPlayer(String name) {
        if (name.isEmpty()) {
            throw new InvalidParameterException("Name must be not empty.");
        }

        ComputerPlayer player = new ComputerPlayer(this.gameLogicStrategy, this.randomProvider);
        player.setName(name);

        return player;
    }

    public IPlayer createUserPlayer(String name) {
        if (name.isEmpty()) {
            throw new InvalidParameterException("Name must be not empty.");
        }

        UserPlayer player = new UserPlayer(this.userWeaponChoiceProvider);
        player.setName(name);

        return player;
    }
}