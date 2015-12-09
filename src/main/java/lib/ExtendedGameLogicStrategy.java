package lib;

import lib.interfaci.IWeaponStorage;

import java.util.HashMap;
import java.util.Map;

public class ExtendedGameLogicStrategy extends BasicGameLogicStrategy {
    private IWeaponStorage weaponStorage;

    public ExtendedGameLogicStrategy(IWeaponStorage weaponStorage) {
        super(weaponStorage);
        this.weaponStorage = weaponStorage;
    }

    public void addWeapons(String beater, String beaten, String beaterExistingWeapon) throws Exception {

        if (beaten.isEmpty() || beater.isEmpty() || beaterExistingWeapon.isEmpty()) {
            throw new Exception("Given additional weapon parameters are invalid.");
        }

        if (!this.weaponStorage.contains(beaterExistingWeapon)) {
            throw new Exception("Given beater existing weapon is invalid.");
        }

        int beaterExistingWeaponId = 0;

        HashMap<String, Integer> weapons = this.weaponStorage.getWeapons();
        //TODO:
        for (Map.Entry<String, Integer> entry : weapons.entrySet()) {
            int currentWeaponId = entry.getValue();
            if (!beaterExistingWeapon.equals(entry.getKey())) {
                this.weaponStorage.put(entry.getKey(), currentWeaponId + 2);
            } else beaterExistingWeaponId = currentWeaponId;
        }


        this.weaponStorage.put(beater, beaterExistingWeaponId + 1);
        this.weaponStorage.put(beaten, beaterExistingWeaponId + 2);
    }

    public String getName() {
        return "Extended";
    }

}
