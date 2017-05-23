package alarmClock;

import alarmClock.model.ConfigReader;
import alarmClock.model.Poller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../mainWindow.fxml"));
        primaryStage.setTitle("Alarm Clock \u00a9");
        Scene scene = new Scene(root);
        if(new ConfigReader().getColorScheme() == "nightmode"){
            //TODO generate a nightmode css
         //   scene.getStylesheets().add("styles.css");
        }
        else {
           // scene.getStylesheets().add("styles.css");
        }

        primaryStage.setScene(scene);
        primaryStage.show();
        //primaryStage.setOnCloseRequest(e -> System.exit(0));
        //TODO let Poller use the Model the other classes uses if it runs as part of the GUI
        // otherwise getSerializable sure it gets cloesed when the gui is started and gets restarted when the GUI is closed
        Poller poller = Poller.getInstance();

        // Enable  ErlyAlertController in the ConfigRead.getNotificationType()
//        EarlyAlertController earlyAlertController = new EarlyAlertController();

        try {
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
