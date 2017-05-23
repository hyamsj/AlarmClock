package alarmClock.model;

import alarmClock.controller.Poller;
import javafx.beans.Observable;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by joni on 25/03/17.
 */
public class Model implements Serializable {
    private ReminderList reminders;
    DataBaseAdapter adapter = new BinaryDBAdapter();

    /**
     * Constructor loads the Data from the Database
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Model() throws IOException, ClassNotFoundException {
        reminders = adapter.load();
    }

    public void addReminder(Reminder reminder) {
        reminders.add(reminder);
    }

    public ObservableList<Reminder> getReminders() {
        //TODO return only a copy of reminders instead of a reference
        return reminders;
    }


    /**
     * Creates binding with table that overwrites the DB, if anything has changed
     */
    public void bindData() {
        reminders.addListener((Observable obs) -> {
            System.out.println("something changed");
            adapter.save(reminders);
        });
        reminders.addListener(Poller.getInstance()::onChanged);
    }

    public void undo() {
        reminders.undo();
    }

    public void redo() {
        reminders.redo();
    }

    public void removeReminders(ReminderList remindersSelected) {
        reminders.removeAll(remindersSelected);
    }

    public void removeReminder(Reminder reminder) {
        reminders.remove(reminder);
    }

}
