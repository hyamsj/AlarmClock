package alarmClock.alertView;

import alarmClock.model.Notification;
import alarmClock.model.Reminder;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by joni on 16/05/17.
 */
public class EarlyAlert extends Stage implements Notification{

    Label label;

    public EarlyAlert(Reminder reminder) {
        label = new Label("Hello: " + reminder.toString());
        Button okButton = new Button("Ok");
        okButton.setOnAction(e -> {
            this.close();
        });
        HBox pane = new HBox(10, okButton, label);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(10));
        Scene scene = new Scene(pane, 200, 50);
        setScene(scene);
        show();
    }

}
