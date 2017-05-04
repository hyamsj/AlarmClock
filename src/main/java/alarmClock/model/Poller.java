package alarmClock.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;

/**
 * Created by pascal on 5/3/17.
 */
public class Poller {
    private Model model;
    private ObservableList<Reminder> reminders;
    private ObservableList<Reminder> notifiedReminders;
    private Thread one;
    private int delay =1000;

    public Poller(Model model){

        notifiedReminders = FXCollections.observableArrayList();
        this.model =  model;
        one = new Thread() {

            public void run() {
                System.out.println("thread");
                try {
                    while(true){
                        sleep(delay);
                        System.out.println("polling...");
                        poll();
                    }
                } catch(InterruptedException v) {
                    System.out.println(v);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        one.start();
        this.reminders =  model.getReminders();

    }

    public void poll()throws Exception {
            for (Reminder r : reminders) {
                LocalDateTime reminderTime = r.getTime();
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime later = now.plusMinutes(5);
                if (
                        reminderTime.isAfter(now)
                        && later.isAfter(reminderTime)
                        && !notifiedReminders.contains(r)
                   ){
                    Notification n = new ConsoleNotification(r);
                    n.send();
                    System.out.print("Notify");
                    notifiedReminders.addAll(r);
                }
            }
    }


}
