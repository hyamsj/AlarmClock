package gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class Runner extends Application {

    public void start(Stage stage) {

        MainModel model = new MainModel();
        MainController controller = new MainController(model);
        new MainView(model, controller);


    }
}
