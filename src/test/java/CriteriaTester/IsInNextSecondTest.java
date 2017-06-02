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
public class IsInNextSecondTest {
    Reminder thisSecondReminder;
    Reminder nextSecondReminder;
    Reminder pastReminder;
    Reminder futureReminder;

    CriteriaTester isInNextSeconds;

    @Before
    public void initialize() {
        String subject = "titel";
        String descritpion = "bla bla";
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime inSevenSeconds = LocalDateTime.now().plusSeconds(7);
        LocalDateTime future = LocalDateTime.now().plusSeconds(70);
        LocalDateTime paste = LocalDateTime.now().minusSeconds(1);

        thisSecondReminder = new Reminder(subject, descritpion, now);
        nextSecondReminder = new Reminder(subject, descritpion, inSevenSeconds);
        futureReminder = new Reminder(subject, descritpion, future);
        pastReminder = new Reminder(subject, descritpion, paste);
        isInNextSeconds = new IsInNextSeconds();
    }

    @Test
    public void isTrueReturnsFalseIfThisSecond() {
        assertFalse(isInNextSeconds.isTrue(thisSecondReminder));
    }

    @Test
    public void isTrueRetunrsFalseIfReminderIsPassed() {
        assertFalse(isInNextSeconds.isTrue(pastReminder));
    }

    @Test
    public void isTrueRetunrsFalseIfReminderIsInSevenSeconds() {
        assertFalse(isInNextSeconds.isTrue(nextSecondReminder));
    }

    @Test
    public void isTrueRetunrsFalseIfReminderIsTooFareInTheFuture() {
        assertFalse(isInNextSeconds.isTrue(futureReminder));
    }
}
