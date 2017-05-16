package alarmClock.model;

import javafx.collections.ListChangeListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by pascal on 5/3/17.
 * Is a Singelton
 * Tests regularly if a Reminder has to send a notification
 */
public class Poller implements ListChangeListener {
    public static final int EARLY_ALERT_TIME = 5;
    private static Poller instance = null;
    private ReminderList reminders;
    private ReminderList notifiedReminders;
    private Thread one;
    private int delay = 1000;

    private Poller() {
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
        /*
        l.stream()
                .filter(r->new IsInNextMin().isTrue(r))
                .filter(r-> !notifiedReminders.contains(r))
                .map(r-> r.notifyIf(new IsToday()))
                .map(r-> notifiedReminders.add(r))
        ;
        */

        for (Reminder r : l) {
            //TODO or make the Reminder store if it did a notifications and isTrue against the Reminder
            /*

            if(  ! notifiedReminders.contains(r)){
                boolean success = r.notifyIf(new IsInNextMin());
                if(success) notifiedReminders.add(r);
            }


            if(  ! notifiedReminders.contains(r)){
                boolean success = r.notifyIf(new IsToday());
                if(success) notifiedReminders.add(r);
            }
            */


            //TODO remove is only here to test tag filtering
            String tag = "important";
            r.addTag(tag);
            Collection<CriteriaTester> importantStuffThisMonth = Arrays.asList(new IsThisMonth(),new hasTag(tag));
            if(  ! notifiedReminders.contains(r)){
                boolean success = r.notifyIf(importantStuffThisMonth);
                if(success) notifiedReminders.add(r);
            }
            //TODO end Remove


            /*was pushed to the Reminder
            LocalDateTime reminderTime = r.getDate();
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime later = now.plusMinutes(EARLY_ALERT_TIME);
            LocalDateTime almostNow = now.plusSeconds(60);
            if (
                    reminderTime.isAfter(now)
                            && later.isAfter(reminderTime)
                            && !notifiedReminders.contains(r)
                    ) {
                Notification n = new ConsoleNotification(r);
                System.out.println("Notify");
                Platform.runLater(() -> {
                    EarlyAlert earlyAlert = new EarlyAlert(r);
                });
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
