package CriteriaTester;

import alarmClock.model.Reminder;
import alarmClock.model.filtering.CriteriaTester;
import alarmClock.model.filtering.IsToday;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by pascal on 6/1/17.
 */
public class IsTodayTest {
    Reminder todayReminder;
    Reminder yesterdayReminder;
    Reminder tomorrowReminder;
    CriteriaTester isTodayTester;

    @Before
    public void initialize() {
        String subject = "titel";
        String descritpion = "bla bla";
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);

        todayReminder = new Reminder(subject, descritpion, today);
        yesterdayReminder = new Reminder(subject, descritpion, yesterday);
        tomorrowReminder = new Reminder(subject, descritpion, tomorrow);
        isTodayTester = new IsToday();
    }

    @Test
    public void isTrueIfTodayIsToday() {
        assertTrue(isTodayTester.isTrue(todayReminder));
    }

    @Test
    public void isTrueRetunrsFalseIfReminderIsYesterday() {
        assertFalse(isTodayTester.isTrue(yesterdayReminder));
    }

    @Test
    public void isTrueRetunrsFalseIfReminderIsTomorrow() {
        assertFalse(isTodayTester.isTrue(tomorrowReminder));
    }

}
