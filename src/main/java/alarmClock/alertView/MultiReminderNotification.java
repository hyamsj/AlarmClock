package alarmClock.alertView;

import alarmClock.model.Reminder;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.Collection;

/**
 * Created by pascal on 5/17/17.
 */
//Does not need to extend JavaFxNotification
public class MultiReminderNotification extends JavaFxNotification {

    Collection<Reminder> reminders;
    Label label;

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
    public void setReminder(Reminder reminder) {
        //TODO dirty hack
        this.reminders.add(reminder);
    }

    /**
     *
     */
    public void send() {
//        this.initModality(Modality.APPLICATION_MODAL);
        String remindersText = "";
        int i = 0;
        for (Reminder r : reminders) {
            remindersText += "Passed Event No " + ++i + ":\n";
            remindersText += r.toString() + "\n";
            System.out.print("added" + r.toString());
        }

        label = new Label(remindersText);
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
