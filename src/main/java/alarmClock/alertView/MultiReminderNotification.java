package alarmClock.alertView;

import alarmClock.model.Reminder;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

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
     *
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

        label = new Label(remindersText);
        Button okButton = new Button("Ok");
        okButton.setOnAction(e -> {
            this.close();
        });
        VBox pane = new VBox(10, label, okButton);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(10));
        Scene scene = new Scene(pane);
        setScene(scene);
        this.setTitle("Previously Occured Reminders: ");
        show();
    }

}
