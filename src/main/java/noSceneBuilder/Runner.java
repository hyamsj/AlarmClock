package noSceneBuilder;

/**
 * Created by joni on 25/04/17.
 */

import javafx.application.Application;
import javafx.stage.Stage;

public class Runner extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Model model = new Model();
        Controller controller = new Controller(model);
        new View(model, controller);
    }
}
