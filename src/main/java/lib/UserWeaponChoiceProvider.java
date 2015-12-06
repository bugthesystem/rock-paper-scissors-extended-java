package lib;

import com.google.inject.Inject;
import lib.interfaci.IGameLogicStrategy;
import lib.interfaci.IUserWeaponChoiceProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserWeaponChoiceProvider implements IUserWeaponChoiceProvider {

    static final String DEFAULT_WEAPON = "paper";
    private IGameLogicStrategy gameLogicStrategy;

    @Inject
    public UserWeaponChoiceProvider(IGameLogicStrategy gameLogicStrategy) {
        this.gameLogicStrategy = gameLogicStrategy;
    }

    public String getInput() {

        List<String> weapons = new ArrayList<String>();

        Object[] array = this.gameLogicStrategy.getWeapons().toArray();

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

