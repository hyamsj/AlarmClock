package alarmClock.model;

import javafx.collections.ListChangeListener;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by pascal on 5/3/17.
 * Is a Singelton
 * Tests regularly if a Reminder has to send a notification
 */
public class Poller implements ListChangeListener {
    private static Poller instance = null;
    private ReminderList reminders;
    private ReminderList notifiedReminders;
    private Thread one;
    private int delay = 1000;

    private Poller() {
        this.reminders = new BinaryDBAdapter().load();
        notifiedReminders = new ReminderList();
        System.out.print(" poller was initialized");
        one.start();

    }

    public static Poller getInstance() {
        if (instance == null) {
            instance = new Poller();
        }
        return instance;
    }


    {
        one = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(delay);
                    poll();
                }
            } catch (InterruptedException v) {
                System.out.println(v);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    public void poll() throws Exception {
        HandleNotifications();
    }
    private void HandleNotifications(){
        ArrayList<Reminder> l = reminders.getSerializable();
        for (Reminder r : l) {
            //TODO or make the Reminder store if it did a notifications and test against the Reminder
            if(  ! notifiedReminders.contains(r)){
                boolean success = r.doNotifyIfSoon();
                if(success) notifiedReminders.add(r);
            }


            /*was pushed to the Reminder
            LocalDateTime reminderTime = r.getDate();
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime later = now.plusMinutes(5);
            if (
                    reminderTime.isAfter(now)
                            && later.isAfter(reminderTime)
                            && !notifiedReminders.contains(r)
                    ) {
                Notification n = new ConsoleNotification(r);
                n.send();
                System.out.println("Notify");
                notifiedReminders.add(r);
            }
            */
        }

    }

    @Override
    public void onChanged(Change c) {
        System.out.println("Poller got notified about change");
        this.reminders = new BinaryDBAdapter().load();
        //TODO only update reminders when the proper change happens
    }


}
