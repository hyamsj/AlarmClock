package alarmClock.model;

import alarmClock.notification.ConsoleNotification;
import alarmClock.notification.JavaFxNotification;
import alarmClock.notification.Notification;

import java.util.ArrayList;

/**
 * Created by pascal on 5/17/17.
 * This is the central place to configure the behavior of the application. Instead of hardcoding the boolean values.
 * It was planned to read a config file and set the boolean values according to the config File.
 */
public class ConfigReader {

    //If you change any of this Hardcoded boolean Values.
    // The ConfigReaderTest will fail, until you adjust them accordingly.
    private boolean enableJavFxNotification = true;
    private boolean enableConsoleNotification = true;
    private boolean enableDarkMode = true;
    private boolean enablePastReminders = true;
    private boolean enableImminentReminders = true;
    private boolean enableRemindersThisMonth = true;

    /**
     * @return the list with the notification types that are used to notify the user
     * We use Objects of the Type Notification to the list instead of Strings. This gives some type safety, like enums would too.
     * And makes it possible, that the Reminder can elegantly itterate throug all notifications it gets in his doNotify() Method
     */
    public ArrayList<Notification> getNotificationTypes() {
        ArrayList<Notification> notificationTypes = new ArrayList<>();
        if (enableConsoleNotification) {
            notificationTypes.add(new ConsoleNotification());
        } else {
            notificationTypes.remove(new ConsoleNotification());
        }

        if (enableJavFxNotification) {
            notificationTypes.add(new JavaFxNotification());
        } else {
            notificationTypes.remove(new JavaFxNotification());
        }
        return notificationTypes;
    }


    /**
     * Getter to check if pastReminders are enabled
     *
     * @return true if it is enabled
     */
    public boolean isEnablePastReminders() {
        return enablePastReminders;
    }

    /**
     * Getter to check if imminentReminders are enabled
     *
     * @return true if it is enabled
     */
    public boolean isEnableImminentReminders() {
        return enableImminentReminders;
    }

    /**
     * Getter to check if reminders this month are enabled
     *
     * @return true if it is enabled
     */
    public boolean isEnableRemindersThisMonth() {
        return enableRemindersThisMonth;
    }

    /**
     * Getter to check if darkMode is enabled
     * if its not enabled the JavaFx default is used.
     *
     * @return true if it is enabled
     */
    public boolean isEnableDarkMode() {
        return enableDarkMode;
    }
}
