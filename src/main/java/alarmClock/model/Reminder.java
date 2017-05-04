package alarmClock.model;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by joni on 24/03/17.
 */
public class Reminder implements Serializable{

    private SimpleStringProperty subject = new SimpleStringProperty("");
    private SimpleStringProperty description = new SimpleStringProperty("");
    private SimpleObjectProperty <LocalDateTime> time = new SimpleObjectProperty<>();
    //TODO date is not needed anymore since we have LocalDateTime which has a Date and a Time
    private SimpleObjectProperty<LocalDate> date = new SimpleObjectProperty<>();

    public Reminder(String subject, String description, LocalDateTime time, LocalDate date) {
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

    public LocalDateTime getTime() {
        return time.get();
    }

    public SimpleObjectProperty<LocalDateTime> getTimeProperty() {
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
    private void setTime(LocalDateTime time) {
        this.time.set(time);
    }

    private void setDate(LocalDate date) {
        this.date.set(date);
    }




}
