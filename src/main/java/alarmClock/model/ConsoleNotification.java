package alarmClock.model;

/**
 * Created by pascal on 5/3/17.
 */
public class ConsoleNotification implements Notification {
    private Reminder reminder;

   public ConsoleNotification(){

   }

    public ConsoleNotification(Reminder reminder) {
        this.reminder = reminder;
    }
    public void setReminder(Reminder reminder){
        this.reminder = reminder;

    }

    public void send() {
        System.out.println(reminder.toString());
    }
}
