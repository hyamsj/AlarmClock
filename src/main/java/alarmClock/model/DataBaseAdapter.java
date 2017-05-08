package alarmClock.model;

import javafx.collections.ObservableList;

/**
 * Created by pascal on 5/6/17.
 */
public interface DataBaseAdapter {
    //TODO Errorhandling load and save throwing DB not Found or similar stufrf.
    //TODO  test for the exceptions
    ObservableList<Reminder> load();

    void save(ObservableList<Reminder> reminders);
}
