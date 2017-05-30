package alarmClock.model;

import alarmClock.notification.JavaFxNotification;
import alarmClock.notification.Notification;

import java.util.ArrayList;

/**
 * Created by pascal on 5/17/17.
 * This is the central place to configure the behavior of the application. Instead of hardcoding the boolean values.
 * It was planned to read a config file and set the boolean values according to the config File.
 */
public class ConfigReader {

    private boolean enableJavFxNotification = true;
    private boolean enableConsoleNotification = true;
    private boolean enableDarkMode = true;
    private boolean enablePastReminders = true;
    private boolean enableImminentReminders = true;
    private boolean enableRemindersThisMonth = true; //TODO is this still here?

    /**
     * @return the list with the notification types that are used to notify the user
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
     * Returns the name of the colorScheme
     *
     * @return the name of the colorScheme
     */
    public String getColorScheme() {
        return "nightmode";
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
     *
     * @return true if it is enabled
     */
    public boolean isEnableDarkMode() {
        return enableDarkMode;
    }
}
