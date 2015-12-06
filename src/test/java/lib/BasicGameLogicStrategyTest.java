package lib;

import lib.interfaci.IGameLogicStrategy;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class BasicGameLogicStrategyTest {

    IGameLogicStrategy strategy;

    @Before
    public void setUp() throws Exception {
        strategy = new BasicGameLogicStrategy();
    }

    @Test
    public void testAddPlayers_should_throw() throws Exception {
        assertThatThrownBy(() -> {
            strategy.addPlayers("", "", "");
        }).isInstanceOf(Exception.class)
                .hasMessageContaining("Not implemented.");
    }

    @Test
    public void testCanBeat_throws_error_when_weapon1_is_empty() throws Exception {
        assertThatThrownBy(() -> {
            strategy.canBeat("", "weapon2");
        }).isInstanceOf(Exception.class)
                .hasMessageContaining("weapon1 must be not empty.");
    }

    @Test
    public void testCanBeat_throws_error_when_weapon2_is_empty() throws Exception {
        assertThatThrownBy(() -> {
            strategy.canBeat("weapon1", "");
        }).isInstanceOf(Exception.class)
                .hasMessageContaining("weapon2 must be not empty.");
    }

    @Test
    public void testCanBeat_throws_error_when_given_weapon_not_exists() throws Exception {
        String weapon = "test";
        assertThatThrownBy(() -> {
            strategy.canBeat("test", "weapon2");
        }).isInstanceOf(Exception.class)
                .hasMessageContaining("Invalid weapon " + weapon);
    }


    @Test
    public void testCanBeat_rock_beats_scissors() throws Exception {
        int beat = strategy.canBeat("rock", "scissors");

        assertThat(beat).isEqualTo(1);
    }

    @Test
    public void testCanBeat_paper_beats_rock() throws Exception {
        int beat = strategy.canBeat("paper", "rock");

        assertThat(beat).isEqualTo(1);
    }

    @Test
    public void testCanBeat_scissors_beats_paper() throws Exception {
        int beat = strategy.canBeat("scissors", "paper");

        assertThat(beat).isEqualTo(1);
    }

    @Test
    public void testCanBeat_returns_1_when_weapon1_is_beater() throws Exception {
        int beat = strategy.canBeat("rock", "scissors");

        assertThat(beat).isEqualTo(1);
    }

    @Test
    public void testCanBeat_returns_0_when_weapon2_is_beater() throws Exception {
        int beat = strategy.canBeat("scissors", "rock");

        assertThat(beat).isEqualTo(0);
    }

    @Test
    public void testCanBeat_returns_negative_1_when_result_is_tie() throws Exception {
        int beat = strategy.canBeat("rock", "rock");

        assertThat(beat).isEqualTo(-1);
    }

    @Test
    public void testGetWeapons() throws Exception {
        HashMap<String, Integer> weaponDict = new HashMap<String, Integer>();

        weaponDict.put("rock", 1);
        weaponDict.put("scissors", 2);
        weaponDict.put("paper", 3);

        Set<String> weapons = strategy.getWeapons();

        assertThat(weapons).isEqualTo(weaponDict.keySet());
    }

    @Test
    public void testGetName() throws Exception {
        String expected = "Basic";
        String actual = strategy.getName();

        assertThat(actual).isEqualTo(expected);
    }
}