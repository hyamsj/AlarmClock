package alarmClock.model;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by joni on 24/03/17.
 */
public class Reminder implements Serializable{

    //private SimpleStringProperty subject = new SimpleStringProperty("");
    private String subject = "";
    //private SimpleStringProperty description = new SimpleStringProperty("");
    private String description ="";
   // private SimpleObjectProperty <LocalDateTime> time = new SimpleObjectProperty<>();
    private LocalDateTime  time;
    //TODO date is not needed anymore since we have LocalDateTime which has a Date and a Time
    //private SimpleObjectProperty<LocalDate> date = new SimpleObjectProperty<>();
    LocalDate date;

    public Reminder(String subject, String description, LocalDateTime time, LocalDate date) {
        setSubject(subject);
        setDescription(description);
        setTime(time);
        setDate(date);
    }

    public String getSubject() {
        //return subject.get();
        return subject;
    }

    public SimpleStringProperty getSubjectProperty() {
        //return subject;
        return new SimpleStringProperty(subject);
    }


    public String getDescription() {
        return description;
        //return description.get();
    }

    public SimpleStringProperty getDescriptionProperty() {
        return new SimpleStringProperty(description);
        //return description;
    }

    public LocalDateTime getTime() {
        //return time.get();
        return time;
    }

    public SimpleObjectProperty<LocalDateTime> getTimeProperty() {
        //return time;
        return new SimpleObjectProperty<>(time);
    }

    public LocalDate getDate() {
        //return date.get();
        return date;
    }

    public SimpleObjectProperty<LocalDate> getDateProperty() {
        //return date;
        return new SimpleObjectProperty<>(date);
    }

    public serializableReminder getSerializable(){
        return new serializableReminder(this.getSubject(),this.getDescription(),this.getTime(),this.getDate());
    }

    private void setSubject(String subject) {
        //this.subject.set(subject);
        this.subject = subject;
    }

    private void setDescription(String description) {
        //this.description.set(description);
        this.description = description;
    }
    private void setTime(LocalDateTime time) {
        //this.time.set(time);
        this.time = time;
    }

    private void setDate(LocalDate date) {
        //this.date.set(date);
        this.date = date;
    }




}
