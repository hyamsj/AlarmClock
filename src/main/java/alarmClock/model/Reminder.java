package alarmClock.model;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by joni on 24/03/17.
 */
public class Reminder implements Serializable {

    private String subject = "";
    private String description = "";
    private LocalDateTime date;

    public Reminder(String subject, String description, LocalDateTime date) {
        setSubject(subject);
        setDescription(description);
        setDate(date);
    }

    public String getSubject() {
        return subject;
    }

    public SimpleStringProperty getSubjectProperty() {
        return new SimpleStringProperty(subject);
    }


    public String getDescription() {
        return description;
    }

    public SimpleStringProperty getDescriptionProperty() {
        return new SimpleStringProperty(description);
    }

    public LocalDateTime getDate() {
        return date;
    }

    public SimpleObjectProperty<LocalDateTime> getTimeProperty() {
        return new SimpleObjectProperty<>(date);
    }

    private void setSubject(String subject) {
        this.subject = subject;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        Reminder r;
        if (o instanceof Reminder) {
            r = (Reminder) o;
            boolean isSame =
                    r.getDescription().equals(this.getDescription())
                            && r.getSubject().equals(this.getSubject())
                            && r.getDate().equals(this.getDate());

            return isSame;
        } else {
            return false;
        }

    }

    @Override
    public int hashCode() {
        int h = 1;
        h *= description.hashCode() * 13;
        h *= subject.hashCode() * 17;
        h *= date.hashCode() * 11;
        return h;
    }


    @Override
    public String toString() {
        String out = "";
        out = "Subject: " + this.getSubject() + "\n";
        out += "Description: " + this.getDescription() + "\n";
        out += "Date: " + this.getDate() + "\n";
        return out;
    }

}
