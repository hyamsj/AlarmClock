package alarmClock.model.filtering;

import alarmClock.model.Reminder;

import java.time.LocalDateTime;

/**
 * Created by pascal on 5/23/17.
 */
public class IsInNextSeconds implements  CriteriaTester{
    int nextSeconds = 1;

    public IsInNextSeconds(int nextSeconds) {
        setNextSeconds(nextSeconds);
    }

    public IsInNextSeconds() {
        setNextSeconds(1);
    }

    public void setNextSeconds(int nextSeconds) {
        this.nextSeconds = nextSeconds;
    }

    @Override
    public boolean isTrue(Reminder r) {
        return r.getDate().isAfter(LocalDateTime.now())
                && r.getDate().isBefore(LocalDateTime.now().plusSeconds(nextSeconds));
    }
}
