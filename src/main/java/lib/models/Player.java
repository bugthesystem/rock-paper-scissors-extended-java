package lib.models;

import lib.IPlayer;

public abstract class Player implements IPlayer {

    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
