import alarmClock.model.Model;
import alarmClock.model.Reminder;
import alarmClock.model.ReminderList;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static junit.framework.Assert.assertFalse;
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
    Model model;

    @Before
    public void init() throws Exception {

        int year = 2017;
        int month = 4;
        int hour = 23;
        int minute = 55;


        subject = "subject";
        description = "description";
        date = LocalDateTime.of(year, month, 1, hour, minute);
        date1 = LocalDateTime.of(year, month, 2, hour, minute);
        date2 = LocalDateTime.of(year, month, 3, hour, minute);
        date3 = LocalDateTime.of(year, month, 4, hour, minute);

        reminder = new Reminder(subject, description, date);
        reminder1 = new Reminder(subject + "1", description, date1);
        reminder2 = new Reminder(subject + "2", description, date2);
        reminder3 = new Reminder(subject + "3", description, date3);
        model = new Model();
    }

    @Test
    public void addReminder() throws Exception {
        model.addReminder(reminder);
        assertTrue(model.getReminders().contains(reminder));
    }

    @Test
    public void getReminders() throws Exception {
        model.addReminder(reminder1);
        model.addReminder(reminder2);
        model.addReminder(reminder3);
        assertTrue(model.getReminders().contains(reminder1));
        assertTrue(model.getReminders().contains(reminder2));
        assertTrue(model.getReminders().contains(reminder3));
    }

    //tests Persistence
    @Test
    public void bindData() throws Exception {
        //TODO more testcases
        model.addReminder(reminder);
        model.bindData();
        Model m2 = new Model();
        m2.getReminders().contains(reminder);
    }


    @Test
    public void removeReminder() throws Exception {
        model.addReminder(reminder);
        model.removeReminder(reminder);
        assertFalse(model.getReminders().contains(reminder));
    }

    @Test
    public void removeReminders() throws Exception {
        model.addReminder(reminder);
        model.addReminder(reminder1);
        model.addReminder(reminder2);
        model.addReminder(reminder3);
        ReminderList reminderObservableList;

        reminderObservableList = new ReminderList();
        reminderObservableList.add(reminder1);
        reminderObservableList.add(reminder2);
        reminderObservableList.add(reminder3);
        model.removeReminders(reminderObservableList);

        assertTrue(model.getReminders().contains(reminder));
        assertFalse(model.getReminders().contains(reminder1));
        assertFalse(model.getReminders().contains(reminder2));
        assertFalse(model.getReminders().contains(reminder3));
    }
}