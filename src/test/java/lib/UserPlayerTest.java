package lib;

import lib.impl.UserPlayer;
import lib.models.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserPlayerTest {

    Player player;

    @Mock
    IUserWeaponChoiceProvider userWeaponChoiceProvider;

    @Before
    public void before() {
        player = new UserPlayer(userWeaponChoiceProvider);
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

        when(userWeaponChoiceProvider.getInput()).thenReturn(weapons.toArray()[0].toString());
        String choice = player.makeChoice();

        assertThat(weapons).contains(choice);
        verify(userWeaponChoiceProvider, times(1)).getInput();
    }
}