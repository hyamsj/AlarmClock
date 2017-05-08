package alarmClock.model;

/**
 * Created by pascal on 5/6/17.
 */
public interface DataBaseAdapter {
    //TODO Errorhandling load and save throwing DB not Found or similar stufrf.
    //TODO  test for the exceptions
    ReminderList load();

    void save(ReminderList reminders);
}
