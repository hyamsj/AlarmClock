package CriteriaTester;

import alarmClock.model.Reminder;
import alarmClock.model.filtering.CriteriaTester;
import alarmClock.model.filtering.IsThisYear;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by pascal on 6/1/17.
 */
public class IsThisYearTester{

        Reminder thisYearReminder;
        Reminder lastYearReminder;
        Reminder nextYearReminder;
        CriteriaTester isThisYear;
        @Before
        public void initialize(){
            String subject = "titel";
            String descritpion = "bla bla";
            LocalDateTime today = LocalDateTime.now();
            LocalDateTime lastYear = LocalDateTime.now().minusYears(1);
            LocalDateTime nextYear = LocalDateTime.now().plusYears(1);

            thisYearReminder= new Reminder(subject,descritpion,today);
            lastYearReminder = new Reminder(subject,descritpion,lastYear);
            nextYearReminder = new Reminder(subject,descritpion,nextYear);
            isThisYear= new IsThisYear();
        }

        @Test
        public void isTrueIfThisYear(){
            assertTrue(isThisYear.isTrue(thisYearReminder));
        }

        @Test
        public void isTrueRetunrsFalseIfReminderIsLastYear(){
            assertFalse(isThisYear.isTrue(nextYearReminder));
        }

        @Test
        public void isTrueRetunrsFalseIfReminderIsNextYear(){
            assertFalse(isThisYear.isTrue(lastYearReminder));
        }

}
