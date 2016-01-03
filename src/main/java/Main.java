import ui.IPresenter;
import ui.IView;
import ui.Presenter;
import ui.ConsoleView;

public class Main {
    public static void main(String[] arguments) throws Exception {
        IView view = new ConsoleView();
        IPresenter presenter = new Presenter(view);
        presenter.init();
    }
}
