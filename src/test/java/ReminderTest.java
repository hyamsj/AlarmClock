import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertSame;
import static junit.framework.TestCase.assertTrue;

import alarmClock.model.Reminder;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by pascal on 5/2/17.
 */
public class ReminderTest {
    @Test
    public void getSubjectProperty() throws Exception {
        String subject = "titel";
        String description = "description fo the Reminder";
        String time = "12:22";
        LocalDate date = LocalDate.of(2017, 3, 4);


        SimpleStringProperty subjectProperty = new SimpleStringProperty(subject);


        Reminder reminder = new Reminder(subject, description, time, date);
        SimpleStringProperty subjectFromPropertyGetter = reminder.getSubjectProperty();
        assertEquals(subjectProperty.get(), subjectFromPropertyGetter.get());
//        assertTrue(subjectProperty.equals(subjectFromPropertyGetter));
        //assertTrue(subjectProperty == subjectFromPropertyGetter);

    }

    @Test
    public void getDescriptionProperty() throws Exception {
    }

    @Test
    public void getTimeProperty() throws Exception {
    }

    @Test
    public void getDateProperty() throws Exception {
    }

    @Test
    public void getSerializable() throws Exception {
    }

    @Test
    public void getSubject() {
        String subject = "titel";
        String description = "description fo the Reminder";
        String timeString = "12:22";
        LocalDate dateTest = LocalDate.of(2017, 3, 4);

        Reminder reminder = new Reminder(subject, description, timeString, dateTest);
        String subjectFromGetter = reminder.getSubject();

        assertEquals(subject, subjectFromGetter);

        /*
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDate dateNow = currentTime.toLocalDate();
        LocalDate dateMAX = LocalDate.MAX;
        LocalDate dateMIN = LocalDate.MIN;
        LocalDateTime time = LocalDateTime.of(2017,3,4,72,23);
        */
    }

    @Test
    public void getDescription() {
        String subject = "titel";
        String description = "description fo the Reminder";
        String timeString = "12:22";
        LocalDate dateTest = LocalDate.of(2017, 3, 4);

        Reminder reminder = new Reminder(subject, description, timeString, dateTest);
        String descriptionFromGetter = reminder.getDescription();

        assertEquals(description, descriptionFromGetter);
    }

    @Test
    public void getTime() {
        String subject = "titel";
        String description = "description fo the Reminder";
        String timeString = "12:22";
        LocalDate dateTest = LocalDate.of(2017, 3, 4);

        Reminder reminder = new Reminder(subject, description, timeString, dateTest);
        String timeFromGetter = reminder.getTime();

        assertEquals(timeString, timeFromGetter);

    }

    @Test
    public void getDate() {
        String subject = "titel";
        String description = "description fo the Reminder";
        String timeString = "12:22";
        LocalDate dateTest = LocalDate.of(2017, 3, 4);

        Reminder reminder = new Reminder(subject, description, timeString, dateTest);
        LocalDate dateFromGetter = reminder.getDate();

        assertEquals(dateTest, dateFromGetter);
    }

    @Test
    public void TestMinMaxDate() {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDate dateNow = currentTime.toLocalDate();
        LocalDate dateMAX = LocalDate.MAX;
        LocalDate dateMIN = LocalDate.MIN;
        LocalDate dateManual = LocalDate.of(2017, 2, 13);

        ReminderGetDateMaxMin(dateNow);
        ReminderGetDateMaxMin(dateMAX);
        ReminderGetDateMaxMin(dateMIN);
        ReminderGetDateMaxMin(dateManual);
    }


    //gets called multiple times by TestMinMaxDate does NOT need a @Test annotation
    public void ReminderGetDateMaxMin(LocalDate date) {
        //TODO test for max min too
        String subject = "titel";
        String description = "description fo the Reminder";
        String timeString = "12:22";

        Reminder reminder = new Reminder(subject, description, timeString, date);
        LocalDate dateFromGetter = reminder.getDate();
        assertEquals(date, dateFromGetter);
    }


}
