package lib;

import lib.interfaci.IGameLogicStrategy;
import lib.interfaci.IGameLogicStrategyResolver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GameLogicStrategyResolverTest {

    IGameLogicStrategyResolver strategyResolver;
    Set<IGameLogicStrategy> strategies;

    @Mock
    IGameLogicStrategy gameLogicStrategyMock;

    @Before
    public void setUp() throws Exception {
        strategies = new HashSet<IGameLogicStrategy>();

        strategyResolver = new GameLogicStrategyResolver(strategies);
    }

    @Test
    public void testResolve_should_throw_InvalidParameterException_strategy_name_is_empty() throws Exception {

        assertThatThrownBy(() -> {
            strategyResolver.resolve("");
        })
                .isInstanceOf(InvalidParameterException.class)
                .hasMessageContaining("Name must be not empty.");
    }

    @Test
    public void testResolve_should_fail_when_strategies_empty() throws Exception {
        assertThatThrownBy(() -> {
            strategyResolver.resolve("test");
        })
                .isInstanceOf(Exception.class)
                .hasMessageContaining("There is no strategy has registered.");
    }

    @Test
    public void testResolve_should_return_null_when_strategy_not_found() throws Exception {

        String strategyName = "basic";

        strategies.add(gameLogicStrategyMock);

        when(gameLogicStrategyMock.getName()).thenReturn(strategyName);

        IGameLogicStrategy strategy = strategyResolver.resolve("test");

        assertThat(strategy).isNull();

        verify(gameLogicStrategyMock, times(1)).getName();
    }

    @Test
    public void testResolve() throws Exception {

        String strategyName = "basic";

        strategies.add(gameLogicStrategyMock);

        when(gameLogicStrategyMock.getName()).thenReturn(strategyName);

        IGameLogicStrategy strategy = strategyResolver.resolve(strategyName);

        assertThat(strategy).isNotNull();

        verify(gameLogicStrategyMock, times(1)).getName();

        assertThat(strategy.getName()).isEqualTo(strategyName);
    }
}