package alarmClock.model.filtering;

import alarmClock.model.Reminder;

import java.time.LocalDateTime;

/**
 * Created by pascal on 5/16/17.
 * Decides if a reminder is today.
 */
public class IsToday implements CriteriaTester {

    /**
     * @param r reminder that gets tested if its date is today.
     * @return the answer if the reminder is today.
     */
    @Override
    public boolean isTrue(Reminder r) {
        LocalDateTime today = LocalDateTime.now();
        return r.getDate().getYear() == today.getYear()
                && r.getDate().getDayOfYear() == today.getDayOfYear();
    }
}
