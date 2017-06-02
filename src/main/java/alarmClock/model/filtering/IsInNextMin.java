package alarmClock.model.filtering;

import alarmClock.model.Reminder;

import java.time.LocalDateTime;

/**
 * Created by pascal on 5/16/17.
 * Decides if a Reminder is actual in the next Minutes
 */
public class IsInNextMin implements CriteriaTester {
    int nextMinutes = 15;

    /**
     * constructor
     *
     * @param nextMinutes the number of Minutes the filter looks ahead
     */
    public IsInNextMin(int nextMinutes) {
        setNextMinutes(nextMinutes);
    }

    /**
     * default constructor looks 15 minutes ahead.
     */
    public IsInNextMin() {
        setNextMinutes(15);
    }

    /**
     * ordinary setter
     *
     * @param nextMinutes the number of minutes to look ahead
     */
    public void setNextMinutes(int nextMinutes) {
        this.nextMinutes = nextMinutes;
    }

    /**
     * @param reminder the Reminder that gets tested if its date is in the next Minutes
     * @return the Answer if the reminder date is in the next x Minutes
     */
    @Override
    public boolean isTrue(Reminder reminder) {
        return reminder.getDate().isAfter(LocalDateTime.now())
                && reminder.getDate().isBefore(LocalDateTime.now().plusMinutes(nextMinutes));
    }
}
