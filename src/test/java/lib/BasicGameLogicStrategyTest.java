package lib;

import lib.interfaci.IGameLogicStrategy;
import lib.interfaci.IWeaponStorage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class BasicGameLogicStrategyTest {

    IGameLogicStrategy strategy;

    @Mock
    IWeaponStorage weaponStorageMock;

    @Before
    public void setUp() throws Exception {
        strategy = new BasicGameLogicStrategy(weaponStorageMock);
    }

    @Test
    public void testAddPlayers_should_throw() throws Exception {
        assertThatThrownBy(() -> {
            strategy.addWeapons("", "", "");
        }).isInstanceOf(Exception.class)
                .hasMessageContaining("This operation doesn't supported in this strategy.");
    }

    @Test
    public void testCanBeat_throws_error_when_weapon1_is_empty() throws Exception {
        String weapon = "weapon";

        assertThatThrownBy(() -> {
            strategy.canBeat("", weapon);
        }).isInstanceOf(Exception.class)
                .hasMessageContaining("weapon1 must be not empty.");
    }

    @Test
    public void testCanBeat_throws_error_when_weapon2_is_empty() throws Exception {
        String weapon = "weapon";

        assertThatThrownBy(() -> {
            strategy.canBeat(weapon, "");
        }).isInstanceOf(Exception.class)
                .hasMessageContaining("weapon2 must be not empty.");
    }

    @Test
    public void testCanBeat_throws_error_when_given_weapon_not_exists() throws Exception {
        String weapon = "test";

        when(weaponStorageMock.get(weapon)).thenReturn(-1);

        assertThatThrownBy(() -> {
            strategy.canBeat("test", "weapon2");
        }).isInstanceOf(Exception.class)
                .hasMessageContaining("Invalid weapon " + weapon);

        verify(weaponStorageMock, times(1)).get(weapon);
    }


    @Test
    public void testCanBeat_rock_beats_scissors() throws Exception {

        int rockId = 1, scissorsId = 2;
        String rock = "rock", scissors = "scissors";

        when(weaponStorageMock.get(scissors)).thenReturn(scissorsId);
        when(weaponStorageMock.get(rock)).thenReturn(rockId);

        int beat = strategy.canBeat(rock,scissors);

        assertThat(beat).isEqualTo(1);

        verify(weaponStorageMock, times(1)).get(rock);
        verify(weaponStorageMock, times(1)).get(scissors);
    }

    @Test
    public void testCanBeat_paper_beats_rock() throws Exception {

        int rockId = 1, paperId = 3;
        String rock = "rock", paper = "paper";

        when(weaponStorageMock.get(paper)).thenReturn(paperId);
        when(weaponStorageMock.get(rock)).thenReturn(rockId);

        int beat = strategy.canBeat(paper, rock);

        assertThat(beat).isEqualTo(1);

        verify(weaponStorageMock, times(1)).get(rock);
        verify(weaponStorageMock, times(1)).get(paper);
    }

    @Test
    public void testCanBeat_scissors_beats_paper() throws Exception {

        int scissorsId = 2, paperId = 3;
        String scissors = "scissors", paper = "paper";

        when(weaponStorageMock.get(scissors)).thenReturn(scissorsId);
        when(weaponStorageMock.get(paper)).thenReturn(paperId);

        int beat = strategy.canBeat(scissors, paper);

        assertThat(beat).isEqualTo(1);

        verify(weaponStorageMock, times(1)).get(scissors);
        verify(weaponStorageMock, times(1)).get(paper);
    }

    @Test
    public void testCanBeat_returns_1_when_weapon1_is_beater() throws Exception {

        int scissorsId = 2, rockId = 1;
        String scissors = "scissors", rock = "rock";

        when(weaponStorageMock.get(scissors)).thenReturn(scissorsId);
        when(weaponStorageMock.get(rock)).thenReturn(rockId);

        int beat = strategy.canBeat(rock, scissors);

        assertThat(beat).isEqualTo(1);

        verify(weaponStorageMock, times(1)).get(scissors);
        verify(weaponStorageMock, times(1)).get(rock);
    }

    @Test
    public void testCanBeat_returns_0_when_weapon2_is_beater() throws Exception {
        int scissorsId = 2, rockId = 1;
        String scissors = "scissors", rock = "rock";

        when(weaponStorageMock.get(scissors)).thenReturn(scissorsId);
        when(weaponStorageMock.get(rock)).thenReturn(rockId);

        int beat = strategy.canBeat(scissors, rock);

        assertThat(beat).isEqualTo(0);

        verify(weaponStorageMock, times(1)).get(scissors);
        verify(weaponStorageMock, times(1)).get(rock);
    }

    @Test
    public void testCanBeat_returns_negative_1_when_result_is_tie() throws Exception {
        int rockId = 1;
        String rock = "rock";

        doReturn(rockId).doReturn(rockId).when(weaponStorageMock).get(rock);

        int beat = strategy.canBeat(rock, rock);

        assertThat(beat).isEqualTo(-1);

        verify(weaponStorageMock, times(2)).get(rock);
    }

    @Test
    public void testGetWeapons() throws Exception {
        Set<String> weapons = new HashSet<>();
        weapons.add("rock");
        weapons.add("scissors");
        weapons.add("paper");

        when(weaponStorageMock.getWeaponNames()).thenReturn(weapons);

        Set<String> result = strategy.getWeapons();

        assertThat(result).isEqualTo(weapons);
        verify(weaponStorageMock, times(1)).getWeaponNames();
    }

    @Test
    public void testGetName() throws Exception {
        String expected = "Basic";
        String actual = strategy.getName();

        assertThat(actual).isEqualTo(expected);
    }
}