package lib.models;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;


public class MatchResultTest {

    MatchResult matchResult;

    @Before
    public void setUp() throws Exception {
        matchResult = new MatchResult();
    }

    @Test
    public void testGetScore() throws Exception {
        String expected = "test";
        matchResult.setScore(expected);
        String actual = matchResult.getScore();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testSetScore() throws Exception {
        String expected = "test";
        matchResult.setScore(expected);
        String actual = matchResult.getScore();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testGetPlayers_returns_null_when_players_is_empty() throws Exception {
        ArrayList<PlayerChoice> expected = new ArrayList<>();

        matchResult.setPlayers(expected);

        ArrayList<PlayerChoice> actual = matchResult.getPlayers();

        assertThat(actual).isNullOrEmpty();
    }

    @Test
    public void testGetPlayers() throws Exception {
        ArrayList<PlayerChoice> expected = new ArrayList<>();
        expected.add(new PlayerChoice("player", "choice"));

        matchResult.setPlayers(expected);

        ArrayList<PlayerChoice> actual = matchResult.getPlayers();

        assertThat(actual).isEqualTo(expected);
    }


    @Test
    public void testSetPlayers() throws Exception {
        ArrayList<PlayerChoice> expected = new ArrayList<>();
        expected.add(new PlayerChoice("player", "choice"));

        matchResult.setPlayers(expected);

        ArrayList<PlayerChoice> actual = matchResult.getPlayers();

        assertThat(actual).isNotNull();
    }

    @Test
    public void testGetWinner_returns_null_when_winner_has_not_been_set() throws Exception {
        PlayerChoice actual = matchResult.getWinner();
        assertThat(actual).isNull();
    }

    @Test
    public void testGetWinner() throws Exception {
        PlayerChoice winner = new PlayerChoice("player", "choice");
        matchResult.setWinner(winner);

        PlayerChoice actual = matchResult.getWinner();
        assertThat(actual).isNotNull();
        assertThat(actual).isEqualTo(winner);
    }

    @Test
    public void testSetWinner() throws Exception {
        PlayerChoice winner = new PlayerChoice("player", "choice");
        matchResult.setWinner(winner);

        PlayerChoice actual = matchResult.getWinner();
        assertThat(actual).isEqualTo(winner);

    }

    @Test
    public void testGetLoser_returns_null_when_loser_has_not_been_set() throws Exception {
        PlayerChoice actual = matchResult.getLoser();
        assertThat(actual).isNull();
    }

    @Test
    public void testGetLoser() throws Exception {
        PlayerChoice loser = new PlayerChoice("player", "choice");
        matchResult.setLoser(loser);

        PlayerChoice actual = matchResult.getLoser();
        assertThat(actual).isNotNull();
        assertThat(actual).isEqualTo(loser);

    }

    @Test
    public void testSetLoser() throws Exception {
        PlayerChoice loser = new PlayerChoice("player", "choice");
        matchResult.setLoser(loser);

        PlayerChoice actual = matchResult.getLoser();
        assertThat(actual).isEqualTo(loser);

    }
}