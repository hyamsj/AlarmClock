package alarmClock.alertView;

import alarmClock.model.Reminder;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Collection;

/**
 * Created by pascal on 5/17/17.
 */
//Does not need to extend JavaFxNotification
public class MultiReminderNotification extends JavaFxNotification {

    private Collection<Reminder> reminders;
    private Label label;

    /**
     *
     */
    public MultiReminderNotification() {
        super();
    }

    /**
     * @param reminders
     */
    public MultiReminderNotification(Collection<Reminder> reminders) {
        this.reminders = reminders;
    }

    /**
     * @param reminders
     */
    public void setReminders(Collection<Reminder> reminders) {
        this.reminders = reminders;
    }

    /**
     * @param reminder
     */
    @Override
    public void setReminder(Reminder reminder) {
        //TODO dirty hack
        this.reminders.add(reminder);
    }

    /**
     * Creates the Popup the shows Reminders that occurred previously
     */
    @Override
    public void send() {
        String remindersText = "";
        int i = 0;
        for (Reminder r : reminders) {
            remindersText += "Passed Event No " + ++i + ":\n";
            remindersText += r.toString() + "\n";
            System.out.print("added" + r.toString());
        }
        Stage stage = new Stage();
        label = new Label(remindersText);
        Button okButton = new Button("Ok");
        okButton.setOnAction(e -> {
            stage.close();
        });
        VBox pane = new VBox(10, label, okButton);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(10));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Previously Occured Reminders: ");
        stage.setResizable(false);
        stage.show();
    }

}
