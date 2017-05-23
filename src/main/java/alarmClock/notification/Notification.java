package alarmClock.notification;

import alarmClock.model.Reminder;

/**
 * Created by pascal on 5/3/17.
 */
public interface Notification {
    void setReminder(Reminder reminder);

    void send();
}
