package alarmClock.model;

import alarmClock.alertView.JavaFxNotification;

import java.util.ArrayList;

/**
 * Created by pascal on 5/17/17.
 *
 */
public class ConfigReader {
    /**
     *This is the central place to configure the behavior of the Application.Instead of Hardcoding the boolean values.
     * It was planed to read a config File and set the boolean values according to the config File.
     * @return the List with the notification types that are used to notify the user
     */
    boolean enableJavFxNotification= false;
    boolean enableConsoleNotification= false;

    public ArrayList<Notification> getNotificationTypes() {
        ArrayList<Notification> notificationTypes = new ArrayList<>();
        if (enableConsoleNotification){
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

    public String getColorScheme() {

        return "nightmode";
    }
}
