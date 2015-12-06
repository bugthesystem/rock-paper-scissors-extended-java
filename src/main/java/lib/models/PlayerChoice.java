package lib.models;


public class PlayerChoice {

    private String choice;
    private String player;

    public PlayerChoice(String player, String choice) {
        this.choice = choice;
        this.player = player;
    }

    public String getChoice() {
        return choice;
    }

    public String getPlayer() {
        return player;
    }
}
