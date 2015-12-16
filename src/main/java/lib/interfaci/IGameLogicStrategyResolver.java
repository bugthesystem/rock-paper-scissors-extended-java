package lib.interfaci;

import lib.StrategyType;

public interface IGameLogicStrategyResolver {

    IGameLogicStrategy resolve(StrategyType strategyType) throws Exception;
}
