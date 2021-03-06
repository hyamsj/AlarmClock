package alarmClock.model.filtering;

import alarmClock.model.Reminder;

import java.time.LocalDateTime;

/**
 * Created by pascal on 5/17/17.
 * Decides if a Reminder date is this year
 */
public class IsThisYear implements CriteriaTester {
    /**
     * @param reminder reminder that gets tested if its date is this year.
     * @return the answer if the reminder date is this year.
     */

    @Override
    public boolean isTrue(Reminder reminder) {
        return reminder.getDate().getYear() == LocalDateTime.now().getYear();
    }
}
