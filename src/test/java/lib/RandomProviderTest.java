package lib;

import lib.interfaci.IRandomProvider;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class RandomProviderTest {

    IRandomProvider randomProvider;

    @Before
    public void setUp() throws Exception {
        randomProvider = new RandomProvider();
    }

    @Test
    public void testRandomize() throws Exception {
        int result = randomProvider.randomize(10);
        assertThat(result).isLessThan(10);
    }
}