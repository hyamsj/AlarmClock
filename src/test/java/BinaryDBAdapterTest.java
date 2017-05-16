/**
 * Created by pascal on 5/6/17.
 */
import alarmClock.model.BinaryDBAdapter;
import alarmClock.model.Reminder;
import alarmClock.model.ReminderList;
import javafx.collections.ObservableList;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
public class BinaryDBAdapterTest {
    private ReminderList reminders1;
    private  ReminderList reminders2;
    private BinaryDBAdapter adapter;
    @Before
    public void before() {
        adapter = new BinaryDBAdapter();
    }

    @Test
    public void load(){
        //gets Teste in the save() Test
    }

    @Test
    public void save(){
        String subject = "titel";
        String description = "description fo the Reminder";
        LocalDateTime time = LocalDateTime.now();
        LocalDate date = LocalDate.of(2017, 3, 4);
        Reminder reminder1 = new Reminder(subject,description,time,date);
        Reminder reminder2 = new Reminder(subject+1,description,time,date);

        reminders1 = new ReminderList();
        reminders2 = new ReminderList();
        reminders1.add(reminder1);
        reminders2.add(reminder2);

        adapter.save(reminders1);

        ObservableList<Reminder> loadedReminders= adapter.load();
        assertFalse(reminders1.equals(reminders2));
        assertEquals(reminders1,loadedReminders);


    }

}
