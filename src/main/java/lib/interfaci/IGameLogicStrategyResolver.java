package lib.interfaci;

public interface IGameLogicStrategyResolver {

    IGameLogicStrategy resolve(String name) throws Exception;
}
