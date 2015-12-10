package lib;

import lib.interfaci.IGameLogicStrategy;
import lib.interfaci.IWeaponStorage;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ExtendedGameLogicStrategyTest {

    IGameLogicStrategy strategy;

    @Mock
    IWeaponStorage weaponStorageMock;

    @Before
    public void setUp() throws Exception {
        strategy = new ExtendedGameLogicStrategy(weaponStorageMock);
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
    @Ignore
    public void testAddPlayers() throws Exception {
        Set<String> expected = new HashSet<String>();
        expected.add("rock");
        expected.add("lizard");
        expected.add("spock");
        expected.add("scissors");
        expected.add("paper");

        strategy.addWeapons("lizard", "spock", "rock");

        when(weaponStorageMock.getWeaponNames()).thenReturn(expected);

        Set<String> actual = strategy.getWeapons();

        assertThat(actual).isEqualTo(expected);
        verify(weaponStorageMock, times(1)).getWeaponNames();
    }

    @Test
    @Ignore
    public void testCanBeat_lizard_beats_spock() throws Exception {
        strategy.addWeapons("lizard", "spock", "rock");
        int beat = strategy.canBeat("lizard", "spock");

        assertThat(beat).isEqualTo(1);
    }

    @Test
    public void testGetName() throws Exception {

        String expectedName = "Extended";
        String actual = strategy.getStrategyType().toString();

        assertThat(actual).isEqualTo(expectedName);
    }
}