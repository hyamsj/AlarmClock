import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertSame;
import static junit.framework.TestCase.assertTrue;

import alarmClock.model.Reminder;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by pascal on 5/2/17.
 */
public class ReminderTest {
    SimpleStringProperty descriptionProperty;
    SimpleStringProperty timeProperty;
    SimpleObjectProperty<LocalDate> dateProperty;
    SimpleStringProperty subjectProperty;
    Reminder reminder;

    @Before
    public void beforeTest(){
        String subject = "titel";
        String description = "description fo the Reminder";
        String time = "12:22";
        LocalDate date = LocalDate.of(2017, 3, 4);

        descriptionProperty = new SimpleStringProperty("");
        timeProperty = new SimpleStringProperty("");
        dateProperty = new SimpleObjectProperty<>();
        subjectProperty = new SimpleStringProperty(subject);
        reminder = new Reminder(subject, description, time, date);

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
        String getProperty= reminder.getTimeProperty().get();
        String get= reminder.getTime();
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
        String subject = "titel";
        String description = "description fo the Reminder";
        String time = "12:22";
        LocalDate date = LocalDate.of(2017, 3, 4);

        SimpleStringProperty descriptionProperty = new SimpleStringProperty("");
        SimpleStringProperty timeProperty = new SimpleStringProperty("");
        SimpleObjectProperty<LocalDate> dateProperty = new SimpleObjectProperty<>();

        SimpleStringProperty subjectProperty = new SimpleStringProperty(subject);


        Reminder reminder = new Reminder(subject, description, time, date);
        SimpleStringProperty subjectFromPropertyGetter = reminder.getSubjectProperty();
        assertEquals(subjectProperty.get(), subjectFromPropertyGetter.get());

    }


    @Test
    public void getDescriptionProperty() throws Exception {
        String subject = "titel";
        String description = "description fo the Reminder";
        String time = "12:22";
        LocalDate date = LocalDate.of(2017, 3, 4);

        SimpleStringProperty descriptionProperty = new SimpleStringProperty(description);
        SimpleStringProperty timeProperty = new SimpleStringProperty(time);
        SimpleObjectProperty<LocalDate> dateProperty = new SimpleObjectProperty<>(LocalDate.MAX);

        SimpleStringProperty subjectProperty = new SimpleStringProperty(subject);


        Reminder reminder = new Reminder(subject, description, time, date);
        SimpleStringProperty descriptionFromPropertyGetter = reminder.getDescriptionProperty();
        assertEquals(descriptionProperty.get(), descriptionFromPropertyGetter.get());
    }

    @Test
    public void getTimeProperty() throws Exception {
        String subject = "titel";
        String description = "description fo the Reminder";
        String time = "12:22";
        LocalDate date = LocalDate.of(2017, 3, 4);

        SimpleStringProperty descriptionProperty = new SimpleStringProperty(description);
        SimpleStringProperty timeProperty = new SimpleStringProperty(time);
        SimpleObjectProperty<LocalDate> dateProperty = new SimpleObjectProperty<>(LocalDate.MAX);



        Reminder reminder = new Reminder(subject, description, time, date);
        SimpleStringProperty timeFromPropertyGetter = reminder.getTimeProperty();
        assertEquals(timeProperty.get(), timeFromPropertyGetter.get());
    }

    @Test
    public void getDateProperty() throws Exception {
        String subject = "titel";
        String description = "description fo the Reminder";
        String time = "12:22";
        LocalDate date = LocalDate.of(2017, 3, 4);

        SimpleStringProperty descriptionProperty = new SimpleStringProperty(description);
        SimpleStringProperty timeProperty = new SimpleStringProperty(time);
        SimpleObjectProperty<LocalDate> dateProperty = new SimpleObjectProperty<>(date);



        Reminder reminder = new Reminder(subject, description, time, date);
        SimpleObjectProperty<LocalDate> dateFromPropertyGetter = reminder.getDateProperty();
        assertTrue(dateProperty.get().isEqual(dateFromPropertyGetter.get()));
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
