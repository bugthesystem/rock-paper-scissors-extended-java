package lib;

public interface IGameLogicStrategyResolver {

    IGameLogicStrategy resolve(StrategyType strategyType) throws Exception;
}
