package alarmClock.model.filtering;

import alarmClock.model.Reminder;

import java.time.LocalDateTime;

/**
 * Created by pascal on 5/23/17.
 * Decides if a reminder occurs in the next seconds
 */
public class IsInNextSeconds implements CriteriaTester {
    int nextSeconds = 1;

    /**
     * constructor
     *
     * @param nextSeconds the number of seconds to look ahead.
     */
    public IsInNextSeconds(int nextSeconds) {
        setNextSeconds(nextSeconds);
    }


    /**
     * Default constructor, looks 1 second ahead.
     */
    public IsInNextSeconds() {
        setNextSeconds(1);
    }

    /**
     * ordinary setter
     *
     * @param nextSeconds the numbers of seconds to look ahead
     */
    public void setNextSeconds(int nextSeconds) {
        this.nextSeconds = nextSeconds;
    }

    /**
     * @param r the Reminder that gets tested if its date is in the next Seconds.
     * @return the Answer if the reminder date is in the next x Seconds.
     */
    @Override
    public boolean isTrue(Reminder r) {
        return r.getDate().isAfter(LocalDateTime.now())
                && r.getDate().isBefore(LocalDateTime.now().plusSeconds(nextSeconds));
    }
}
