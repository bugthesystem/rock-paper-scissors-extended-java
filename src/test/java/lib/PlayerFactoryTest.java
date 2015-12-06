package lib;

import lib.interfaci.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.security.InvalidParameterException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(MockitoJUnitRunner.class)
public class PlayerFactoryTest {

    @Mock
    IGameLogicStrategy gameLogicStrategyMock;

    @Mock
    IUserWeaponChoiceProvider userWeaponChoiceProviderMock;

    @Mock
    IRandomProvider randomProviderMock;

    IPlayerFactory playerFactory;

    @Before
    public void setUp() throws Exception {
        playerFactory = new PlayerFactory(gameLogicStrategyMock,
                userWeaponChoiceProviderMock,
                randomProviderMock);
    }

    @Test
    public void testCreateComputerPlayer_should_throw_InvalidParameterException_when_name_is_empty() throws Exception {
        assertThatThrownBy(() -> {
            playerFactory.createComputerPlayer("");
        }).isInstanceOf(InvalidParameterException.class)
                .hasMessageContaining("Name must be not empty.");
    }

    @Test
    public void testCreateComputerPlayer() throws Exception {
        IPlayer player = playerFactory.createComputerPlayer("test");

        assertThat(player).isNotNull();
        assertThat(player).isInstanceOf(ComputerPlayer.class);
    }

    @Test
    public void testCreateUserPlayer_should_throw_InvalidParameterException_when_name_is_empty() throws Exception {
        assertThatThrownBy(() -> {
            playerFactory.createUserPlayer("");
        }).isInstanceOf(InvalidParameterException.class)
                .hasMessageContaining("Name must be not empty.");
    }

    @Test
    public void testCreateUserPlayer() throws Exception {
        IPlayer player = playerFactory.createUserPlayer("test");

        assertThat(player).isNotNull();
        assertThat(player).isInstanceOf(UserPlayer.class);
    }
}