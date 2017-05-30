package alarmClock.model;

/**
 * Created by pascal on 5/6/17.
 */
public interface DataBaseAdapter {
    /**
     * Loads data from the DB
     * @return a list with all reminders
     */
    ReminderList load();

    /**
     * Saves the Reminders to a DB
     * @param reminders all reminders to be saved
     */
    void save(ReminderList reminders);
}
