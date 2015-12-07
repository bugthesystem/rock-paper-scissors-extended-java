package lib.interfaci;

import java.util.Set;

public interface IGameLogicStrategy {

    void addWeapons(String beater, String beaten, String beaterExistingWeapon) throws Exception;

    int canBeat(String weapon1, String weapon2) throws Exception;

    Set<String> getWeapons();

    String getName();
}

