package alarmClock.notification;

import alarmClock.model.Reminder;

/**
 * Created by pascal on 5/3/17.
 */
public interface Notification {
    void setReminder(Reminder reminder);

    //we wanted to call the method notify, but that is allready a keyword in Java.
    // Send is the second best name for this function
    void send();
}
