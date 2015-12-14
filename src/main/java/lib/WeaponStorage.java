package lib;

import lib.interfaci.IWeaponStorage;

import java.util.HashMap;
import java.util.Set;


public class WeaponStorage implements IWeaponStorage {

    HashMap<String, Integer> weaponDict;

    public WeaponStorage() {
        weaponDict = new HashMap<String, Integer>();

        weaponDict.put("rock", 1);
        weaponDict.put("scissors", 2);
        weaponDict.put("paper", 3);
    }

    @Override
    public Set<String> getWeaponNames() {
        return this.weaponDict.keySet();
    }

    @Override
    public HashMap<String, Integer> getWeapons() {
        return weaponDict;
    }

    @Override
    public boolean contains(String weapon) {
        return this.weaponDict.containsKey(weapon);
    }

    @Override
    public int get(String weapon) {

        if (this.weaponDict.containsKey(weapon))
            return weaponDict.get(weapon);

        return -1;
    }

    @Override
    public Integer put(String key, int id) {
        return weaponDict.put(key, id);
    }
}
