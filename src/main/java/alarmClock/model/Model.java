package alarmClock.model;

import alarmClock.controller.Poller;
import javafx.beans.Observable;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by joni on 25/03/17.
 * Handles usage of the data. Data in this case would be the reminders
 */
public class Model implements Serializable {
    private ReminderList reminders; // list with all reminders
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

    /**
     * Adds a new reminder to our reminderList
     *
     * @param reminder the reminder to be added to the list
     */
    public void addReminder(Reminder reminder) {
        reminders.add(reminder);
    }

    /**
     * Returns the list with all reminders
     *
     * @return list with all reminders
     */
    public ObservableList<Reminder> getReminders() {
        return reminders;
    }


    /**
     * Creates binding with table that overwrites the DB, if anything has changed
     */
    public void bindData() {
        reminders.addListener((Observable obs) -> {
            adapter.save(reminders);
        });
        /**
         * this is the place where the Poller gets implicit constructed. Remember that it is a Singleton,
         * therefor the getInstance methode has the role of the constructor.
         */
        reminders.addListener(Poller.getInstance()::onChanged);
    }

    /**
     * Undoes what has been changed on the remindersList
     */
    public void undo() {
        reminders.undo();
    }

    /**
     * Redoes what has been changed on the remindersList
     */
    public void redo() {
        reminders.redo();
    }

    /**
     * Removes all reminders that have been selected
     *
     * @param remindersSelected the selected reminders
     */
    public void removeReminders(ReminderList remindersSelected) {
        reminders.removeAll(remindersSelected);
    }

    /**
     * Removes the reminder that have been selected
     *
     * @param reminder the selected reminder
     */
    public void removeReminder(Reminder reminder) {
        reminders.remove(reminder);
    }

}
