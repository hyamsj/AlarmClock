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
    LocalDateTime time1;
    LocalDateTime time2;
    LocalDateTime time3;
    LocalDateTime time4;
    LocalDate date;
    Reminder reminder1;
    Reminder reminder2;
    Reminder reminder3;
    Reminder reminder4;
    Model m;
    Poller poller;
    @Before
    public void before() throws IOException, ClassNotFoundException {
        subject = "subject ";
        description = "desc";
        time1=LocalDateTime.now();
        time2=LocalDateTime.now().minusMinutes(5);
        time3=LocalDateTime.now().plusMinutes(5);
        time4=LocalDateTime.now().plusMinutes(20);
        date = LocalDate.of(2017, 4, 23);

        reminder1 = new Reminder(subject+"1",description,time1,date);
        reminder2 = new Reminder(subject+"2",description,time2,date);
        reminder3 = new Reminder(subject+"3",description,time3,date);
        reminder4 = new Reminder(subject+"4",description,time4,date);
        m = new Model();
    }

    @Test
    public void poll(){
        m.addReminder(reminder1);
        m.addReminder(reminder2);
        m.addReminder(reminder3);
        m.addReminder(reminder4);
        poller = new Poller(m);
        //TODO test if  necessary and only necessary notifications  were made
    }
}
