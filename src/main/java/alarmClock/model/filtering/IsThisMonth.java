package alarmClock.model.filtering;

import alarmClock.model.Reminder;

import java.time.LocalDateTime;

/**
 * Created by pascal on 5/16/17.
 * Decides if a reminder date is this month.
 */
public class IsThisMonth implements CriteriaTester {
    /**
     *
     * @param r reminder that gets tested if its date is this month.
     * @return the Answer if the reminder date is this month.
     */
    @Override
    public boolean isTrue(Reminder r) {
        LocalDateTime today = LocalDateTime.now();
        return r.getDate().getYear() == today.getYear()
                && r.getDate().getMonth() == today.getMonth();
    }
}
