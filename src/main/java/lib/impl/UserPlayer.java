package lib.impl;

import lib.IUserWeaponChoiceProvider;
import lib.models.Player;

public class UserPlayer extends Player {

    private IUserWeaponChoiceProvider userWeaponChoiceProvider;

    public UserPlayer(IUserWeaponChoiceProvider userWeaponChoiceProvider) {

        this.userWeaponChoiceProvider = userWeaponChoiceProvider;
    }

    public String makeChoice() {
        String input = this.userWeaponChoiceProvider.getInput();
        System.out.println(String.format("\nYou chose %s.\n", input));

        return input;
    }
}
