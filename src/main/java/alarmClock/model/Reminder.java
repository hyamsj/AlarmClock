package alarmClock.model;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by joni on 24/03/17.
 */
public class Reminder implements Serializable{

    private SimpleStringProperty subject = new SimpleStringProperty("");
    private SimpleStringProperty description = new SimpleStringProperty("");
    private SimpleStringProperty time = new SimpleStringProperty("");
    private SimpleObjectProperty<LocalDate> date = new SimpleObjectProperty<>();

    public Reminder(String subject, String description, String time, LocalDate date) {
        setSubject(subject);
        setDescription(description);
        setTime(time);
        setDate(date);
    }

    public String getSubject() {
        return subject.get();
    }

    public SimpleStringProperty getSubjectProperty() {
        return subject;
    }


    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty getDescriptionProperty() {
        return description;
    }

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty getTimeProperty() {
        return time;
    }

    public LocalDate getDate() {
        return date.get();
    }

    public SimpleObjectProperty<LocalDate> getDateProperty() {
        return date;
    }

    public serializableReminder getSerializable(){
        return new serializableReminder(this.getSubject(),this.getDescription(),this.getTime(),this.getDate());
    }
    private void setSubject(String subject) {
        this.subject.set(subject);
    }

    private void setDescription(String description) {
        this.description.set(description);
    }
    private void setTime(String time) {
        this.time.set(time);
    }

    private void setDate(LocalDate date) {
        this.date.set(date);
    }




}
