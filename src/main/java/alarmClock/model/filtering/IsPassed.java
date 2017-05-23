package alarmClock.model.filtering;

import alarmClock.model.Reminder;

import java.time.LocalDateTime;

/**
 * Created by pascal on 5/17/17.
 */
public class IsPassed implements CriteriaTester {
    @Override
    public boolean isTrue(Reminder r) {
        return r.getDate().isBefore(LocalDateTime.now());
    }
}
