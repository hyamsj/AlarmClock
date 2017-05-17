
import alarmClock.model.Reminder;
import alarmClock.model.ReminderList;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by pascal on 5/10/17.
 */

public class ReminderListTest {
    ReminderList reminderList;
    ReminderList reminderList2;
    Reminder reminder;
    Reminder reminder2;
    Reminder reminder3;
    Reminder reminder4;
    ArrayList reminders;

    @Before
    public void before(){
        String descriptionProperty;
        LocalDateTime timeProperty;
        LocalDate dateProperty;
        String subjectProperty;
        String subject = "titel";
        String description = "description fo the Reminder";
        LocalDateTime time = LocalDateTime.now();
        LocalDateTime date = LocalDateTime.of(2017, 3, 4,23,44);
        reminder = new Reminder(subject,description,date);
        reminder2 = new Reminder(subject+2,description,date);
        reminder3 = new Reminder(subject+3,description,date);
        reminder4 = new Reminder(subject+4,description,date);
        reminderList = new ReminderList();
        reminders = new ArrayList();
        reminders.add(reminder);
        reminders.add(reminder2);
        reminders.add(reminder3);




    }
    @Test
    public void undo() throws Exception {
        //undo too many times
        //undo multiple times

        final ReminderList rl1 =  reminderList;
        reminderList.addAll(reminders);
        //TODO should be false?
        assertTrue( reminderList.equals(rl1));
        reminderList.undo();
        assertTrue( reminderList.equals(rl1));
        //ReminderList is empty

        reminderList.addAll(reminders);
        final ReminderList rl2 =  reminderList;

        reminderList.add(reminder4);
        final ReminderList rl3 =  reminderList;

        reminderList.remove(reminder);
        final ReminderList rl4 =reminderList;


        assertTrue(reminderList.equals(rl4));
        reminderList.undo();
        assertTrue(reminderList.equals(rl3));
        reminderList.undo();
        assertTrue(reminderList.equals(rl2));

        /*
        //if there is no more undo operation test if undo does not change anything.
        reminderList.undo();
        assertTrue(reminderList.equals(rl2));
        */

    }

    @Test
    public void redo() throws Exception {
        /*

        //undo too many times
        //undo multiple times

        final ReminderList rl1 = reminderList;
        reminderList.addAll(reminders);
        reminderList.undo();
        assertTrue( reminderList.equals(rl1));

        reminderList.addAll(reminders);
        final ReminderList rl2 =  reminderList;

        /*
        reminderList.add(reminder4);
        final ReminderList rl3 =  reminderList;

        reminderList.remove(reminder);
        final ReminderList rl4 =reminderList;


        assertTrue(reminderList.equals(rl4));
        reminderList.undo();
        assertTrue(reminderList.equals(rl3));
        reminderList.undo();
        assertTrue(reminderList.equals(rl2));

        //redo
        reminderList.redo();
        assertTrue(reminderList.equals(rl3));

        reminderList.redo();
        assertTrue(reminderList.equals(rl4));

        //if there is no redo operation test if nothing happens
        reminderList.redo();
        assertTrue(reminderList.equals(rl4));
        */
    }

    @Test
    public void addListener() throws Exception {
    }

    @Test
    public void removeListener() throws Exception {
    }

    @Test
    public void addAll() throws Exception {
        /*
        /*
        ArrayList<Reminder> reminders = new ArrayList<>();
        reminders.add(reminder);
        reminders.add(reminder2);
        reminders.add(reminder3);
        reminderList.addAll(reminders);

        assertTrue(reminderList.containsAll(reminders));
        assertTrue(reminders.contains(reminders));
        assertTrue(reminders.contains(reminder2));
        assertTrue(reminders.contains(reminder3));
        //reminder4 as not added and should not be in the reminderList
        assertFalse(reminders.contains(reminder4));
        */
    }


    @Test
    public void remove() throws Exception {
        reminderList.add(reminder);
        reminderList.remove(reminder);
        assertTrue(reminderList.isEmpty());

        reminderList.addAll(reminders);
        reminderList.remove(reminder);
        assertFalse(reminderList.contains(reminder));
    }

    @Test
    public void contains() throws Exception {
        assertFalse(reminderList.contains(reminder));
        reminderList.addAll(reminders);
        assertFalse(reminderList.contains(reminder4));
        assertTrue(reminderList.contains(reminder));
        assertTrue(reminderList.contains(reminder2));
        assertTrue(reminderList.contains(reminder3));
    }


    @Test
    public void  isEmpty(){
        reminderList = new ReminderList();
        assertTrue(reminderList.isEmpty());
        reminderList.add(reminder);
        assertFalse(reminderList.isEmpty());
    }

}