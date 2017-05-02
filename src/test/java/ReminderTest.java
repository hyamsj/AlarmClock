import alarmClock.model.Reminder;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by pascal on 5/2/17.
 */
public class ReminderTest {
    SimpleStringProperty descriptionProperty;
    SimpleStringProperty timeProperty;
    SimpleObjectProperty<LocalDate> dateProperty;
    SimpleStringProperty subjectProperty;
    Reminder reminder;
    String subject = "titel";
    String description = "description fo the Reminder";
    String time = "12:22";
    LocalDate date = LocalDate.of(2017, 3, 4);

    @Before
    public void beforeTest(){
        descriptionProperty = new SimpleStringProperty(description);
        timeProperty = new SimpleStringProperty(time);
        dateProperty = new SimpleObjectProperty<>(date);
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
        SimpleStringProperty timeFromPropertyGetter = reminder.getTimeProperty();
        assertEquals(timeProperty.get(), timeFromPropertyGetter.get());
    }

    @Test
    public void getDateProperty() throws Exception {
        SimpleObjectProperty<LocalDate> dateFromPropertyGetter = reminder.getDateProperty();
        assertTrue(dateProperty.get().isEqual(dateFromPropertyGetter.get()));
    }

    @Test
    //TODO implement getSerializable Test
    public void getSerializable() throws Exception {

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
        String timeFromGetter = reminder.getTime();
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
