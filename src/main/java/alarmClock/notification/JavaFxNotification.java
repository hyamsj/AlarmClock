package alarmClock.notification;

import alarmClock.model.Reminder;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by joni on 16/05/17.
 */
public class JavaFxNotification implements Notification {

    private Reminder reminder;
    private Label label;

    /**
     * Needed by JavaFx
     */
    public JavaFxNotification() {
        super();
    }

    /**
     * Constructor that is given the reminder that this class creates a popup for
     *
     * @param reminder the reminder that is given the notification
     */
    public JavaFxNotification(Reminder reminder) {
        this.reminder = reminder;
    }

    /**
     * Sets the reminder that gets the notification
     *
     * @param reminder the reminder that get's the popup attached to it
     */
    @Override
    public void setReminder(Reminder reminder) {
        this.reminder = reminder;
    }

    /**
     * Creates the Reminder Popup that uses JavaFx.
     */
    @Override
    public void send() {
        /**
         * the Platform Run Later call is needed by JavaFx to handle a Popup that runs independent a.k.a in a
         * different Thread than the "main"-window,
         */
        Platform.runLater(
                () -> {
                    Stage stage = new Stage();
                    label = new Label("Hello: " + reminder.toString());
                    Button okButton = new Button("Ok");
                    okButton.setOnAction(e -> {
                        stage.close();
                    });
                    VBox pane = new VBox(10, label, okButton);
                    pane.setAlignment(Pos.CENTER);
                    pane.setPadding(new Insets(10));
                    Scene scene = new Scene(pane);
                    stage.setTitle("Reminder");
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();

                }
        );

    }

}
