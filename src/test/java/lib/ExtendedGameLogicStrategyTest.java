package lib;

import lib.interfaci.IGameLogicStrategy;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ExtendedGameLogicStrategyTest {

    IGameLogicStrategy strategy;

    @Before
    public void setUp() throws Exception {
        strategy = new ExtendedGameLogicStrategy();
    }

    @Test
    public void testAddPlayers_should_throw_when_beater_is_empty() throws Exception {
        assertThatThrownBy(() -> {
            strategy.addWeapons("", "beaten", "beaterExistingWeapon");
        }).isInstanceOf(Exception.class)
                .hasMessageContaining("Given additional weapon parameters are invalid.");
    }

    @Test
    public void testAddPlayers_should_throw_when_beaten_is_empty() throws Exception {
        assertThatThrownBy(() -> {
            strategy.addWeapons("beater", "", "beaterExistingWeapon");
        }).isInstanceOf(Exception.class)
                .hasMessageContaining("Given additional weapon parameters are invalid.");
    }

    @Test
    public void testAddPlayers_should_throw_when_beaterExistingWeapon_is_empty() throws Exception {
        assertThatThrownBy(() -> {
            strategy.addWeapons("beater", "beaten", "");
        }).isInstanceOf(Exception.class)
                .hasMessageContaining("Given additional weapon parameters are invalid.");
    }

    @Test
    public void testAddPlayers_should_throw_when_beaterExistingWeapon_invalid() throws Exception {
        assertThatThrownBy(() -> {
            strategy.addWeapons("beater", "beaten", "invalidExistingWeapon");
        }).isInstanceOf(Exception.class)
                .hasMessageContaining("Given beater existing weapon is invalid.");
    }

    @Test
    public void testAddPlayers() throws Exception {
        Set<String> expected = new HashSet<String>();
        expected.add("rock");
        expected.add("lizard");
        expected.add("spock");
        expected.add("scissors");
        expected.add("paper");

        strategy.addWeapons("lizard", "spock", "rock");

        Set<String> actual = strategy.getWeapons();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testCanBeat_lizard_beats_spock() throws Exception {
        strategy.addWeapons("lizard", "spock", "rock");
        int beat = strategy.canBeat("lizard", "spock");

        assertThat(beat).isEqualTo(1);
    }

    @Test
    public void testGetName() throws Exception {

        String expectedName = "Extended";
        String actual = strategy.getName();

        assertThat(actual).isEqualTo(expectedName);
    }
}