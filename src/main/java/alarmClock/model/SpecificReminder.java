package alarmClock.model;

import alarmClock.notification.Notification;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by pascal on 5/17/17.
 * Same as Reminder, only that SpecificReminder can also have tags and specific notifications
 */
public class SpecificReminder extends Reminder {
    Set<Notification> notifications;

    /**
     * Normal constructor of specificReminder
     *
     * @param subject     subject of the reminder
     * @param description description of the reminder
     * @param date        date of the reminder
     */
    public SpecificReminder(String subject, String description, LocalDateTime date) {
        super(subject, description, date);
    }

    /**
     * Constructor with a set of tags
     *
     * @param subject     subject of the reminder
     * @param description description of the reminder
     * @param date        date of the reminder
     * @param tags        set of tage of the reminder
     */
    public SpecificReminder(String subject, String description, LocalDateTime date, Set<String> tags) {
        super(subject, description, date, tags);
    }

    /**
     * Constructor with a set of tags and notifications
     *
     * @param subject       subject of the reminder
     * @param description   description of the reminder
     * @param date          date of the reminder
     * @param tags          tags of the reminder, they help to add tags, or "keywords" to a  reminder.
     * @param notifications notifications of the reminder
     */
    public SpecificReminder(String subject, String description, LocalDateTime date, Set<String> tags, Set<Notification> notifications) {
        this(subject, description, date, tags);
        this.notifications = notifications;
    }

    /**
     * Add a notification to the reminder
     *
     * @param notification notification to be added to the reminder
     */
    public void addNotification(Notification notification) {
        notifications.add(notification);
    }

    /**
     * Removes the Notification from the reminder
     *
     * @param notification notification to be removed from the reminder
     */
    public void removeNotification(Notification notification) {
        notifications.remove(notification);
    }

    /**
     * Every notification in the list is given this reminder and sends the notification
     */
    @Override
    public void doNotify() {
        for (Notification notification : notifications) {
            notification.setReminder(this);
            notification.send();
        }
    }

}
