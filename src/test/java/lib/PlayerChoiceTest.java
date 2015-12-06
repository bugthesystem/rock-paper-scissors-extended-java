package lib;

import lib.models.PlayerChoice;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerChoiceTest {

    PlayerChoice playerChoice;

    @Test
    public void testGetChoice() throws Exception {
        String choice = "choice";
        playerChoice = new PlayerChoice("player", choice);

        assertThat(playerChoice.getChoice()).isEqualTo(choice);
    }

    @Test
    public void testGetPlayer() throws Exception {
        String player = "player";
        playerChoice = new PlayerChoice("player", "choice");

        assertThat(playerChoice.getPlayer()).isEqualTo(player);
    }
}