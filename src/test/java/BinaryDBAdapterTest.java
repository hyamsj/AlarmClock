/**
 * Created by pascal on 5/6/17.
 */

import alarmClock.model.BinaryDBAdapter;
import alarmClock.model.Reminder;
import alarmClock.model.ReminderList;
import javafx.collections.ObservableList;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

public class BinaryDBAdapterTest {
    private ReminderList savedReminder;
    private ReminderList reminder;
    private BinaryDBAdapter adapter;

    @Before
    public void before() {
        adapter = new BinaryDBAdapterMock();
    }

    @Test
    public void load() {
        //gets tested in the save() Test
    }

    @Test
    public void save() {
        String subject = "titel";
        String description = "description fo the Reminder";
        LocalDateTime date = LocalDateTime.now();
        Reminder reminder1 = new Reminder(subject, description, date);
        Reminder reminder2 = new Reminder(subject + 1, description, date);

        savedReminder = new ReminderList();
        reminder = new ReminderList();

        savedReminder.add(reminder1);
        reminder.add(reminder2);

        adapter.save(savedReminder);

        ObservableList<Reminder> loadedReminders = adapter.load();
        assertFalse(savedReminder.equals(reminder));
        assertEquals(savedReminder, loadedReminders);
    }

    /**
     * extends BinaryDBAdpater so that we can give it another path to store the Reminders to a diffrent location.
     * Otherwise the entrys from the User would be overwritten when you run this test.
     */
    public class BinaryDBAdapterMock extends BinaryDBAdapter {
        public BinaryDBAdapterMock() {
            super();
            super.path = "testReminders.ser";
        }

    }

}
