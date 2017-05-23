package alarmClock.model;
import alarmClock.alertView.MultiReminderNotification;
import alarmClock.model.filtering.*;
import javafx.application.Platform;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by pascal on 5/23/17.
 */
public class NotificationHandler {
    private ReminderList reminders;
    private ReminderList notifiedReminders;
    private ReminderList notifiedRemindersImminent;

    public NotificationHandler(ReminderList reminders){
        notifiedReminders = new ReminderList();
        notifiedRemindersImminent = new ReminderList();
        setReminders(reminders);
    }

    public void setReminders(ReminderList reminders){
        this.reminders = reminders;
    }

        public void handle(){
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


            Collection<CriteriaTester> imminent = Arrays.asList(new IsInNextSeconds(1));
            if(  ! notifiedRemindersImminent.contains(r)){
                Platform.runLater(
                        ()->{
                            boolean success = r.notifyIf(imminent);
                            if(success) notifiedRemindersImminent.add(r);
                        }

                );
            }


        }

    }
    public void showPastEvents() {
        ArrayList<Reminder> l = reminders.getSerializable();
        ArrayList<Reminder> passedReminders = new ArrayList<Reminder>();
        Collection<CriteriaTester> criteria = new ArrayList<CriteriaTester>();
        criteria.add(new IsPassed());
        criteria.add(new IsThisYear());

        //exampe how CriteraTester can be written on the fly
        //Pus this to documentation
        criteria.add(
                r -> (!r.getTags().contains("hidden"))
        );
                //exampe how CriteraTester can be written on the fly
        criteria.add(
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
        Platform.runLater(
                ()->{
                    //r.notifyIf(criteria);
                    if(passedReminders.size()!= 0) {
                        new MultiReminderNotification(passedReminders).send();
                    }
                }
        );
    }
}
