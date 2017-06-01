package CriteriaTester;

import alarmClock.model.Reminder;
import alarmClock.model.filtering.CriteriaTester;
import alarmClock.model.filtering.HasTag;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by pascal on 6/1/17.
 */
public class HasTagTest {
    Reminder Reminder;

    String tag1;
    String tag2;
    String tag3;

    CriteriaTester hasTag1;
    CriteriaTester hasTag2;
    CriteriaTester hasTag3;

    @Before
    public void initialize(){
        String subject = "titel";
        String descritpion = "bla bla";
        LocalDateTime now = LocalDateTime.now();
        tag1 ="tag1";
        tag2 ="tag2";
        tag3 ="tag3";

        Reminder = new Reminder(subject,descritpion, now);

        Reminder.addTag(tag1);
        Reminder.addTag(tag2);

        hasTag1 = new HasTag(tag1);
        hasTag2 = new HasTag(tag2);
        hasTag3 = new HasTag(tag3);
    }

    @Test
    public void isTrueforSingleTag(){
        assertTrue(hasTag1.isTrue(Reminder));
    }

    @Test
    public void isTrueRetunsFalseIfTagIsMissing(){
        assertFalse(hasTag3.isTrue(Reminder));
    }

}
