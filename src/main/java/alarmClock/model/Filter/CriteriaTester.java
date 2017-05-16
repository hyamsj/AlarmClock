package alarmClock.model.Filter;

import alarmClock.model.Reminder;

/**
 * Created by pascal on 5/16/17.
 */
public interface CriteriaTester {
    public boolean isTrue(Reminder r);
}
