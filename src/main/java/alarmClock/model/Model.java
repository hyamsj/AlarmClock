package alarmClock.model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Observer;

/**
 * Created by joni on 25/03/17.
 */
public class Model implements Serializable {

    ObservableList<Reminder> reminders;
    private Poller p;

    public Model() throws IOException, ClassNotFoundException{
        reminders = new BinaryDBAdapter().load();
    }

    public void addReminder(Reminder reminder) {
        reminders.add(reminder);
    }

    public ObservableList<Reminder> getReminders() {
        //TODO return only a copy of reminders instead of a reference
        return reminders;
    }


    public void bindData() {
        reminders.addListener((Observable obs) -> {
            System.out.println("something changed");
            save();
        });
        reminders.addListener(new Poller()::onChanged);
    }

    private void save() {
        new BinaryDBAdapter().save(reminders);
    }

    public void removeReminders(ObservableList<Reminder> reminderSelected) {
         reminders.removeAll(reminderSelected);
    }

    public void removeReminder(Reminder reminder) {
        reminders.remove(reminder);
    }


}
