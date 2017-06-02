package alarmClock.model;

/**
 * Created by pascal on 5/6/17.
 * Interface for extracting and writing data from and to the database
 */
public interface DataBaseAdapter {
    /**
     * Loads data from the DB, at this state, the Application only stores  the reminderList in the DB.
     *
     * @return a list with all reminders
     */
    ReminderList load();

    /**
     * Saves the Reminders to a DB
     *
     * @param reminders all reminders to be saved
     */
    void save(ReminderList reminders);
}
