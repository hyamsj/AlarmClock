import alarmClock.model.Reminder;
import alarmClock.model.serializableReminder;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by pascal on 5/2/17.
 */
public class SerializableReminderTest {

    SimpleStringProperty descriptionProperty;
    SimpleStringProperty timeProperty;
    SimpleObjectProperty<LocalDate> dateProperty;
    SimpleStringProperty subjectProperty;
    Reminder reminder;
    String subject = "titel";
    String description = "description fo the Reminder";
    String time = "12:22";
    LocalDate date = LocalDate.of(2017, 3, 4);
    serializableReminder sReminder;

    @Before
    public void beforeTest(){
        descriptionProperty = new SimpleStringProperty(description);
        timeProperty = new SimpleStringProperty(time);
        dateProperty = new SimpleObjectProperty<>(date);
        subjectProperty = new SimpleStringProperty(subject);
        reminder = new Reminder(subject, description, time, date);
        sReminder= new serializableReminder(subject,description,time,date);
    }

    @Test
    public void getSubject() throws Exception {
        assertEquals(subject,sReminder.getSubject());
    }

    @Test
    public void getDescription() throws Exception {
        assertEquals(description, sReminder.getDescription());
    }

    @Test
    public void getTime() throws Exception {
        assertEquals(time,sReminder.getTime());
    }

    @Test
    public void getDate(){
        assertEquals(date,sReminder.getDate());
    }

    @Test
    public void getReminder() throws Exception {
        assertEquals(reminder.getDate(),sReminder.getDate());
        assertEquals(reminder.getDescription(),sReminder.getDescription());
        assertEquals(reminder.getSubject(),sReminder.getSubject());
        assertEquals(reminder.getTime(),sReminder.getTime());
    }

}
