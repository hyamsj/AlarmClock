package alarmClock.model;

import javafx.beans.Observable;
import javafx.collections.ObservableList;

import java.io.*;

/**
 * Created by joni on 25/03/17.
 */
public class Model implements Serializable {

    ObservableList<Reminder> reminders;
    DataBaseAdapter adapter = new BinaryDBAdapter();

    public Model() throws IOException, ClassNotFoundException{
        reminders = adapter.load();
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
            adapter.save(reminders);
        });
        reminders.addListener(new Poller()::onChanged);
    }

    public void removeReminders(ObservableList<Reminder> reminderSelected) {
        reminders.removeAll(reminderSelected);
    }

    public void removeReminder(Reminder reminder) {
        reminders.remove(reminder);
    }

}
