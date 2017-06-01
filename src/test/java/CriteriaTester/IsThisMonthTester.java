package CriteriaTester;
import alarmClock.model.Reminder;
import alarmClock.model.filtering.IsThisMonth;
import alarmClock.model.filtering.CriteriaTester;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by pascal on 6/1/17.
 */
public class IsThisMonthTester {
      Reminder thisMonthReminder;
        Reminder lastMonthReminder;
        Reminder nextMonthReminder;
        CriteriaTester isThisMonth;
        @Before
        public void initialize(){
            String subject = "titel";
            String descritpion = "bla bla";
            LocalDateTime today = LocalDateTime.now();
            LocalDateTime lastYear = LocalDateTime.now().minusMonths(1);
            LocalDateTime nextYear = LocalDateTime.now().plusMonths(1);

            thisMonthReminder = new Reminder(subject,descritpion,today);
            lastMonthReminder = new Reminder(subject,descritpion,lastYear);
            nextMonthReminder = new Reminder(subject,descritpion,nextYear);
            isThisMonth = new IsThisMonth();
        }

        @Test
        public void isTrueIfThisMonth(){
            assertTrue(isThisMonth.isTrue(thisMonthReminder));
        }

        @Test
        public void isTrueRetunrsFalseIfReminderIsLastMonth(){
            assertFalse(isThisMonth.isTrue(nextMonthReminder));
        }

        @Test
        public void isTrueRetunrsFalseIfReminderIsNextMonth(){
            assertFalse(isThisMonth.isTrue(lastMonthReminder));
        }

}
