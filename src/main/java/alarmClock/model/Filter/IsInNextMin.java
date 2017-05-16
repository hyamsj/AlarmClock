package alarmClock.model.Filter;

import alarmClock.model.Reminder;

import java.time.LocalDateTime;

/**
 * Created by pascal on 5/16/17.
 */
public class IsInNextMin implements CriteriaTester {
    int nextMinutes = 15;

    public IsInNextMin(int nextMinutes) {
        setNextMinutes(nextMinutes);
    }

    public IsInNextMin(){
        setNextMinutes(15);
    }

    public void setNextMinutes(int nextMinutes){
        this.nextMinutes = nextMinutes;
    }

    public boolean isTrue(Reminder r){
        return r.getDate().isAfter(LocalDateTime.now())
                && r.getDate().isBefore(LocalDateTime.now().plusMinutes(nextMinutes));
    }
}
