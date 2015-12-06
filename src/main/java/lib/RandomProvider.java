package lib;

import lib.interfaci.IRandomProvider;

public class RandomProvider implements IRandomProvider {
    public int randomize(int max) {
        return (int) (Math.floor(Math.random() * 100 * max) % max);
    }
}
