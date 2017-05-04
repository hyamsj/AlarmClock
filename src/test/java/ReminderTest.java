import alarmClock.model.Reminder;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by pascal on 5/2/17.
 */
public class ReminderTest {

    SimpleStringProperty descriptionProperty;
    SimpleObjectProperty<LocalDateTime> timeProperty;
    SimpleObjectProperty<LocalDate> dateProperty;
    SimpleStringProperty subjectProperty;
    Reminder reminder;
    String subject = "titel";
    String description = "description fo the Reminder";
    LocalDateTime time = LocalDateTime.now();
    LocalDate date = LocalDate.of(2017, 3, 4);

    @Before
    public void beforeTest(){
        descriptionProperty = new SimpleStringProperty(description);
        timeProperty = new SimpleObjectProperty<>(time);
        dateProperty = new SimpleObjectProperty<>(date);
        subjectProperty = new SimpleStringProperty(subject);
        reminder = new Reminder(subject, description, time, date);
    }


    @Test
    public void equalsTest() throws Exception {
        Reminder equalReminder= new Reminder(subject,description,time,date);
        Reminder diffrentReminder = new Reminder(subject+1,description,time,date);
        Reminder diffrentReminder2 = new Reminder(subject,description+1,time,date);
        Reminder diffrentReminder3 = new Reminder(subject,description,time.plusMinutes(1),date);
        Reminder diffrentReminder4 = new Reminder(subject,description+1,time,date.plusMonths(1));

        assertTrue(reminder.equals(reminder));
        assertTrue(reminder.equals(equalReminder));
        assertTrue(equalReminder.equals(reminder));

        assertFalse(reminder.equals(diffrentReminder));
        assertFalse(diffrentReminder.equals(reminder));

        assertFalse(reminder.equals(diffrentReminder2));
        assertFalse(diffrentReminder2.equals(reminder));

        assertFalse(reminder.equals(diffrentReminder3));
        assertFalse(diffrentReminder3.equals(reminder));

        assertFalse(reminder.equals(diffrentReminder4));
        assertFalse(diffrentReminder4.equals(reminder));
    }

    @Test
    public void hashCodeTest() throws Exception {

    }
    @Test
    public void gettersReturnSameSubject(){
        String subjectFromPropertyGetter = reminder.getSubjectProperty().get();
        String subjectFromGetter = reminder.getSubject();
        assertEquals(subjectFromGetter,subjectFromPropertyGetter);
    }


    @Test
    public void gettersReturnSameDescription(){
        String getProperty= reminder.getDescriptionProperty().get();
        String get= reminder.getDescription();
        assertEquals(get,getProperty);
    }

    @Test
    public void gettersReturnSameTime(){
        LocalDateTime getProperty= reminder.getTimeProperty().get();
        LocalDateTime get= reminder.getTime();
        assertEquals(get,getProperty);
    }

    @Test
    public void gettersReturnSameDate(){
        LocalDate getProperty= reminder.getDateProperty().get();
        LocalDate get= reminder.getDate();
        assertEquals(get,getProperty);
    }

    @Test
    public void getSubjectProperty() throws Exception {
        SimpleStringProperty subjectFromPropertyGetter = reminder.getSubjectProperty();
        assertEquals(subjectProperty.get(), subjectFromPropertyGetter.get());
    }


    @Test
    public void getDescriptionProperty() throws Exception {
        SimpleStringProperty descriptionFromPropertyGetter = reminder.getDescriptionProperty();
        assertEquals(descriptionProperty.get(), descriptionFromPropertyGetter.get());
    }

    @Test
    public void getTimeProperty() throws Exception {
        SimpleObjectProperty<LocalDateTime> timeFromPropertyGetter = reminder.getTimeProperty();
        assertEquals(timeProperty.get(), timeFromPropertyGetter.get());
    }

    @Test
    public void getDateProperty() throws Exception {
        SimpleObjectProperty<LocalDate> dateFromPropertyGetter = reminder.getDateProperty();
        assertTrue(dateProperty.get().isEqual(dateFromPropertyGetter.get()));
    }



    @Test
    public void getSubject() {
        String subjectFromGetter = reminder.getSubject();
        assertEquals(subject, subjectFromGetter);
    }

    @Test
    public void getDescription() {
        String descriptionFromGetter = reminder.getDescription();
        assertEquals(description, descriptionFromGetter);
    }

    @Test
    public void getTime() {
        LocalDateTime timeFromGetter = reminder.getTime();
        assertEquals(time, timeFromGetter);
    }

    @Test
    public void getDate() {
        LocalDate dateFromGetter = reminder.getDate();
        assertEquals(date, dateFromGetter);
    }

    @Test
    public void getDateSpecialValues() {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDate dateNow = currentTime.toLocalDate();
        LocalDate dateMAX = LocalDate.MAX;
        LocalDate dateMIN = LocalDate.MIN;
        LocalDate dateManual = LocalDate.of(2017, 2, 13);

        assertTrue(isDateEqualsGetDate(dateNow));
        assertTrue(isDateEqualsGetDate(dateMAX));
        assertTrue(isDateEqualsGetDate(dateMIN));
        assertTrue(isDateEqualsGetDate(dateManual));
    }

    //gets called multiple times by TestMinMaxDate does NOT need a @Test annotation
    public boolean isDateEqualsGetDate(LocalDate date) {
        Reminder reminder = new Reminder(subject, description, time, date);
        LocalDate dateFromGetter = reminder.getDate();
        return (date.equals(dateFromGetter));
    }

}
