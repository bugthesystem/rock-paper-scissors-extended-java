package lib;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class PlayerTypeTest {

    @Test
    public void testPlayerType_ComputerPlayer() {
        assertThat(PlayerType.valueOf("ComputerPlayer")).isNotNull();
    }

    @Test
    public void testPlayerType_UserPlayer() {
        assertThat(PlayerType.valueOf("UserPlayer")).isNotNull();
    }
}