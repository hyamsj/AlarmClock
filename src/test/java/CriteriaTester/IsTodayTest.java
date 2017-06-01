package CriteriaTester;

import alarmClock.model.Reminder;
import alarmClock.model.filtering.CriteriaTester;
import alarmClock.model.filtering.IsToday;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static javafx.scene.input.KeyCode.L;
import static javafx.scene.input.KeyCode.R;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by pascal on 6/1/17.
 */
public class IsTodayTest {
    Reminder todayReminder;
    Reminder yesterdayReminder;
    Reminder tomorrowReminder;
    @Before
    public void initialize(){
        String subject = "titel";
        String descritpion = "bla bla";
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);

        todayReminder = new Reminder(subject,descritpion,today);
        yesterdayReminder= new Reminder(subject,descritpion,yesterday);
        tomorrowReminder = new Reminder(subject,descritpion,tomorrow);
    }

    @Test
    public void isTrueIfTodayIsToday(){
        CriteriaTester isTodayTester = new IsToday();
        assertTrue(isTodayTester.isTrue(todayReminder));
    }

    @Test
    public void isTrueRetunrsFalseIfReminderIsYesterday(){
        CriteriaTester isTodayTester = new IsToday();
        assertFalse(isTodayTester.isTrue(yesterdayReminder));
    }

    @Test
    public void isTrueRetunrsFalseIfReminderIsTomorrow(){
        CriteriaTester isTodayTester = new IsToday();
        assertFalse(isTodayTester.isTrue(tomorrowReminder));
    }

}
