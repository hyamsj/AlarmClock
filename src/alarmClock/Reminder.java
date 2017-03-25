package alarmClock;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by joni on 24/03/17.
 */
public class Reminder {

    private SimpleStringProperty subject = new SimpleStringProperty("");
    private SimpleStringProperty description = new SimpleStringProperty("");
    private SimpleStringProperty time = new SimpleStringProperty("");
    private SimpleStringProperty date = new SimpleStringProperty("");

    public Reminder(String subject, String description, String time, String date) {
        setSubject(subject);
        setDescription(description);
        setTime(time);
        setDate(date);
    }

    public String getSubject() {
        return subject.get();
    }

    public SimpleStringProperty subjectProperty() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject.set(subject);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }
}
