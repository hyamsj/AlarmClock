package alarmClock.controller;

import alarmClock.model.BinaryDBAdapter;
import alarmClock.model.ReminderList;
import alarmClock.notification.NotificationHandler;
import javafx.collections.ListChangeListener;

/**
 * Created by pascal on 5/3/17.
 * Is a Singelton, see "Design Patterns. Elements of Reusable Object-Oriented Software"  by the Gang of Four
 * <p>
 * Calls the NotificationHandler, every time the ReminderList is updated, and additional on a regularly period.
 */
public class Poller implements ListChangeListener {
    private static Poller instance = null;
    private final int DELAY = 1000; // time between every poll in miliseconds
    private ReminderList reminders;
    private Thread thread;
    private NotificationHandler notificationHandler;
    private boolean notedPassed = false;

    /**
     * Starts the thread
     */ {
        thread = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(DELAY);
                    poll();
                }
            } catch (InterruptedException v) {
                v.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


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
     * Functions as the constructor, this was implemented to the singelton pattern.
     *
     * @return this poller.
     */
    public static Poller getInstance() {
        if (instance == null) {
            instance = new Poller();
        }
        return instance;
    }

    /**
     * Wakes up the notificationHandler
     * Requests the notificationHandler to show passed events, only once
     *
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
     *
     * @param c the object that has changed.
     */
    @Override
    public void onChanged(Change c) {
        this.reminders = new BinaryDBAdapter().load();
        notificationHandler.setReminders(reminders);
        /**
         * since NotificationHandler.handle() is called periodically this would not really be neccessair.
         * But if the DELAY is set to a  very big value, the Notifications appear directly if a Reminder that shoudld,
         * immediatly send a notification. Without this, there could be a noticable time gap.
         */
        notificationHandler.handle();
    }
}
