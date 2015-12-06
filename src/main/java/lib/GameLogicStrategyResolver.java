package lib;

import com.google.inject.Inject;
import lib.interfaci.IGameLogicStrategy;
import lib.interfaci.IGameLogicStrategyResolver;

import java.security.InvalidParameterException;
import java.util.Set;

public class GameLogicStrategyResolver implements IGameLogicStrategyResolver {
    private Set<IGameLogicStrategy> strategies;

    @Inject
    public GameLogicStrategyResolver(Set<IGameLogicStrategy> strategies) {
        this.strategies = strategies;
    }

    public IGameLogicStrategy resolve(String name) throws Exception {

        if (name.isEmpty()) {
            throw new InvalidParameterException("Name must be not empty.");
        }

        if (strategies.isEmpty()) {
            throw new Exception("There is no strategy has registered.");
        }

        IGameLogicStrategy result = null;
        for (IGameLogicStrategy strategy : this.strategies) {
            if (strategy.getName().equals(name)) {
                result = strategy;
                break;
            }
        }

        return result;
    }
}
