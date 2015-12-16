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
            strategyResolver.resolve(null);
        })
                .isInstanceOf(InvalidParameterException.class)
                .hasMessageContaining("strategyType must be not null.");
    }

    @Test
    public void testResolve() throws Exception {

        StrategyType strategyType = StrategyType.Basic;

        strategies.add(gameLogicStrategyMock);

        when(gameLogicStrategyMock.getStrategyType()).thenReturn(strategyType);

        IGameLogicStrategy strategy = strategyResolver.resolve(strategyType);

        assertThat(strategy).isNotNull();

        verify(gameLogicStrategyMock, times(1)).getStrategyType();

        assertThat(strategy.getStrategyType()).isEqualTo(strategyType);
    }

    @Test
    public void testResolve_throws_Exception_when_strategies_is_empty() throws Exception {

        strategies = new HashSet<>();
        strategyResolver = new GameLogicStrategyResolver(strategies);
        assertThatThrownBy(() -> {
            strategyResolver.resolve(StrategyType.Basic);
        })
                .isInstanceOf(Exception.class)
                .hasMessageContaining("There is no strategy has registered.");


    }

    @Test
    public void testResolve_returns_null_when_strategy_not_found() throws Exception {

        strategies = new HashSet<>();
        strategies.add(gameLogicStrategyMock);
        strategyResolver = new GameLogicStrategyResolver(strategies);

        when(gameLogicStrategyMock.getStrategyType()).thenReturn(StrategyType.Basic);

        IGameLogicStrategy result = strategyResolver.resolve(StrategyType.Extended);

        assertThat(result).isNull();
    }
}