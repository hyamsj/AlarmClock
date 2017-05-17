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

import java.util.Collection;

/**
 * Created by pascal on 5/17/17.
 */
//Does not need to extend JavaFxNotification
public class MultyReminderNotification extends JavaFxNotification{

        Collection<Reminder> reminders;
        Label label;

        public MultyReminderNotification() {
            super();
        }

    public MultyReminderNotification(Collection<Reminder> reminders) {
                                                                   this.reminders = reminders;
                                                                                              }

    public void setReminders (Collection<Reminder> reminders){
            this.reminders=reminders;
    }

        public void setReminder(Reminder reminder){
            //TODO dirty hack
            this.reminders.add(reminder);
        }

        public void send(){
//        this.initModality(Modality.APPLICATION_MODAL);
            String remindersText = "";
            int i=0;
            for(Reminder r : reminders) {
                remindersText +=  "Passed Event No " + ++i +":\n";
                remindersText += r.toString()+"\n";
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
