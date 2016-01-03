package lib.impl;

import lib.impl.IGameLogicStrategy;
import lib.impl.IRandomProvider;
import lib.models.Player;

import java.util.Set;


public class ComputerPlayer extends Player {

    private IGameLogicStrategy gameLogicStrategy;
    private IRandomProvider randomProvider;

    public ComputerPlayer(IGameLogicStrategy gameLogicStrategy, IRandomProvider randomProvider) {

        this.gameLogicStrategy = gameLogicStrategy;
        this.randomProvider = randomProvider;
    }

    public String makeChoice() {
        Set<String> weapons = this.gameLogicStrategy.getWeapons();
        int rnd = this.randomProvider.randomize(weapons.size());
        String result = weapons.toArray()[rnd].toString();

        return result;
    }
}
