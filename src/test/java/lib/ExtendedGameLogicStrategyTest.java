package lib;

import lib.impl.ExtendedGameLogicStrategy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;


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
    public void testAddWeapons_should_throw_when_beaterExistingWeapon_invalid() throws Exception {
        assertThatThrownBy(() -> {
            strategy.addWeapons("beater", "beaten", "invalidExistingWeapon");
        }).isInstanceOf(Exception.class)
                .hasMessageContaining("Given beater existing weapon is invalid.");
    }

    @Test
    public void testAddWeapons() throws Exception {
        HashMap<String, Integer> defaultWeapons = new HashMap<String, Integer>();
        defaultWeapons.put("beaterExistingWeapon", 1);
        defaultWeapons.put("scissors", 2);
        defaultWeapons.put("paper", 3);

        when(weaponStorageMock.getWeapons()).thenReturn(defaultWeapons);
        when(weaponStorageMock.contains("beaterExistingWeapon")).thenReturn(true);

        when(weaponStorageMock.put("scissors", 4)).thenReturn(2);
        when(weaponStorageMock.put("paper", 5)).thenReturn(3);

        String weapon1 = "weapon1";
        String weapon2 = "weapon2";

        when(weaponStorageMock.put(weapon1, 2)).thenReturn(null);
        when(weaponStorageMock.put(weapon2, 3)).thenReturn(null);

        strategy.addWeapons(weapon1, weapon2, "beaterExistingWeapon");

        verify(weaponStorageMock, times(1)).getWeapons();
        verify(weaponStorageMock, times(4)).put(anyString(), anyInt());
    }

    @Test
    public void testCanBeat_lizard_beats_spock() throws Exception {

        HashMap<String, Integer> defaultWeapons = new HashMap<String, Integer>();
        defaultWeapons.put("rock", 1);
        defaultWeapons.put("scissors", 2);
        defaultWeapons.put("paper", 3);

        String beaterExistingWeapon = "rock";
        String beater = "lizard";
        String beaten = "spock";

        when(weaponStorageMock.getWeapons()).thenReturn(defaultWeapons);
        when(weaponStorageMock.contains(beaterExistingWeapon)).thenReturn(true);

        when(weaponStorageMock.put("scissors", 4)).thenReturn(2);
        when(weaponStorageMock.put("paper", 5)).thenReturn(3);

        when(weaponStorageMock.put("lizard", 2)).thenReturn(null);
        when(weaponStorageMock.put("spock", 3)).thenReturn(null);

        when(weaponStorageMock.get(beater)).thenReturn(2);
        when(weaponStorageMock.get(beaten)).thenReturn(3);

        strategy.addWeapons(beater, beaten, beaterExistingWeapon);

        int beat = strategy.canBeat(beater, beaten);

        assertThat(beat).isEqualTo(1);

        verify(weaponStorageMock, times(1)).get(beater);
        verify(weaponStorageMock, times(1)).get(beaten);
        verify(weaponStorageMock, times(4)).put(anyString(), anyInt());
    }

    @Test
    public void testGetName() throws Exception {

        String expectedName = "Extended";
        String actual = strategy.getStrategyType().toString();

        assertThat(actual).isEqualTo(expectedName);
    }
}