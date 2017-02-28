package gui;

public class MainController {

    private MainModel model;

    public MainController(MainModel m) {
        model = m;
    }

    public void increment() {
        model.inc();
    }

    public void reset() {
        model.reset();

    }
}
