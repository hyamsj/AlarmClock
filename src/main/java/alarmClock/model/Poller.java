package alarmClock.model;

import alarmClock.alertView.MultiReminderNotification;
import alarmClock.model.filtering.*;
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
    public static final int EARLY_ALERT_TIME = 5; // minutes
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


    private  boolean notedPassed = false;
    public void poll() throws Exception {
        HandleNotifications();
        if(!notedPassed){
            showPastEvents();
            notedPassed =true;
        }
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
    private void showPastEvents() {
        ArrayList<Reminder> l = reminders.getSerializable();
        ArrayList<Reminder> passedReminders = new ArrayList<Reminder>();
        Collection<CriteriaTester> criteria = new ArrayList<CriteriaTester>();
        criteria.add(new IsPassed());
        criteria.add(new IsThisYear());

        //exampe how CriteraTester can be written on the fly
        criteria.add(
                r -> (!r.getTags().contains("hidden"))
        );
                //exampe how CriteraTester can be written on the fly
        criteria.add(
                // does not have Tag hidden
                new CriteriaTester() {
                    @Override
                    public boolean isTrue(Reminder r) {
                        return (!r.getTags().contains("hidden"));
                    }
                }
        );


        for (Reminder r : l) {
            if(r.meetsCriteria(criteria))
            passedReminders.add(r);
        }

        //is needed for JavaFx
        Platform.runLater(
                ()->{
                    //r.notifyIf(criteria);
                    if(passedReminders.size()!= 0) {
                        new MultiReminderNotification(passedReminders).send();
                    }
                }
        );
    }

    @Override
    public void onChanged(Change c) {
        System.out.println("Poller got notified about change");
        this.reminders = new BinaryDBAdapter().load();
        //TODO only update reminders when the proper change happens
    }


}
