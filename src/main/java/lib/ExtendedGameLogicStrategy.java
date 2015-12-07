package lib;

import java.util.Map;

public class ExtendedGameLogicStrategy extends BasicGameLogicStrategy {
    @Override
    public void addWeapons(String beater, String beaten, String beaterExistingWeapon) throws Exception {

        if (beaten.isEmpty() || beater.isEmpty() || beaterExistingWeapon.isEmpty()) {
            throw new Exception("Given additional weapon parameters are invalid.");
        }

        if (!this.weaponDict.containsKey(beaterExistingWeapon)) {
            throw new Exception("Given beater existing weapon is invalid.");
        }

        int beaterExistingWeaponId = 0;

        for (Map.Entry<String, Integer> entry : this.weaponDict.entrySet()) {
            int currentWeaponId = entry.getValue();
            if (!beaterExistingWeapon.equals(entry.getKey())) {
                //TODO: concurrency
                this.weaponDict.put(entry.getKey(), currentWeaponId + 2);
            } else beaterExistingWeaponId = currentWeaponId;
        }


        this.weaponDict.put(beater, beaterExistingWeaponId + 1);
        this.weaponDict.put(beaten, beaterExistingWeaponId + 2);
    }

    public String getName() {
        return "Extended";
    }

}
