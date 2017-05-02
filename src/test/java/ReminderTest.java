import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import alarmClock.model.Reminder;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by pascal on 5/2/17.
 */
public class ReminderTest {
    @Test
    public void ReminderGetSubject(){
        String subject="titel";
        String description="description fo the Reminder";
        String timeString = "12:22";
        LocalDate dateTest = LocalDate.of(2017,3,4);

        Reminder reminder = new Reminder(subject,description,timeString,dateTest);
        String subjectFromGetter = reminder.getSubject();

        assertEquals(subject,subjectFromGetter);

        /*
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDate dateNow = currentTime.toLocalDate();
        LocalDate dateMAX = LocalDate.MAX;
        LocalDate dateMIN = LocalDate.MIN;
        LocalDateTime time = LocalDateTime.of(2017,3,4,72,23);
        */
    }

    @Test
    public void ReminderGetDescription(){
        String subject="titel";
        String description="description fo the Reminder";
        String timeString = "12:22";
        LocalDate dateTest = LocalDate.of(2017,3,4);

        Reminder reminder = new Reminder(subject,description,timeString,dateTest);
        String descriptionFromGetter = reminder.getDescription();

        assertEquals(description,descriptionFromGetter);
    }

    @Test
    public void ReminderGetTime(){
        String subject="titel";
        String description="description fo the Reminder";
        String timeString = "12:22";
        LocalDate dateTest = LocalDate.of(2017,3,4);

        Reminder reminder = new Reminder(subject,description,timeString,dateTest);
        String timeFromGetter = reminder.getTime();

        assertEquals(timeString,timeFromGetter);

    }

    @Test
    public void ReminderGetDate(){
        String subject="titel";
        String description="description fo the Reminder";
        String timeString = "12:22";
        LocalDate dateTest = LocalDate.of(2017,3,4);

        Reminder reminder = new Reminder(subject,description,timeString,dateTest);
        LocalDate dateFromGetter= reminder.getDate();

        assertEquals(dateTest,dateFromGetter);
    }

    @Test
    public void TestMinMaxDate(){
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDate dateNow = currentTime.toLocalDate();
        LocalDate dateMAX = LocalDate.MAX;
        LocalDate dateMIN = LocalDate.MIN;
        LocalDate dateManual = LocalDate.of(2017,2,13);

        ReminderGetDateMaxMin(dateNow);
        ReminderGetDateMaxMin(dateMAX);
        ReminderGetDateMaxMin(dateMIN);
        ReminderGetDateMaxMin(dateManual);
    }

    public void ReminderGetDateMaxMin(LocalDate date){
        //TODO test for max min too
        String subject="titel";
        String description="description fo the Reminder";
        String timeString = "12:22";

        Reminder reminder = new Reminder(subject,description,timeString,date);
        LocalDate dateFromGetter= reminder.getDate();

        assertEquals(date,dateFromGetter);

    }



}
