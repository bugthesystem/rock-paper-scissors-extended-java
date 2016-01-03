package lib;

import lib.impl.UserWeaponChoiceProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class UserWeaponChoiceProviderTest {

    IUserWeaponChoiceProvider provider;

    @Mock
    IWeaponStorage weaponStorageMock;

    @Before
    public void setUp() throws Exception {
        provider = new UserWeaponChoiceProvider(weaponStorageMock);
    }

    @Test
    public void testGetInput() throws Exception {
        InputStream original = System.in;

        ByteArrayInputStream in = new ByteArrayInputStream("0".getBytes());
        System.setIn(in);

        HashSet<String> weapons = new HashSet<String>();
        weapons.add("a");
        weapons.add("b");
        weapons.add("c");

        when(weaponStorageMock.getWeaponNames()).thenReturn(weapons);

        String input = provider.getInput();

        assertThat(weapons).contains(input);

        verify(weaponStorageMock).getWeaponNames();

        System.setIn(original);
    }

    @Test
    public void testGetInput_if_user_enter_wrong_input_should_return_default_weapon() throws Exception {
        String defaultWeapon = "paper";
        InputStream original = System.in;

        ByteArrayInputStream in = new ByteArrayInputStream("5".getBytes());
        System.setIn(in);

        HashSet<String> weapons = new HashSet<String>();
        weapons.add("a");
        weapons.add("b");
        weapons.add("c");

        when(weaponStorageMock.getWeaponNames()).thenReturn(weapons);

        String input = provider.getInput();

        assertThat(input).isEqualTo(defaultWeapon);

        verify(weaponStorageMock).getWeaponNames();

        System.setIn(original);
    }

    @Test
    public void testGetInput_if_user_enter_wrong_input_system_err_should_write_message() throws Exception {
        String defaultWeapon = "paper";
        String newLineSeperator = System.getProperty("line.separator");
        String expectedMessage = "[INFO] You entered wrong weapon, `paper` will be use default." + newLineSeperator;

        ByteArrayInputStream in = new ByteArrayInputStream("5".getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outContent));

        HashSet<String> weapons = new HashSet<String>();
        weapons.add("a");
        weapons.add("b");
        weapons.add("c");

        when(weaponStorageMock.getWeaponNames()).thenReturn(weapons);

        String input = provider.getInput();

        assertThat(input).isEqualTo(defaultWeapon);
        assertThat(expectedMessage)
                .isEqualTo(outContent.toString());

        verify(weaponStorageMock).getWeaponNames();
    }

    @Test
    public void testGetInput_if_user_weapon_is_empty_should_return_default_weapon() throws Exception {
        String defaultWeapon = "paper";

        ByteArrayInputStream in = new ByteArrayInputStream("0".getBytes());
        System.setIn(in);

        HashSet<String> weapons = new HashSet<String>();
        weapons.add("a");
        weapons.add("b");
        weapons.add("c");
        weapons.add("");

        when(weaponStorageMock.getWeaponNames()).thenReturn(weapons);

        String input = provider.getInput();

        assertThat(input).isEqualTo(defaultWeapon);

        verify(weaponStorageMock).getWeaponNames();
    }
}