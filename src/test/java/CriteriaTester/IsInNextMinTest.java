package CriteriaTester;

import alarmClock.model.Reminder;
import alarmClock.model.filtering.CriteriaTester;
import alarmClock.model.filtering.IsInNextSeconds;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;

/**
 * Created by pascal on 6/1/17.
 */
public class IsInNextMinTest {
    Reminder thisMinReminder;
    Reminder nextMinReminder;
    Reminder pastReminder;
    Reminder futureReminder;

    CriteriaTester isInNextMin;
    @Before
    public void initialize(){
        String subject = "titel";
        String descritpion = "bla bla";
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime imminent = LocalDateTime.now().plusMinutes(1);
        LocalDateTime future = LocalDateTime.now().plusMinutes(70);
        LocalDateTime paste = LocalDateTime.now().minusMinutes(1);

        thisMinReminder = new Reminder(subject,descritpion, now);
        nextMinReminder = new Reminder(subject,descritpion, imminent);
        futureReminder = new Reminder(subject,descritpion, future);
        pastReminder = new Reminder(subject,descritpion, paste);
        isInNextMin = new IsInNextSeconds();
    }

    @Test
    public void isTrueReturnsFalseIfThisMin(){
        assertFalse(isInNextMin.isTrue(thisMinReminder));
    }

    @Test
    public void isTrueRetunrsFalseIfReminderIsPassed(){
        assertFalse(isInNextMin.isTrue(pastReminder));
    }

    @Test
    public void isTrueInNextMin(){
        assertFalse(isInNextMin.isTrue(nextMinReminder));
    }

    @Test
    public void isTrueRetunrsFalseIfReminderIsTooFareInTheFuture(){
        assertFalse(isInNextMin.isTrue(futureReminder));
    }
}
