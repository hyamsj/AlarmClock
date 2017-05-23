package alarmClock.model.filtering;

import alarmClock.model.Reminder;

/**
 * Created by pascal on 5/16/17.
 */
public interface CriteriaTester {
    boolean isTrue(Reminder r);
}
