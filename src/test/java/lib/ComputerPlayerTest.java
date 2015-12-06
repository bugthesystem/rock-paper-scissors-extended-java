package lib;

import lib.interfaci.IGameLogicStrategy;
import lib.interfaci.IRandomProvider;
import lib.models.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ComputerPlayerTest {

    Player player;

    @Mock
    IGameLogicStrategy gameLogicStrategyMock;

    @Mock
    IRandomProvider randomProviderMock;

    @Before
    public void setUp() {
        player = new ComputerPlayer(gameLogicStrategyMock, randomProviderMock);
    }

    @Test
    public void testGetName() throws Exception {

        String name = "test";
        player.setName(name);

        assertThat(player.getName()).isEqualTo(name);
    }

    @Test
    public void testSetName() throws Exception {

        String name = "test";
        player.setName(name);

        assertThat(player.getName()).isEqualTo(name);
    }

    @Test
    public void testMakeChoice() throws Exception {
        HashSet<String> weapons = new HashSet<String>();
        weapons.add("a");
        weapons.add("b");
        weapons.add("c");

        int expectedIndex = 0;

        when(gameLogicStrategyMock.getWeapons()).thenReturn(weapons);
        when(randomProviderMock.randomize(weapons.size())).thenReturn(expectedIndex);

        String choice = player.makeChoice();

        assertThat(choice).isEqualTo(weapons.toArray()[expectedIndex].toString());

        verify(gameLogicStrategyMock).getWeapons();
        verify(randomProviderMock).randomize(weapons.size());
    }
}