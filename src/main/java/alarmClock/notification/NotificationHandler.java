package alarmClock.notification;

import alarmClock.model.Reminder;
import alarmClock.model.ReminderList;
import alarmClock.model.filtering.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by pascal on 5/23/17.
 * This Class configures and handles the diffrent Notifications, it configures which criterias must be true, so that a notification is
 * sent.
 *
 */
public class NotificationHandler {
    private ReminderList reminders;
    private ReminderList notifiedReminders;
    private ReminderList notifiedRemindersImminent;

    /**
     * Constructor initialized for each kind of Notification that is managed a list, where it can store
     * the reminder who have sent a notification.
     *
     * @param reminders is the ReminderList for which the notifications are handled
     */
    public NotificationHandler(ReminderList reminders) {
        notifiedReminders = new ReminderList();
        notifiedRemindersImminent = new ReminderList();
        setReminders(reminders);
    }

    /**
     *
     * ordinary setter
     * @param reminders is the ReminderList for which the notifications are handled
     */
    public void setReminders(ReminderList reminders) {
        this.reminders = reminders;
    }

    /**
     * configures the  diffrent kind of Notifications that will be used.
     * The first is the early Notification.
     * The second is a Notification that pops up, at the time the Reminder occures.
     * It passes CiteriaTesters to the Reminders, so that each Reminder can test for them and eventually send the
     * according notification.
     *
     * Reminders who have sent a notification get stored in a list, to exclude them for further testing. Otherwise
     * the reminders would send a notification every time handle is called.
     */
    public void handle() {
        ArrayList<Reminder> reminderList = reminders.getSerializable();
        for (Reminder reminder : reminderList) {
            Collection<CriteriaTester> importantStuffThisMonth = Arrays.asList(new IsThisMonth());
            if (!notifiedReminders.contains(reminder)) {
                            /**
                             *this passes the criteriaTesters to the Reminder itself, and lets the Reminder  send the notification if
                             * the criterias are met.
                             */
                            boolean success = reminder.notifyIf(importantStuffThisMonth);
                            if (success) notifiedReminders.add(reminder);
            }


            Collection<CriteriaTester> imminent = Arrays.asList(new IsInNextSeconds(1));
            if (!notifiedRemindersImminent.contains(reminder)) {
                            /**
                             *this passes the criteriaTesters to the Reminder itself, and lets the Reminder  send the notification if
                             * the criterias are met.
                             */
                            boolean success = reminder.notifyIf(imminent);
                            if (success) notifiedRemindersImminent.add(reminder);
            }
        }

    }

    /**
     * this method generats a summary of all Reminders, whos date is in the past of this year.
     */

    public void showPastEvents() {
        ArrayList<Reminder> reminderList = reminders.getSerializable();
        ArrayList<Reminder> passedReminders = new ArrayList<>();
        Collection<CriteriaTester> criteria = new ArrayList<>();
        /**
         * the boths criterias filter the Reminders for Reminders, which are dated  in th past and dated this year.
         */
        criteria.add(new IsPassed());
        criteria.add(new IsThisYear());

        for (Reminder reminder : reminderList) {
            if (reminder.meetsCriteria(criteria))
                passedReminders.add(reminder);
        }
                    // gets sure that a Notification is only sent, if it is not void.
                    if (passedReminders.size() != 0) {
                        new MultiReminderNotification(passedReminders).send();
                    }
    }
}
