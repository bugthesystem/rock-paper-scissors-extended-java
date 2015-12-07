package lib;

import lib.interfaci.IGameLogicStrategy;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Set;

public class BasicGameLogicStrategy implements IGameLogicStrategy {

    HashMap<String, Integer> weaponDict;

    public BasicGameLogicStrategy() {
        weaponDict = new HashMap<String, Integer>();

        weaponDict.put("rock", 1);
        weaponDict.put("scissors", 2);
        weaponDict.put("paper", 3);
    }

    public void addWeapons(String beater, String beaten, String beaterExistingWeapon) throws Exception {
        throw new Exception("Not implemented.");
    }

    private void assertWeaponExists(String weapon) throws Exception {
        if (!weaponDict.containsKey(weapon)) {
            throw new Exception("Invalid weapon " + weapon);
        }
    }

    public int canBeat(String weapon1, String weapon2) throws Exception {

        if (weapon1.isEmpty()) {
            throw new InvalidParameterException("weapon1 must be not empty.");
        }

        if (weapon2.isEmpty()) {
            throw new InvalidParameterException("weapon2 must be not empty.");
        }

        this.assertWeaponExists(weapon1);
        this.assertWeaponExists(weapon2);

        int weapon1Id = weaponDict.get(weapon1);
        int weapon2Id = weaponDict.get(weapon2);

        int difference = weapon2Id - weapon1Id;

        if (difference == 0) {
            return -1;
        }

        if (difference < 0) {
            difference *= -1;
            difference++;
        }

        return difference % 2;
    }

    public Set<String> getWeapons() {
        return weaponDict.keySet();
    }

    public String getName() {
        return "Basic";
    }
}
