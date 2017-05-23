package alarmClock;

import alarmClock.model.ConfigReader;
import alarmClock.controller.Poller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The Main class launches the program
 */
public class Main extends Application {
    private String windowName = "../mainWindow.fxml";
    private String title = "Alarm Clock";

    /**
     * The Application Class requires the start method.
     * It launches the GUI and the programs beneath it
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource(windowName));
        primaryStage.setTitle(title);
        Scene scene = new Scene(root);
        if (new ConfigReader().getColorScheme() == "nightmode") {
            scene.getStylesheets().add("dark.css");
        if (new ConfigReader().isEnableDarkMode()) {
               scene.getStylesheets().add("dark.css");
        } else {
            scene.getStylesheets().add("styles.css");
        }

        primaryStage.setScene(scene);
        primaryStage.show();

        // Creates a poller
        Poller.getInstance();

        try {
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * Main method.
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
