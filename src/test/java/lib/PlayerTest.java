package lib;

import lib.interfaci.IGameLogicStrategy;
import lib.interfaci.IRandomProvider;
import lib.models.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PlayerTest {

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
}