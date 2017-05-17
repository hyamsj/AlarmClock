package alarmClock.model;

import alarmClock.alertView.MultyReminderNotification;
import alarmClock.model.Filter.CriteriaTester;
import alarmClock.model.Filter.IsPassed;
import alarmClock.model.Filter.IsThisMonth;
import alarmClock.model.Filter.hasTag;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

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


    //private  boolean notedPassed = false;
    public void poll() throws Exception {
        HandleNotifications();
        /*
        if(!notedPassed){
            showPastEvents();
            notedPassed =true;
        }
        */
    }
    private void HandleNotifications(){
        ArrayList<Reminder> l = reminders.getSerializable();
        for (Reminder r : l) {

        // remove is only here to test tag filtering
            String tag = "important";
            r.addTag(tag);
            Collection<CriteriaTester> importantStuffThisMonth = Arrays.asList(new IsThisMonth(),new hasTag(tag));
            if(  ! notifiedReminders.contains(r)){
                Platform.runLater(
                        ()->{
                            boolean success = r.notifyIf(importantStuffThisMonth);
                            if(success) notifiedReminders.add(r);
                        }

                );
            }

        // end Remove

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
        }

    }
    /*
    private void showPastEvents() {
        ArrayList<Reminder> l = reminders.getSerializable();
        ArrayList<Reminder> noteWorthyReminders = reminders.getSerializable();
        Collection<CriteriaTester> criteria = new ArrayList<CriteriaTester>();
        criteria.add(new IsPassed());

        for (Reminder r : l) {
            if(r.meetsCriteria(criteria))
            noteWorthyReminders.add(r);
        }
        Reminder r =noteWorthyReminders.get(0);
        Platform.runLater(
                ()->{
                    r.notifyIf(criteria);
                    /*
                    MultyReminderNotification multy = new MultyReminderNotification(noteWorthyReminders);
                    multy.send();
                }

        );
    }
                    */

    @Override
    public void onChanged(Change c) {
        System.out.println("Poller got notified about change");
        this.reminders = new BinaryDBAdapter().load();
        //TODO only update reminders when the proper change happens
    }


}
