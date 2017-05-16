package alarmClock.model;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by pascal on 5/3/17.
 */
public class Poller implements ListChangeListener {
    private static Poller instance = null;
    private ReminderList reminders;
    private ReminderList notifiedReminders;
    private Thread one;
    private int delay = 1000;

    protected Poller() {
        this.reminders = new BinaryDBAdapter().load();
        notifiedReminders = new ReminderList();
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
            System.out.println("thread");
            try {
                while (true) {
                    Thread.sleep(delay);
                    System.out.println("polling...");
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
        ArrayList<Reminder> l = reminders.getSerializable();
        for (Reminder r : l) {
            LocalDateTime reminderTime = r.getDate();
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime later = LocalDateTime.now().plusSeconds(360);
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
        }
    }

    @Override
    public void onChanged(Change c) {
        System.out.println("Poller got notified about change");
        this.reminders = new BinaryDBAdapter().load();
        //TODO only update reminders when the proper change happens
        /*
        if (c instanceof ObservableList) {
            this.reminders = new BinaryDBAdapter().load();
        }
        */
    }


}
