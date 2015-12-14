package lib.interfaci;

import java.util.HashMap;
import java.util.Set;

public interface IWeaponStorage {

    Set<String> getWeaponNames();

    HashMap<String, Integer> getWeapons();

    boolean contains(String weapon);

    int get(String weapon);

    Integer put(String key, int id);
}
