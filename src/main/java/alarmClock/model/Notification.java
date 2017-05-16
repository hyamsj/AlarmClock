package alarmClock.model;

/**
 * Created by pascal on 5/3/17.
 */
public interface Notification {
    public void setReminder(Reminder reminder);
    public void send();
}
