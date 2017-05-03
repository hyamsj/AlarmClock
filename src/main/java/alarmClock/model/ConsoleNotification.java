package alarmClock.model;

/**
 * Created by pascal on 5/3/17.
 */
public class ConsoleNotification implements Notification {
        private Reminder reminder;

    public ConsoleNotification(Reminder reminder ){
        this.reminder=reminder;
        //note();
        }

        public void note(){
            System.out.println(reminder.toString());
        }
}