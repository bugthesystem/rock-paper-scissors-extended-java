import lib.interfaci.CoverageIgnore;
import ui.IPresenter;
import ui.IView;
import ui.Presenter;
import ui.View;

public class Main {
    @CoverageIgnore
    public static void main(String[] arguments) throws Exception {
        IView view = new View();
        IPresenter presenter = new Presenter(view);
        presenter.init();
    }
}
