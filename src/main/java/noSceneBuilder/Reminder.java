package noSceneBuilder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;

/**
 * Created by joni on 25/04/17.
 */
public class Reminder implements Serializable {
    private String subject, description, time, date;

    public Reminder() {
        subject = "";
        description = "";
        time = "";
        date = "";
    }

    public Reminder(String subject, String description, String time, String date) {
        this.subject = subject;
        this.description = description;
        this.time = time;
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ObservableList<Reminder> getReminders() {
        ObservableList<Reminder> products = FXCollections.observableArrayList();
        return products;
    }
}
