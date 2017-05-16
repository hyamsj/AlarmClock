import alarmClock.model.Model;
import alarmClock.model.Reminder;
import alarmClock.model.ReminderList;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * Created by pascal on 5/2/17.
 */
public class ModelTest {

    String subject;
    String description;
    LocalDateTime date;
    LocalDateTime date1;
    LocalDateTime date2;
    LocalDateTime date3;

    Reminder reminder;
    Reminder reminder1;
    Reminder reminder2;
    Reminder reminder3;
    Model m;
    @Before
    public void init()throws Exception{

        int year = 2017;
        int month = 4;
        int hour= 23;
        int minute = 55;


        subject = "subject";
        description = "description";
        date = LocalDateTime.of(year,month,1,hour,minute);
        date1 = LocalDateTime.of(year,month,2,hour,minute);
        date2 = LocalDateTime.of(year,month,3,hour,minute);
        date3 = LocalDateTime.of(year,month,4,hour,minute);

        reminder = new Reminder(subject,description,date);
        reminder1 = new Reminder(subject+"1",description,date1);
        reminder2 = new Reminder(subject+"2",description,date2);
        reminder3 = new Reminder(subject+"3",description,date3);
        m= new Model();
    }

    @Test
    public void addReminder() throws Exception {
        m.addReminder(reminder);
        assertTrue(m.getReminders().contains(reminder));
    }

    @Test
    public void getReminders() throws Exception {
        m.addReminder(reminder1);
        m.addReminder(reminder2);
        m.addReminder(reminder3);
        assertTrue(m.getReminders().contains(reminder1));
        assertTrue(m.getReminders().contains(reminder2));
        assertTrue(m.getReminders().contains(reminder3));
    }

    //tests Persistence
    @Test
    public void bindData() throws Exception {
        //TODO more testcases
        m.addReminder(reminder);
        m.bindData();
        Model m2 = new Model();
        m2.getReminders().contains(reminder);
    }


    @Test
    public void removeReminder() throws Exception {
        m.addReminder(reminder);
        m.removeReminder(reminder);
        assertFalse(m.getReminders().contains(reminder));
    }

    @Test
    public void removeReminders() throws Exception {
        m.addReminder(reminder);
        m.addReminder(reminder1);
        m.addReminder(reminder2);
        m.addReminder(reminder3);
        ReminderList reminderObservableList;

        reminderObservableList =  new ReminderList();
        reminderObservableList.add(reminder1);
        reminderObservableList.add(reminder2);
        reminderObservableList.add(reminder3);
        m.removeReminders(reminderObservableList);

        assertTrue(m.getReminders().contains(reminder));
        assertFalse(m.getReminders().contains(reminder1));
        assertFalse(m.getReminders().contains(reminder2));
        assertFalse(m.getReminders().contains(reminder3));
    }
}