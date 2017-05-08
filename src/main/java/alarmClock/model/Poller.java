package alarmClock.model;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by pascal on 5/3/17.
 */
public class Poller implements ListChangeListener {
    private ObservableList<Reminder> reminders;
    //private AtomicReference<ObservableList<Reminder>> atomicReferenceReminders;

    /*
    public AtomicReference<ObservableList<Reminder>> getAtomicReferenceReminders() {
        return atomicReferenceReminders;
    }
    public void setAtomicReferenceReminders(ObservableList<Reminder> reminders){
        atomicReferenceReminders =  (AtomicReference) reminders;
    }
    */

    private ObservableList<Reminder> notifiedReminders;
    private Thread one;
    private int delay = 1000;

    {
        one = new Thread() {

            public void run() {
                System.out.println("thread");
                try {
                    while (true) {
                        sleep(delay);
                        System.out.println("polling...");
                        //reminders = (ObservableList<Reminder>) getAtomicReferenceReminders();
                        poll();
                    }
                } catch (InterruptedException v) {
                    System.out.println(v);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }



    public Poller() {
        this.reminders = new BinaryDBAdapter().load();
        notifiedReminders = FXCollections.observableArrayList();
        //setAtomicReferenceReminders(reminders);
        one.start();
    }

    public void poll() throws Exception {
        for (Reminder r : reminders) {
            LocalDateTime reminderTime = r.getTime();
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime later = now.plusMinutes(5);
            if (
                    reminderTime.isAfter(now)
                            && later.isAfter(reminderTime)
                            && !notifiedReminders.contains(r)
                    ) {
                Notification n = new ConsoleNotification(r);
                n.send();
                System.out.print("Notify");
                notifiedReminders.addAll(r);
            }
        }
    }

    @Override
    public void onChanged(Change c) {
        System.out.println("Poller got notified about change");
        if (c instanceof ObservableList) {
            this.reminders = new BinaryDBAdapter().load();
        }
    }


}
