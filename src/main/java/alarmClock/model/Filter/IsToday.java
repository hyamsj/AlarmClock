package alarmClock.model.Filter;

import alarmClock.model.Reminder;

import java.time.LocalDateTime;

/**
 * Created by pascal on 5/16/17.
 */
public class IsToday implements CriteriaTester {

    @Override
    public boolean isTrue(Reminder r) {
        LocalDateTime today = LocalDateTime.now();
        return r.getDate().getYear() == today.getYear()
            &&r.getDate().getDayOfYear() == today.getDayOfYear();
    }
}
