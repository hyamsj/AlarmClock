package alarmClock.controller;

import alarmClock.model.BinaryDBAdapter;
import alarmClock.notification.NotificationHandler;
import alarmClock.model.ReminderList;
import javafx.collections.ListChangeListener;

/**
 * Created by pascal on 5/3/17.
 * Is a Singelton
 * Tests regularly if a Reminder has to send a notification
 */
public class Poller implements ListChangeListener {
    private static Poller instance = null;
    private ReminderList reminders;
    private Thread thread;
    private final int DELAY = 1000; // time between every poll
    private NotificationHandler notificationHandler;

    /**
     * Constructor. Is private because it follows the singleton pattern.
     * Starts the thread, loads the data from the DB.
     * Initializes the notification handler
     */
    private Poller() {
        this.reminders = new BinaryDBAdapter().load();
        notificationHandler = new NotificationHandler(reminders);
        thread.start();
    }

    /**
     * Functions as the constructor
     * @return this poller.
     */
    public static Poller getInstance() {
        if (instance == null) {
            instance = new Poller();
        }
        return instance;
    }


    /**
     * Starts the thread
     */
    {
        thread = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(DELAY);
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

    /**
     * Wakes up the notificationHandler
     * Requests the notificationHandler to show passed events, only once
     * @throws Exception
     */
    public void poll() throws Exception {
        notificationHandler.handle();
        if (!notedPassed) {
            notificationHandler.showPastEvents();
            notedPassed = true;
        }
    }


    /**
     * Listener that gets called when the data in the reminderList changes
     * @param c the object that has changed.
     */
    @Override
    public void onChanged(Change c) {
        this.reminders = new BinaryDBAdapter().load();
        notificationHandler.setReminders(reminders);
    }
}
