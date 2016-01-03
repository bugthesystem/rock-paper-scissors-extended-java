package lib;

import lib.models.MatchResult;

import java.util.ArrayList;
import java.util.List;

public interface IGame {
    List<IPlayer> getPlayers();

    ArrayList<MatchResult> play() throws Exception;

    void addPlayer(PlayerType playerType);

    void clear();
}
