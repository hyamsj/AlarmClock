package alarmClock.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by pascal on 5/17/17.
 */
public class SpecificReminder extends Reminder {
    Set<Notification> notifications;

    public SpecificReminder(String subject, String description, LocalDateTime date) {
        super(subject, description, date);
    }

    public SpecificReminder(String subject, String description, LocalDateTime date, Set<String> tags) {
        super(subject,description,date,tags);
    }
    public SpecificReminder(String subject, String description, LocalDateTime date, Set<String> tags,Set<Notification> notifications) {
        this(subject,description,date,tags);
        this.notifications=notifications;
    }
    public void addNotification(Notification n){
        notifications.add(n);
    }
    public  void removeNotification(Notification n){
        notifications.remove(n);
    }

    @Override
    public void doNotify() {
        for (Notification n : notifications) {
            n.setReminder(this);
            n.send();
        }
    }

}
