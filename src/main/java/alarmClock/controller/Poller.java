package alarmClock.controller;

import alarmClock.model.BinaryDBAdapter;
import alarmClock.notification.NotificationHandler;
import alarmClock.model.ReminderList;
import javafx.collections.ListChangeListener;

/**
 * Created by pascal on 5/3/17.
 * Is a Singelton
 * Tests regularly if a Reminder has to send a notification.
 *
 */
public class Poller implements ListChangeListener {
    public static final int EARLY_ALERT_TIME = 5; // minutes
    private static Poller instance = null;
    private ReminderList reminders;
    private Thread one;
    private int delay = 1000;
    private NotificationHandler notificationHandler;

    private Poller() {
        this.reminders = new BinaryDBAdapter().load();
        notificationHandler = new NotificationHandler(reminders);
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


    private boolean notedPassed = false;

    public void poll() throws Exception {
        notificationHandler.handle();
        if (!notedPassed) {
            notificationHandler.showPastEvents();
            notedPassed = true;
        }
    }


    @Override
    public void onChanged(Change c) {
        System.out.println("Poller got notified about change");
        this.reminders = new BinaryDBAdapter().load();
        notificationHandler.setReminders(reminders);
    }
}
