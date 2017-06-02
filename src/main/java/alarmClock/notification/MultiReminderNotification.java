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

import java.util.Collection;

/**
 * Created by pascal on 5/17/17. Popup that shows all Reminders that have already occured
 */

public class MultiReminderNotification extends JavaFxNotification {

    private Collection<Reminder> reminders;
    private Label label;

    /**
     * Empty constructor
     */
    public MultiReminderNotification() {
        super();
    }

    /**
     * Constructor that gets the list of all reminders
     *
     * @param reminders the list containing all reminders
     */
    public MultiReminderNotification(Collection<Reminder> reminders) {
        this.reminders = reminders;
    }

    /**
     * Setter for the remindersList
     *
     * @param reminders the list containing all reminders
     */
    public void setReminders(Collection<Reminder> reminders) {
        this.reminders = reminders;
    }

    /**
     * Adds a reminder to the remindersList
     *
     * @param reminder the reminder to be added to the list
     */
    @Override
    public void setReminder(Reminder reminder) {
        this.reminders.add(reminder);
    }

    /**
     * Creates the Popup the shows Reminders that occurred previously
     */
    @Override
    public void send() {
        /**
         * the Platform Run Later call is needed by JavaFx to handle a Popup that runs independent a.k.a in a
         * diffrent Thread than the "main"-window,
         */
        Platform.runLater(
                () -> {
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
        );

    }

}
