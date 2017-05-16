import alarmClock.model.Model;
import alarmClock.model.Poller;
import alarmClock.model.Reminder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
/**
 * Created by pascal on 5/3/17.
 */
public class PollerTest {
    String subject;
    String description;
    LocalDateTime date1;
    LocalDateTime date2;
    LocalDateTime date3;
    LocalDateTime date5;
    LocalDate date;
    Reminder reminder1;
    Reminder reminder2;
    Reminder reminder3;
    Reminder reminder4;
    Model m;
    Poller poller;
    Poller poller2;
    @Before
    public void before() throws IOException, ClassNotFoundException {
        subject = "subject ";
        description = "desc";
        date1 =LocalDateTime.now();
        date2 =LocalDateTime.now().minusMinutes(5);
        date3 =LocalDateTime.now().plusMinutes(5);
        date5 =LocalDateTime.now().plusMinutes(20);

        reminder1 = new Reminder(subject+"1",description, date1);
        reminder2 = new Reminder(subject+"2",description, date2);
        reminder3 = new Reminder(subject+"3",description, date3);
        reminder4 = new Reminder(subject+"4",description, date5);
        m = new Model();
    }

    @Test
    public void poller(){
        m.addReminder(reminder1);
        m.addReminder(reminder2);
        m.addReminder(reminder3);
        m.addReminder(reminder4);
        poller = Poller.getInstance();
        //TODO isTrue if  necessary and only necessary notifications  were made
    }

}
