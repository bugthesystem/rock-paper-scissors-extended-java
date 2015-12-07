package lib.interfaci;

import java.util.Set;

public interface IGameLogicStrategy {

    int canBeat(String weapon1, String weapon2) throws Exception;

    Set<String> getWeapons();

    String getName();
}

