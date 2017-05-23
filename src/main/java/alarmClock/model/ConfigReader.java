package alarmClock.model;

import alarmClock.alertView.JavaFxNotification;

import java.util.ArrayList;

/**
 * Created by pascal on 5/17/17.
 */
public class ConfigReader {

    public ArrayList<Notification> getNotificationTypes() {
        ArrayList<Notification> notificationTypes = new ArrayList<>();
        if (true/*should check value from the configfile*/) {
            notificationTypes.add(new ConsoleNotification());
        } else {
            notificationTypes.remove(new ConsoleNotification());
        }

        if (true/*should check value from the configfile*/) {
            notificationTypes.add(new JavaFxNotification());
        } else {
            notificationTypes.remove(new JavaFxNotification());
        }

        return notificationTypes;
    }

    public String getColorScheme() {

        return "nightmode";
    }
}
