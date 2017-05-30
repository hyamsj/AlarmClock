package alarmClock.model;

import alarmClock.notification.Notification;

/**
 * Created by pascal on 5/3/17.
 * Writes the Notifications to the Console.
 */
public class ConsoleNotification implements Notification {
    private Reminder reminder;

    /**
     * Default Constructor ConsoleNotification
     */
    public ConsoleNotification() {

    }

    /**
     * ConsoleNotification Constructor with reminder as Parameter
     *
     * @param reminder the reminder which gets the console notification
     */
    public ConsoleNotification(Reminder reminder) {
        this.reminder = reminder;
    }

    /**
     * Sets the reminder to the consoleNotification
     *
     * @param reminder the reminder which gets the console notification
     */
    @Override
    public void setReminder(Reminder reminder) {
        this.reminder = reminder;

    }

    /**
     * Sends the notification to the console
     */
    @Override
    public void send() {
        System.out.println(reminder.toString());
    }

}
