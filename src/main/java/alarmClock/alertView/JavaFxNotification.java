package alarmClock.alertView;

import alarmClock.model.Notification;
import alarmClock.model.Reminder;
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
public class JavaFxNotification extends Stage implements Notification {

    private Reminder reminder;
    private Label label;

    /**
     *
     */
    public JavaFxNotification() {
        super();
    }

    /**
     * @param reminder
     */
    public JavaFxNotification(Reminder reminder) {
        this.reminder = reminder;
    }

    /**
     * @param reminder
     */
    @Override
    public void setReminder(Reminder reminder) {
        this.reminder = reminder;
    }

    /**
     *
     */

    @Override
    public void send() {
        label = new Label("Hello: " + reminder.toString());
        Button okButton = new Button("Ok");
        okButton.setOnAction(e -> {
            this.close();
        });
        VBox pane = new VBox(10, label, okButton);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(10));
        Scene scene = new Scene(pane);
        this.setTitle("Reminder");
        setScene(scene);
        show();
    }

}
