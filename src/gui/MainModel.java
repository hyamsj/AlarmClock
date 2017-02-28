package gui;

public class MainModel {

    private MainView view;
    private int value = 0;

    public void inc() {
        value++;
        view.update();
    }

    public int getValue() {
        return value;
    }

    public void addObserver(MainView v) {
        view = v;
    }

    public void reset() {
        value = 0;
        view.update();
    }
}
