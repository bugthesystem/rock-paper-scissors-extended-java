package lib;

import lib.interfaci.IWeaponStorage;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


public class WeaponStorageTest {

    IWeaponStorage weaponStorage;

    @Before
    public void setUp() throws Exception {
        weaponStorage = new WeaponStorage();
    }

    @Test
    public void testGetWeaponNames() throws Exception {
        Set<String> weaponNames = weaponStorage.getWeaponNames();
        assertThat(weaponNames).containsExactly("rock", "scissors", "paper");
    }

    @Test
    public void testGetWeapons() throws Exception {
        HashMap<String, Integer> weapons = weaponStorage.getWeapons();
        assertThat(weapons).containsKeys("rock", "scissors", "paper");
    }

    @Test
    public void testContains() throws Exception {
        boolean result = weaponStorage.contains("rock");
        assertThat(result).isEqualTo(true);
    }

    @Test
    public void testContains_returns_when_given_weapon_doesnt_exists() throws Exception {
        boolean result = weaponStorage.contains("TEST");
        assertThat(result).isEqualTo(false);
    }

    @Test
    public void testGet() throws Exception {
        String test = "test";
        int value = 7;
        weaponStorage.put(test, value);

        int result = weaponStorage.get(test);

        assertThat(result).isEqualTo(value);
    }

    @Test
    public void testGet_not_exists_weapon() throws Exception {
        int result = weaponStorage.get("TEST");
        assertThat(result).isEqualTo(-1);
    }

    @Test
    public void testPut() throws Exception {
        String test = "PUT";
        int value = 666;
        weaponStorage.put(test, value);

        int result = weaponStorage.get(test);

        assertThat(result).isEqualTo(value);
        assertThat(weaponStorage.getWeapons().size()).isEqualTo(4);
    }
}