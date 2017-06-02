package CriteriaTester;

import alarmClock.model.Reminder;
import alarmClock.model.filtering.CriteriaTester;
import alarmClock.model.filtering.IsPassed;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by pascal on 6/1/17.
 */
public class IsPassedTest {
    Reminder imminentReminder;
    Reminder pastReminder;
    Reminder tomorrowReminder;
    Reminder nowReminder;
    CriteriaTester isPassed;

    @Before
    public void initialize() {
        String subject = "titel";
        String descritpion = "bla bla";
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime imminent = LocalDateTime.now().plusSeconds(7);
        LocalDateTime past = LocalDateTime.now().minusSeconds(1);
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);

        imminentReminder = new Reminder(subject, descritpion, imminent);
        pastReminder = new Reminder(subject, descritpion, past);
        tomorrowReminder = new Reminder(subject, descritpion, tomorrow);
        nowReminder = new Reminder(subject, descritpion, tomorrow);

        isPassed = new IsPassed();
    }

    @Test
    public void isTrueReturnsFalseIfImminent() {
        assertFalse(isPassed.isTrue(imminentReminder));
    }

    /**
     * Since the Date is only precise to the second, a Reminder that was generated, and a few moments later tested,
     * is not in te paste. However if you run this test on a very weak machiine, that lets the gap between creation and
     * test become more than a second this test might fail.
     */
    @Test
    public void isTrueIfNow() {
        assertFalse(isPassed.isTrue(nowReminder));
    }

    @Test
    public void isTrueIfPassed() {
        assertTrue(isPassed.isTrue(pastReminder));
    }

    @Test
    public void isTrueReturnsFalseIfTomorrow() {
        assertFalse(isPassed.isTrue(tomorrowReminder));
    }
}
