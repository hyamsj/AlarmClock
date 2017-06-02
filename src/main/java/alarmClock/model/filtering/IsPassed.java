package alarmClock.model.filtering;

import alarmClock.model.Reminder;

import java.time.LocalDateTime;

/**
 * Created by pascal on 5/17/17.
 * Decides if a Reminder Date was in the paste.
 */
public class IsPassed implements CriteriaTester {
    /**
     * @param reminder the reminder that gets tested if its date was in the past.
     * @return the Answer if the reminder was in the past.
     */
    @Override
    public boolean isTrue(Reminder reminder) {
        return reminder.getDate().isBefore(LocalDateTime.now());
    }
}
