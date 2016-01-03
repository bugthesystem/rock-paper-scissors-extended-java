package lib.impl;

import com.google.inject.Inject;
import lib.IUserWeaponChoiceProvider;
import lib.IWeaponStorage;

import java.util.*;

public class UserWeaponChoiceProvider implements IUserWeaponChoiceProvider {

    static final String DEFAULT_WEAPON = "paper";
    private IWeaponStorage weaponStorage;

    @Inject
    public UserWeaponChoiceProvider(IWeaponStorage weaponStorage) {
        this.weaponStorage = weaponStorage;
    }

    public String getInput() {

        List<String> weapons = new ArrayList<String>();

        Object[] array = this.weaponStorage.getWeaponNames().toArray();

        for (int i = 0; i < array.length; i++) {
            weapons.add(String.format("%s(%d)", array[i], i));
        }

        System.out.println(String.format("Make your move: %s.\n", String.join(", ", weapons)));
        Scanner scanner = new Scanner(System.in);

        int answer = scanner.nextInt();

        if (answer <= array.length) {
            String weapon = array[answer].toString();
            if (!weapon.isEmpty()) {
                return weapon;
            }

            return DEFAULT_WEAPON;
        } else {
            System.err.println("[INFO] You entered wrong weapon, `paper` will be use default.");
            return DEFAULT_WEAPON;
        }
    }
}

