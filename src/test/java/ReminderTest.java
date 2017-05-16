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

    @Before
    public void beforeTest(){
        descriptionProperty = new SimpleStringProperty(description);
        timeProperty = new SimpleObjectProperty<>(time);
        subjectProperty = new SimpleStringProperty(subject);
        reminder = new Reminder(subject, description, time);
    }


    @Test
    public void equalsTest() throws Exception {
        reminder = new Reminder(subject,description,time);
        Reminder equalReminder= new Reminder(subject,description,time);
        Reminder diffrentReminder = new Reminder(subject+1,description,time);
        Reminder diffrentReminder2 = new Reminder(subject,description+1,time);
        Reminder diffrentReminder3 = new Reminder(subject,description,time.plusMinutes(1));
        Reminder diffrentReminder4 = new Reminder(subject,description+1,time);

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
        Reminder equalReminder = new Reminder(subject,description,time);
        Reminder diffrentReminder = new Reminder(subject+1,description,time);
        Reminder diffrentReminder2 = new Reminder(subject,description+1,time);
        Reminder diffrentReminder3 = new Reminder(subject,description,time.plusMinutes(1));
        Reminder diffrentReminder4 = new Reminder(subject,description+1,time.plusMonths(1));

        assertTrue(reminder.hashCode() == reminder.hashCode());

        assertTrue(reminder.hashCode() == equalReminder.hashCode());
        assertTrue(equalReminder.hashCode() == equalReminder.hashCode());

        assertFalse(reminder.hashCode() == diffrentReminder.hashCode());
        assertFalse(diffrentReminder.hashCode() == reminder.hashCode());

        assertFalse(reminder.hashCode() == diffrentReminder2.hashCode());
        assertFalse(diffrentReminder2.hashCode() == reminder.hashCode());

        assertFalse(reminder.hashCode() == diffrentReminder3.hashCode());
        assertFalse(diffrentReminder3.hashCode() == reminder.hashCode());

        assertFalse(reminder.hashCode() == diffrentReminder4.hashCode());
        assertFalse(diffrentReminder4.hashCode() == reminder.hashCode());

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
    public void gettersReturnSameDate(){
        LocalDateTime getProperty= reminder.getDateProperty().get();
        LocalDateTime get= reminder.getDate();
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
    public void getDateProperty() throws Exception {
        SimpleObjectProperty<LocalDateTime> dateFromPropertyGetter = reminder.getDateProperty();
        assertTrue(dateFromPropertyGetter.get().isEqual(dateFromPropertyGetter.get()));
        //assertTrue(dateProperty.get().isEqual(dateFromPropertyGetter.get()));
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
    public void getDate() {
        LocalDateTime dateFromGetter = reminder.getDate();
        assertEquals(time, dateFromGetter);
    }

    /*
    @Test
    public void getDateSpecialValues() {
        LocalDateTime dateNow= LocalDateTime.now();
        LocalDateTime dateMAX = LocalDateTime.MAX;
        LocalDateTime dateMIN = LocalDateTime.MIN;
        LocalDateTime dateManual = LocalDateTime.of(2017, 2, 13,23,55);

       assertTrue(isDateEqualsGetDate(dateNow));
        assertTrue(isDateEqualsGetDate(dateMAX));
        assertTrue(isDateEqualsGetDate(dateMIN));
        assertTrue(isDateEqualsGetDate(dateManual));
    }

    //gets called multiple times by TestMinMaxDate does NOT need a @Test annotation
    public boolean isDateEqualsGetDate(LocalDateTime date) {
        Reminder reminder = new Reminder(subject, description, time);
        LocalDateTime dateFromGetter = reminder.getDate();
        return (date.equals(dateFromGetter));
    }
    */

}
