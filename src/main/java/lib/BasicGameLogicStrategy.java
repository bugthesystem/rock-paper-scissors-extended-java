package lib;

import lib.interfaci.IGameLogicStrategy;
import lib.interfaci.IWeaponStorage;

import java.security.InvalidParameterException;
import java.util.Set;

public class BasicGameLogicStrategy implements IGameLogicStrategy {

    private IWeaponStorage weaponStorage;

    public BasicGameLogicStrategy(IWeaponStorage weaponStorage) {
        this.weaponStorage = weaponStorage;
    }

    public void addWeapons(String beater, String beaten, String beaterExistingWeapon) throws Exception {
        throw new Exception("This operation doesn't supported in this strategy.");
    }

    private void assertWeaponExists(int weapon, String name) throws Exception {
        if (weapon == -1) {
            throw new Exception("Invalid weapon " + name);
        }
    }

    public int canBeat(String weapon1, String weapon2) throws Exception {

        if (weapon1.isEmpty()) {
            throw new InvalidParameterException("weapon1 must be not empty.");
        }

        if (weapon2.isEmpty()) {
            throw new InvalidParameterException("weapon2 must be not empty.");
        }

        int weapon1Id = weaponStorage.get(weapon1);
        int weapon2Id = weaponStorage.get(weapon2);

        this.assertWeaponExists(weapon1Id, weapon1);
        this.assertWeaponExists(weapon2Id, weapon2);

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
        return weaponStorage.getWeaponNames();
    }

    public StrategyType getStrategyType() {
        return StrategyType.Basic;
    }
}
