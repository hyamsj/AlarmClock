package alarmClock.model;

import javafx.collections.ObservableList;

/**
 * Created by pascal on 5/6/17.
 */
public interface DataBaseAdapter {
    //TODO Errorhandling load and save throwing DB not Found or similar stufrf.
    //TODO  test for the exceptions
    public ObservableList<Reminder> load();
    public void save(ObservableList<Reminder> reminders);
}
