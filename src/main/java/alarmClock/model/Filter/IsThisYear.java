package alarmClock.model.Filter;

import alarmClock.model.Reminder;

import java.time.LocalDateTime;

/**
 * Created by pascal on 5/17/17.
 */
public class IsThisYear implements CriteriaTester{

    @Override
    public boolean isTrue(Reminder r) {
        return r.getDate().getYear() == LocalDateTime.now().getYear();
    }
}
