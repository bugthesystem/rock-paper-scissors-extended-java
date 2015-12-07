import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import lib.*;
import lib.interfaci.*;

public class AppModule extends AbstractModule {
    @CoverageIgnore
    @Override
    protected void configure() {

        bind(IPlayerFactory.class).to(PlayerFactory.class);

        Multibinder<IGameLogicStrategy> strategyMultibinder = Multibinder.newSetBinder(binder(), IGameLogicStrategy.class);

        strategyMultibinder.addBinding().to(BasicGameLogicStrategy.class);
        strategyMultibinder.addBinding().to(ExtendedGameLogicStrategy.class);

        bind(IUserWeaponChoiceProvider.class).to(UserWeaponChoiceProvider.class);

        bind(IGameLogicStrategyResolver.class).to(GameLogicStrategyResolver.class);

        bind(Game.class).to(Game.class);
    }
}
