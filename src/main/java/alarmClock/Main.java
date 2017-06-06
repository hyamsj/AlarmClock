package alarmClock;

import alarmClock.model.ConfigReader;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The Main class launches the program
 */
public class Main extends Application {
    private String windowName = "/mainWindow.fxml";
    private String title = "Alarm Clock";

    /**
     * Main method.
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The Application Class requires the start method.
     * It launches the GUI and the programs beneath it
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // If set to false, Notification-Popup still come even after the main gui is stopped
        Platform.setImplicitExit(false);
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource(windowName));
        primaryStage.setTitle(title);
        Scene scene = new Scene(root);
        if (new ConfigReader().isEnableDarkMode()) {
            scene.getStylesheets().add("dark.css");
        } else {
            scene.getStylesheets().add("styles.css");
        }

        primaryStage.setScene(scene);
        primaryStage.show();


        try {
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
