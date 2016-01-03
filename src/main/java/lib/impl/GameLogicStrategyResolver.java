package lib.impl;

import com.google.inject.Inject;
import lib.StrategyType;
import lib.impl.IGameLogicStrategy;
import lib.impl.IGameLogicStrategyResolver;

import java.security.InvalidParameterException;
import java.util.Set;

public class GameLogicStrategyResolver implements IGameLogicStrategyResolver {
    private Set<IGameLogicStrategy> strategies;

    @Inject
    public GameLogicStrategyResolver(Set<IGameLogicStrategy> strategies) {
        this.strategies = strategies;
    }

    public IGameLogicStrategy resolve(StrategyType strategyType) throws Exception {

        if (strategyType == null) {
            throw new InvalidParameterException("strategyType must be not null.");
        }

        if (strategies.isEmpty()) {
            throw new Exception("There is no strategy has registered.");
        }

        for (IGameLogicStrategy strategy : this.strategies) {
            if (strategy.getStrategyType().equals(strategyType)) {
                return strategy;
            }
        }

        return null;
    }
}
