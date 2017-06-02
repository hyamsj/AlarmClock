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
        adapter = new BinaryDBAdapterMock();
    }

    @Test
    public void load(){
        //gets Teste in the save() Test
    }

    @Test
    public void save(){
        String subject = "titel";
        String description = "description fo the Reminder";
        //TODO do isTrue with a fixed time
        LocalDateTime date = LocalDateTime.now();
        Reminder reminder1 = new Reminder(subject,description,date);
        Reminder reminder2 = new Reminder(subject+1,description,date);

        reminders1 = new ReminderList();
        reminders2 = new ReminderList();
        reminders1.add(reminder1);
        reminders2.add(reminder2);

        adapter.save(reminders1);

        ObservableList<Reminder> loadedReminders= adapter.load();
        assertFalse(reminders1.equals(reminders2));
        assertEquals(reminders1,loadedReminders);


    }

    /**
     * extends BinaryDBAdpater so that we can give it another path to store the Reminders to a diffrent location.
     * Otherwise the Entrys from the User would otherwise be overwritten.
     */
    public class BinaryDBAdapterMock extends BinaryDBAdapter{
        public BinaryDBAdapterMock(){
            super();
            super.path = "testReminders.ser";
        }

    }

}
