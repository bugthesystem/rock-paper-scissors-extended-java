package lib;

import lib.impl.*;
import lib.models.MatchResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {

    IGame game;

    @Mock
    IPlayerFactory playerFactoryMock;

    @Mock
    IGameLogicStrategy gameLogicStrategyMock;

    @Mock
    IUserWeaponChoiceProvider userWeaponChoiceProviderMock;

    @Mock
    IRandomProvider randomProviderMock;

    @Before
    public void setUp() throws Exception {
        game = new Game(playerFactoryMock, gameLogicStrategyMock);
    }

    @Test
    public void testAddPlayer_add_ComputerPlayer() throws Exception {
        ComputerPlayer player = new ComputerPlayer(gameLogicStrategyMock, randomProviderMock);
        game.clear();
        when(playerFactoryMock.createComputerPlayer(anyString())).thenReturn(player);

        game.addPlayer(PlayerType.ComputerPlayer);

        assertThat(game.getPlayers().size()).isEqualTo(1);
        assertThat(game.getPlayers().get(0)).isInstanceOf(ComputerPlayer.class);
        verify(playerFactoryMock, times(1)).createComputerPlayer(anyString());
    }

    @Test
    public void testAddPlayer_add_UserPlayer() throws Exception {

        UserPlayer player = new UserPlayer(userWeaponChoiceProviderMock);
        game.clear();
        when(playerFactoryMock.createUserPlayer(anyString())).thenReturn(player);

        game.addPlayer(PlayerType.UserPlayer);

        verify(playerFactoryMock, times(1)).createUserPlayer(anyString());

        List<IPlayer> players = game.getPlayers();
        assertThat(players.size()).isEqualTo(1);
        assertThat(players.get(0)).isInstanceOf(UserPlayer.class);
    }

    @Test
    public void testClear() throws Exception {
        game.clear();
        assertThat(game.getPlayers().size()).isEqualTo(0);
    }

    @Test
    public void testPlay_should_throw_when_player_count_is_invalid() throws Exception {
        String expected = "At least two players should be added to start the game.";

        assertThatThrownBy(() -> {
            game.play();
        }).isInstanceOf(Exception.class).hasMessageContaining(expected);

    }

    @Test
    public void testPlay_randomly_matched() throws Exception {

        IPlayer player1 = new ComputerPlayer(gameLogicStrategyMock, randomProviderMock);
        IPlayer player2 = new ComputerPlayer(gameLogicStrategyMock, randomProviderMock);

        HashMap<String, Integer> weapons = getDefaultWeapons();

        int size = gameLogicStrategyMock.getWeapons().size();
        int expected1 = 1;
        int expected2 = 2;

        doReturn(expected1).doReturn(expected2).when(randomProviderMock).randomize(size);
        doReturn(weapons.keySet()).doReturn(weapons.keySet()).when(gameLogicStrategyMock).getWeapons();
        doReturn(player1).doReturn(player2).when(playerFactoryMock).createComputerPlayer(anyString());

        game.addPlayer(PlayerType.ComputerPlayer);
        game.addPlayer(PlayerType.ComputerPlayer);

        ArrayList<MatchResult> matchResults = game.play();
        assertThat(matchResults).isNotNull();
    }

    @Test
    public void testPlay_when_same_weapons_chose_result_should_be_tie() throws Exception {

        IPlayer player1 = new ComputerPlayer(gameLogicStrategyMock, randomProviderMock);
        IPlayer player2 = new ComputerPlayer(gameLogicStrategyMock, randomProviderMock);

        HashMap<String, Integer> weapons = getDefaultWeapons();

        int size = gameLogicStrategyMock.getWeapons().size();
        int expectedIndex1 = 1;
        int expectedIndex2 = 1;

        doReturn(expectedIndex1).doReturn(expectedIndex2).when(randomProviderMock).randomize(size);
        doReturn(weapons.keySet()).doReturn(weapons.keySet()).when(gameLogicStrategyMock).getWeapons();
        doReturn(player1).doReturn(player2).when(playerFactoryMock).createComputerPlayer(anyString());

        game.addPlayer(PlayerType.ComputerPlayer);
        game.addPlayer(PlayerType.ComputerPlayer);

        String weaponChoice = "rock";
        int matchResult = -1;

        when(gameLogicStrategyMock.canBeat(weaponChoice, weaponChoice)).thenReturn(matchResult);
        ArrayList<MatchResult> matchResults = game.play();
        assertThat(matchResults).isNotNull();
        assertThat(matchResults.get(0).getScore()).isEqualTo("tie");
    }

    @Test
    public void testPlay_when_first_player_wins_should_return_1() throws Exception {
        IPlayer player1 = new ComputerPlayer(gameLogicStrategyMock, randomProviderMock);
        IPlayer player2 = new ComputerPlayer(gameLogicStrategyMock, randomProviderMock);

        HashMap<String, Integer> weapons = getDefaultWeapons();

        doReturn(weapons.keySet()).doReturn(weapons.keySet()).when(gameLogicStrategyMock).getWeapons();

        int size = gameLogicStrategyMock.getWeapons().size();
        int expected1 = 1;
        int expected2 = 0;

        doReturn(expected1).doReturn(expected2).when(randomProviderMock).randomize(size);

        doReturn(player1).doReturn(player2).when(playerFactoryMock).createComputerPlayer(anyString());

        game.addPlayer(PlayerType.ComputerPlayer);
        game.addPlayer(PlayerType.ComputerPlayer);

        String weaponChoice1 = "scissors";
        String weaponChoice2 = "rock";
        int matchResult = 1;

        when(gameLogicStrategyMock.canBeat(weaponChoice1, weaponChoice2)).thenReturn(matchResult);

        ArrayList<MatchResult> matchResults = game.play();
        assertThat(matchResults).isNotNull();
        assertThat(matchResults.get(0).getScore()).isEqualTo("win");
    }

    private HashMap<String, Integer> getDefaultWeapons() {
        HashMap<String, Integer> weaponDict = new HashMap<String, Integer>();
        weaponDict.put("rock", 1);
        weaponDict.put("scissors", 2);
        weaponDict.put("paper", 3);

        return weaponDict;
    }
}